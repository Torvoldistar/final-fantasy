package ru.eltech.sapr.web.storageapp;

import ru.eltech.sapr.web.storageapp.dao.H2ProductDao;
import ru.eltech.sapr.web.storageapp.dao.ProductBase;
import ru.eltech.sapr.web.storageapp.model.Product;
import ru.eltech.sapr.web.storageapp.service.ProductService;
import ru.eltech.sapr.web.storageapp.exception.ServiceException;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = ConnectionManager.createDataSource();
        ProductService service = new ProductService(
                new H2ProductDao(dataSource),
                ProductBase.INSTANCE);

        try {
            service.create("Blue Tool", 10000,"-");
            service.create("Green Tool", 20000, "Nice Tool");

            for (Product product : service.getAll()) {
                System.out.println(product);
            }
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}
