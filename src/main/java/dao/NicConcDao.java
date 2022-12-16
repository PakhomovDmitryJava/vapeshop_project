package dao;

import entity.NicConc;
import exception.DaoException;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class NicConcDao implements Dao<Long, NicConc> {
    private static final NicConcDao INSTANCE = new NicConcDao();

    private static final String DELETE_SQL = """
            DELETE FROM nicotine_concentration
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO nicotine_concentration (nic_conc)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE nicotine_concentration
            SET
            nic_conc = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT nc.id,
                   nc.nic_conc
            FROM nicotine_concentration nc
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE nc.id = ?
            """;

    public static NicConcDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public NicConc save(NicConc nicConc) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nicConc.getNicConcentration());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                nicConc.setId(generatedKeys.getLong("id"));
            }
            return nicConc;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(NicConc nicConc) {
        try (var connetcion = ConnectionManager.get(); var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, nicConc.getNicConcentration());
            preparedStatement.setLong(2, nicConc.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<NicConc> findById(Long id) {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            NicConc nicConc = null;
            if (resultSet.next()) {
                nicConc = buildNicConcentration(resultSet);
            }
            return Optional.ofNullable(nicConc);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private NicConc buildNicConcentration(ResultSet resultSet) {
        try {
            return NicConc.builder().id(resultSet.getLong("id"))
                    .nicConcentration(resultSet.getString("nic_conc"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<NicConc> findAll() {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<NicConc> bases = new ArrayList<>();
            while (resultSet.next()) {
                bases.add(buildNicConcentration(resultSet));
            }
            return bases;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
