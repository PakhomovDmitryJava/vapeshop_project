package DAO;

import entity.NicConcentration;
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

public class NicConcentrationDao implements Dao<Long, NicConcentration> {
    private static final NicConcentrationDao INSTANCE = new NicConcentrationDao();

    private static final String DELETE_SQL = """
            DELETE FROM nicotine_concentration
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO nicotine_concentration ( concentration)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE nicotine_concentration
            SET
            concentration = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT nc.id,
                   nc.concentration
            FROM nicotine_concentration nc
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE nc.id = ?
            """;

    public static NicConcentrationDao getInstance() {
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
    public NicConcentration save(NicConcentration nicConcentration) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nicConcentration.getNicConcentration());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                nicConcentration.setId(generatedKeys.getLong("id"));
            }
            return nicConcentration;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(NicConcentration nicConcentration) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, nicConcentration.getNicConcentration());
            preparedStatement.setLong(2, nicConcentration.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<NicConcentration> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            NicConcentration nicConcentration = null;
            if (resultSet.next()) {
                nicConcentration = buildNicConcentration(resultSet);
            }
            return Optional.ofNullable(nicConcentration);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private NicConcentration buildNicConcentration(ResultSet resultSet) {
        try {
            return NicConcentration.builder()
                    .id(resultSet.getLong("id"))
                    .nicConcentration(resultSet.getString("concentration"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<NicConcentration> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<NicConcentration> bases = new ArrayList<>();
            while (resultSet.next()) {
                bases.add(buildNicConcentration(resultSet));
            }
            return bases;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
