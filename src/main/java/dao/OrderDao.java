package dao;

import entity.Order;
import entity.Role;
import entity.User;
import exception.DaoException;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OrderDao implements Dao<Long, Order> {
    private static final OrderDao INSTANCE = new OrderDao();
    private static final String DELETE_SQL = """
            DELETE FROM "order"
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO "order" (user_id, date_of_order, is_paid, date_of_payment)
            VALUES (?,?,?,?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE "order"
            SET
            user_id = ? ,
            date_of_order = ? ,
            is_paid = ? ,
            date_of_payment = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT u.id,
                   u.first_name ,
                   u.last_name    ,
                   u.date_of_birth,
                   u.address      ,
                   u.email        ,
                   u.mobile_phone ,
                   u."password"   ,
                   r.role,
                   o.id,
                   o.user_id,
                   o.date_of_payment,
                   o.is_paid,
                   o.date_of_order
            FROM "user" u
                     join vapeshop_repository.public."order" o on u.id = o.user_id
                     join roles r on r.id = u.role_id
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE o.id = ?
            """;


    private final UserDao userDao = UserDao.getInstance();


    public static OrderDao getInstance() {
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
    public Order save(Order order) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setOrderFields(order, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Order order) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            setOrderFields(order, preparedStatement);
            preparedStatement.setLong(5, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setOrderFields(Order order, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            preparedStatement.setBoolean(3, order.isPaid());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getPaymentDate()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order buildOrder(ResultSet resultSet) {
        try {
            var role = Role.builder()
                    .id(resultSet.getLong("role_id"))
                    .role(resultSet.getString("role"))
                    .build();
            var user = User.builder()
                    .id(resultSet.getLong("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .dateOfBirth(resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate())
                    .address(resultSet.getString("address"))
                    .email(resultSet.getString("email"))
                    .mobilePhone(resultSet.getString("mobile_phone"))
                    .password(resultSet.getString("password"))
                    .role(role)
                    .build();
            return Order.builder()
                    .id(resultSet.getLong("id"))
                    .user(user)
                    .orderDate(resultSet.getTimestamp("date_of_order").toLocalDateTime())
                    .isPaid(resultSet.getBoolean("is_paid"))
                    .paymentDate(resultSet.getTimestamp("date_of_payment").toLocalDateTime())
                    .build();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
