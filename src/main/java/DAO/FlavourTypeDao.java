package DAO;

import entity.FlavourType;
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

public class FlavourTypeDao implements Dao<Long, FlavourType> {
    private static final FlavourTypeDao INSTANCE = new FlavourTypeDao();

    private static final String DELETE_SQL = """
            DELETE FROM flavour_type
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO flavour_type (flavour)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE flavour_type
            SET
            flavour = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT ft.id,
                   ft.flavour
            FROM flavour_type ft
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE ft.id = ?
            """;

    public static FlavourTypeDao getInstance() {
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
    public FlavourType save(FlavourType flavourType) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, flavourType.getFlavourType());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                flavourType.setId(generatedKeys.getLong("id"));
            }
            return flavourType;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(FlavourType flavourType) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, flavourType.getFlavourType());
            preparedStatement.setLong(2, flavourType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<FlavourType> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            FlavourType flavourType = null;
            if (resultSet.next()) {
                flavourType = buildBase(resultSet);
            }
            return Optional.ofNullable(flavourType);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private FlavourType buildBase(ResultSet resultSet) {
        try {
            return FlavourType.builder()
                    .id(resultSet.getLong("id"))
                    .flavourType(resultSet.getString("type_of_taste"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FlavourType> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<FlavourType> flavourTypes = new ArrayList<>();
            while (resultSet.next()) {
                flavourTypes.add(buildBase(resultSet));
            }
            return flavourTypes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
