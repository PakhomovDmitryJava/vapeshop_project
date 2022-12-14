package DAO;

import entity.LiquidLine;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiquidLineDao implements Dao<Long, LiquidLine> {

    private static final LiquidLineDao INSTANCE = new LiquidLineDao();

    private static final String DELETE_SQL = """
            DELETE FROM "liquid_line"
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO "liquid_line" (line)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE "liquid_line"
            SET
            line = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT ll.id, ll.line
            from liquid_line ll
               """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE ll.id = ?
            """;

    public static LiquidLineDao getInstance() {
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
    public LiquidLine save(LiquidLine liquidLine) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, liquidLine.getLiquidLine());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                liquidLine.setId(generatedKeys.getLong("id"));
            }
            return liquidLine;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(LiquidLine liquidLine) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, liquidLine.getLiquidLine());
            preparedStatement.setLong(2, liquidLine.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<LiquidLine> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            LiquidLine liquidLine = null;
            if (resultSet.next()) {
                liquidLine = buildLiquidLine(resultSet);
            }
            return Optional.ofNullable(liquidLine);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private LiquidLine buildLiquidLine(ResultSet resultSet) {
        try {
            return LiquidLine.builder()
                    .id(resultSet.getLong("id"))
                    .liquidLine(resultSet.getString("liquid_line"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<LiquidLine> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<LiquidLine> liquidLines = new ArrayList<>();
            while (resultSet.next()) {
                liquidLines.add(buildLiquidLine(resultSet));
            }
            return liquidLines;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
