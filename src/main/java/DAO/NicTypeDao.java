package DAO;

import entity.Base;
import entity.NicType;
import exceptiom.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NicTypeDao implements Dao<Long, NicType> {
    private static final NicTypeDao INSTANCE = new NicTypeDao();

    private static final String DELETE_SQL = """
            DELETE FROM nicotine_type
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO nicotine_type (nicotine_type)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE nicotine_type
            SET
            nicotine_type = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT nt.id,
                   nt.nicotine_type
            FROM nicotine_type nt
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE nt.id = ?
            """;

    public static NicTypeDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public NicType save(NicType nicType) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nicType.getNicType());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                nicType.setId(generatedKeys.getLong("id"));
            }
            return nicType;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(NicType nicType) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, nicType.getNicType());
            preparedStatement.setLong(2, nicType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<NicType> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            NicType nicType = null;
            if (resultSet.next()) {
                nicType = buildNicType(resultSet);
            }
            return Optional.ofNullable(nicType);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private NicType buildNicType(ResultSet resultSet) {
        try {
            return NicType.builder()
                    .id(resultSet.getLong("id"))
                    .nicType(resultSet.getString("nicotine_type"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<NicType> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<NicType> nicTypes = new ArrayList<>();
            while (resultSet.next()) {
                nicTypes.add(buildNicType(resultSet));
            }
            return nicTypes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
