package ru.eltech.sapr.web.contactsapp;

import ru.eltech.sapr.web.contactsapp.dao.H2ContactsDao;
import ru.eltech.sapr.web.contactsapp.dao.InMemoryPhones;
import ru.eltech.sapr.web.contactsapp.exception.ContactServiceException;
import ru.eltech.sapr.web.contactsapp.model.Contact;
import ru.eltech.sapr.web.contactsapp.service.ContactServiceImpl;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = ConnectionManager.createDataSource();
        ContactServiceImpl service = new ContactServiceImpl(
                new H2ContactsDao(dataSource),
                InMemoryPhones.INSTANCE);

        try {
            service.createContact("Petr", "Petrov");
            service.createContact("Ivan", "Ivanov");

            for (Contact contact : service.getAll()) {
                System.out.println(contact);
            }
        } catch (ContactServiceException e) {
            // TODO: log, handle
            System.err.println(e);
        }
    }
}
