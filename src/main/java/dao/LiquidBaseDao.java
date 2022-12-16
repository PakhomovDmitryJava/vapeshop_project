package dao;

import entity.LiquidBase;
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
public class LiquidBaseDao implements Dao<Long, LiquidBase> {

    private static final LiquidBaseDao INSTANCE = new LiquidBaseDao();

    private static final String DELETE_SQL = """
            DELETE FROM liquid_base
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO liquid_base (base)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE liquid_base
            SET
            base = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT b.id,
                   b.base
            FROM liquid_base b
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE b.id = ?
            """;


    public static LiquidBaseDao getInstance() {
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
    public entity.LiquidBase save(entity.LiquidBase liquidBase) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, liquidBase.getBase());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                liquidBase.setId(generatedKeys.getLong("id"));
            }
            return liquidBase;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(entity.LiquidBase liquidBase) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, liquidBase.getBase());
            preparedStatement.setLong(2, liquidBase.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<entity.LiquidBase> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            entity.LiquidBase liquidBase = null;
            if (resultSet.next()) {
                liquidBase = buildBase(resultSet);
            }
            return Optional.ofNullable(liquidBase);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private entity.LiquidBase buildBase(ResultSet resultSet) {
        try {
            return entity.LiquidBase
                    .builder()
                    .id(resultSet.getLong("id"))
                    .base(resultSet.getString("base"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<entity.LiquidBase> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<entity.LiquidBase> bases = new ArrayList<>();
            while (resultSet.next()) {
                bases.add(buildBase(resultSet));
            }
            return bases;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
