package DAO;

import entity.Base;
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

public class BaseDao implements Dao<Long, Base> {

    private static final BaseDao INSTANCE = new BaseDao();

    private static final String DELETE_SQL = """
            DELETE FROM base
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO base (pg_vg_ratio)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE base
            SET
            pg_vg_ratio = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT b.id,
                   b.pg_vg_ratio
            FROM base b
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE b.id = ?
            """;


    public static BaseDao getInstance() {
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
    public Base save(Base base) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, base.getPrVgRatio());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                base.setId(generatedKeys.getLong("id"));
            }
            return base;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Base base) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, base.getPrVgRatio());
            preparedStatement.setLong(2, base.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Base> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Base base = null;
            if (resultSet.next()) {
                base = buildBase(resultSet);
            }
            return Optional.ofNullable(base);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Base buildBase(ResultSet resultSet) {
        try {
            return Base
                    .builder()
                    .id(resultSet.getLong("id"))
                    .prVgRatio(resultSet.getString("pg_vg_ratio"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Base> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Base> bases = new ArrayList<>();
            while (resultSet.next()) {
                bases.add(buildBase(resultSet));
            }
            return bases;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
