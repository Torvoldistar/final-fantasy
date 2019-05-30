package ru.eltech.sapr.web.contactsapp.servlets;

import ru.eltech.sapr.web.contactsapp.ConnectionManager;
import ru.eltech.sapr.web.contactsapp.dao.H2ContactsDao;
import ru.eltech.sapr.web.contactsapp.dao.InMemoryPhones;
import ru.eltech.sapr.web.contactsapp.service.ContactService;
import ru.eltech.sapr.web.contactsapp.service.ContactServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;

@WebListener
public class ServiceInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            DataSource dataSource = ConnectionManager.createDataSource();
            ContactServiceImpl service = new ContactServiceImpl(
                    new H2ContactsDao(dataSource),
                    InMemoryPhones.INSTANCE);

            sce.getServletContext().setAttribute(ContactService.SERVICE_NAME, service);
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize contacts service");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
