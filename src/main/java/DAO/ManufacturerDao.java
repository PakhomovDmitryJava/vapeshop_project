package DAO;

import entity.Manufacturer;
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

public class ManufacturerDao implements Dao<Long, Manufacturer> {
    private static final ManufacturerDao INSTANCE = new ManufacturerDao();

    private static final String DELETE_SQL = """
            DELETE FROM manufacturer_name
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO manufacturer_name ( manufacturer)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE manufacturer_name
            SET
            manufacturer = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT m.id,
                   m.manufacturer
            FROM manufacturer_name m
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE m.id = ?
            """;


    public static ManufacturerDao getInstance() {
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
    public Manufacturer save(Manufacturer manufacturer) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getManufacturer());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                manufacturer.setId(generatedKeys.getLong("id"));
            }
            return manufacturer;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, manufacturer.getManufacturer());
            preparedStatement.setLong(2, manufacturer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Manufacturer manufacturer = null;
            if (resultSet.next()) {
                manufacturer = buildBase(resultSet);
            }
            return Optional.ofNullable(manufacturer);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Manufacturer buildBase(ResultSet resultSet) {
        try {
            return Manufacturer.builder()
                    .id(resultSet.getLong("id"))
                    .manufacturer(resultSet.getString("manufacturer_name"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Manufacturer> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(buildBase(resultSet));
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
