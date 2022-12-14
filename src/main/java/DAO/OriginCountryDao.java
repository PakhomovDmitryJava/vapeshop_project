package DAO;

import entity.OriginCountry;
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

public class OriginCountryDao implements Dao<Long, OriginCountry> {
    private static final OriginCountryDao INSTANCE = new OriginCountryDao();

    private static final String DELETE_SQL = """
            DELETE FROM origin_country
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO origin_country (country)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE origin_country
            SET
            country = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT c.id,
                   c.country
            FROM origin_country c
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE c.id = ?
            """;


    public static OriginCountryDao getInstance() {
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
    public OriginCountry save(OriginCountry originCountry) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, originCountry.getCountry());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                originCountry.setId(generatedKeys.getLong("id"));
            }
            return originCountry;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(OriginCountry originCountry) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, originCountry.getCountry());
            preparedStatement.setLong(2, originCountry.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<OriginCountry> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            OriginCountry originCountry = null;
            if (resultSet.next()) {
                originCountry = buildOriginCountry(resultSet);
            }
            return Optional.ofNullable(originCountry);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private OriginCountry buildOriginCountry(ResultSet resultSet) {
        try {
            return OriginCountry.builder()
                    .id(resultSet.getLong("id"))
                    .country(resultSet.getString("country"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<OriginCountry> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<OriginCountry> bases = new ArrayList<>();
            while (resultSet.next()) {
                bases.add(buildOriginCountry(resultSet));
            }
            return bases;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
