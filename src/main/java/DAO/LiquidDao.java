package DAO;

import entity.Liquid;
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

@NoArgsConstructor
public class LiquidDao implements Dao<Long, Liquid> {
    private static final LiquidDao INSTANCE = new LiquidDao();

    private static final String DELETE_SQL = """
            DELETE FROM "liquid"
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO "liquid" (manuf_id, line_id, flavour_id, description, nic_type_id, nic_conc_id, base_id, country_id, price)
            VALUES (?,?,?,?,?,?,?,?,?,?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE "liquid"
            SET
            manuf_id = ? ,
            line_id = ? ,
            flavour_id = ? ,
            description = ? ,
            nic_type_id = ? ,
            nic_conc_id = ? ,
            base_id = ? ,
            country_id = ? ,
            price = ? ,
            stock = ?
            WHERE id = ?
            """;


    /*переписать*/
    private static final String FIND_ALL_SQL = """  
            SELECT id, manuf_id, line_id, flavour_id, description, nic_type_id, nic_conc_id, base_id, country_id, price, stock
            from liquid l
               /*      join manufacturer m on m.id = l.manufacturer_id
                     join liquid_line ll on ll.id = l.liquid_line_id
                     join liquid_taste lt on lt.id = l.liquid_taste_id
                     join nicotine_type nt on nt.id = l.nicotine_type_id
                     join nicotine_concentration nc on nc.id = l.nicotine_concentration_id
                     join base b on b.id = l.base_id
                     join origin_country oc on oc.id = l.country_id*/
                """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE l.id = ?
            """;
    private final ManufacturerDao manufacturerDao = ManufacturerDao.getInstance();
    private final LiquidLineDao liquidLineDao = LiquidLineDao.getInstance();
    private final FlavourTypeDao flavourTypeDao = FlavourTypeDao.getInstance();
    private final NicTypeDao nicTypeDao = NicTypeDao.getInstance();
    private final NicConcDao nicConcDao = NicConcDao.getInstance();
    private final BaseDao baseDao = BaseDao.getInstance();
    private final OriginCountryDao originCountryDao = OriginCountryDao.getInstance();

    public static LiquidDao getInstance() {
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
    public Liquid save(Liquid liquid) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setLiquidFields(liquid, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                liquid.setId(generatedKeys.getLong("id"));
            }
            return liquid;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Liquid liquid) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            setLiquidFields(liquid, preparedStatement);
            preparedStatement.setLong(11, liquid.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Liquid> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Liquid liquid = null;
            if (resultSet.next()) {
                liquid = buildLiquid(resultSet);
            }
            return Optional.ofNullable(liquid);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Liquid buildLiquid(ResultSet resultSet) {
        try {
            return Liquid.builder()
                    .id(resultSet.getLong("id"))
                    .manufacturer(manufacturerDao.findById(resultSet.getLong("manufacturer_id")).orElse(null))
                    .liquidLine(liquidLineDao.findById(resultSet.getLong("liquid_line_id")).orElse(null))
                    .flavourType(flavourTypeDao.findById(resultSet.getLong("liquid_taste_id")).orElse(null))
                    .description(resultSet.getString("description"))
                    .nicType(nicTypeDao.findById(resultSet.getLong("nicotine_type_id")).orElse(null))
                    .nicConc(nicConcDao.findById(resultSet.getLong("nicotine_concentration_id")).orElse(null))
                    .base(baseDao.findById(resultSet.getLong("base_id")).orElse(null))
                    .originCountry(originCountryDao.findById(resultSet.getLong("country_id")).orElse(null))
                    .price((resultSet.getBigDecimal("price")))
                    .stock(resultSet.getLong("stock"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Liquid> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Liquid> liquidTastes = new ArrayList<>();
            while (resultSet.next()) {
                liquidTastes.add(buildLiquid(resultSet));
            }
            return liquidTastes;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private void setLiquidFields(Liquid liquid, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, liquid.getManufacturer().getId());
        preparedStatement.setLong(2, liquid.getLiquidLine().getId());
        preparedStatement.setLong(3, liquid.getFlavourType().getId());
        preparedStatement.setString(4, liquid.getDescription());
        preparedStatement.setLong(5, liquid.getNicType().getId());
        preparedStatement.setLong(6, liquid.getNicConc().getId());
        preparedStatement.setLong(7, liquid.getBase().getId());
        preparedStatement.setLong(8, liquid.getOriginCountry().getId());
        preparedStatement.setBigDecimal(9, liquid.getPrice());
        preparedStatement.setLong(10, liquid.getStock());
    }
}
