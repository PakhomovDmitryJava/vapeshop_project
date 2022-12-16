package dao;

import entity.LiquidTaste;
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
public class LiquidTasteDao implements Dao<Long, LiquidTaste> {
    private static final LiquidTasteDao INSTANCE = new LiquidTasteDao();

    private static final String DELETE_SQL = """
            DELETE FROM liquid_taste
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO liquid_taste (taste)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE liquid_taste
            SET
            taste = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT lt.id,
                   lt.taste
            FROM liquid_taste lt
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE lt.id = ?
            """;

    public static LiquidTasteDao getInstance() {
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
    public LiquidTaste save(LiquidTaste liquidTaste) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, liquidTaste.getLiquidTaste());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                liquidTaste.setId(generatedKeys.getLong("id"));
            }
            return liquidTaste;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(LiquidTaste liquidTaste) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, liquidTaste.getLiquidTaste());
            preparedStatement.setLong(2, liquidTaste.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<LiquidTaste> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            LiquidTaste liquidTaste = null;
            if (resultSet.next()) {
                liquidTaste = buildBase(resultSet);
            }
            return Optional.ofNullable(liquidTaste);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private LiquidTaste buildBase(ResultSet resultSet) {
        try {
            return LiquidTaste.builder()
                    .id(resultSet.getLong("id"))
                    .liquidTaste(resultSet.getString("taste"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<LiquidTaste> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<LiquidTaste> liquidTastes = new ArrayList<>();
            while (resultSet.next()) {
                liquidTastes.add(buildBase(resultSet));
            }
            return liquidTastes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
