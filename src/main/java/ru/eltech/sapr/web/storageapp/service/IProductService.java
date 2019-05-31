package ru.eltech.sapr.web.storageapp.service;

import ru.eltech.sapr.web.storageapp.model.Product;

import java.util.List;

public interface IProductService {

    String SERVICE_NAME = "ContactService";
    List<Product> getAll();

    Product getById(long id);

    Product create(String name, int price, String comments);

    void update(Product product);

    boolean delete(long id);
}

