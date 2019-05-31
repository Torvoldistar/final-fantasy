package ru.eltech.sapr.web.storageapp.dao;

import ru.eltech.sapr.web.storageapp.model.Order;
import ru.eltech.sapr.web.storageapp.exception.ServiceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2OrderDao implements OrderDao {
    private final DataSource dataSource;

    public H2OrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Order> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from ORDERS")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load products");

            List<Order> orders = new ArrayList<>();
            while (rs.next())
                orders.add(retrieveOrder(rs));
            return orders;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    private Order retrieveOrder(ResultSet rs) throws SQLException {
        return new Order(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );
    }

    @Override
    public Order getById(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("select ID, CUSTOMERNAME, CUSTOMERPHONE, CUSTOMERORDER from PRODUCTS where ID = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return retrieveOrder(rs);
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order create(String customerName, String customerPhone, String customerOrder) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into ORDERS (CUSTOMERNAME, CUSTOMERPHONE, CUSTOMERORDER) values (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, customerName);
            statement.setString(2, customerPhone);
            statement.setString(3, customerOrder);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create order");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new Order(id, customerName, customerPhone, customerOrder);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Order order) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE ORDERS " +
                        "SET CUSTOMERNAME = ?, CUSTOMERPHONE = ?, CUSTOMERORDER = ?" +
                        "WHERE ID= ?");
        ) {
            statement.setString(1, order.getCustomerName());
            statement.setString(2, order.getCustomerPhone());
            statement.setString(3, order.getCustomerOrder());
            statement.setLong(4, order.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
                throw new ServiceException("Unable to update order");
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE from ORDERS " +
                        "WHERE ID = ?");
        ) {
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
                throw new ServiceException("Unable to delete order");
            return true;
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }
}