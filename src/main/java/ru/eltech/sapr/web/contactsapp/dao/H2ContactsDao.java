package ru.eltech.sapr.web.contactsapp.dao;

import ru.eltech.sapr.web.contactsapp.exception.ContactServiceException;
import ru.eltech.sapr.web.contactsapp.model.Contact;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ContactsDao implements ContactsDao {
    private final DataSource dataSource;

    public H2ContactsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Contact> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from CONTACTS")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load contacts");

            List<Contact> contacts = new ArrayList<>();
            while (rs.next())
                contacts.add(retrieveContact(rs));
            return contacts;
        } catch (SQLException e) {
            throw new ContactServiceException(e);
        }
    }

    private Contact retrieveContact(ResultSet rs) throws SQLException {
        return new Contact(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        );
    }

    @Override
    public Contact getById(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("select id, first_name, last_name from CONTACTS where id = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return retrieveContact(rs);
            }
        } catch (SQLException e) {
            throw new ContactServiceException(e);
        }
    }

    @Override
    public Contact create(String firstName, String lastName) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into contacts (first_name, last_name) values (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create contact");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new Contact(id, firstName, lastName);
                } else {
                    throw new SQLException("Creating contact failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new ContactServiceException(e);
        }
    }

    @Override
    public void update(Contact contact) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE CONTACTS " +
                        "SET first_name = ?, last_name = ? " +
                        "WHERE id = ?");
        ) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setLong(3, contact.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
                throw new ContactServiceException("Unable to update contact");
        } catch (SQLException e) {
            throw new ContactServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE from CONTACTS " +
                        "WHERE id = ?");
        ){
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
                throw new ContactServiceException("Unable to delete contact");
            return true;
        } catch (SQLException e) {
            throw new ContactServiceException(e);
        }
    }
}
