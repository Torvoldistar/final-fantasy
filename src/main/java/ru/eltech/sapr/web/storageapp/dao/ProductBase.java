package ru.eltech.sapr.web.storageapp.dao;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import ru.eltech.sapr.web.storageapp.model.Product;

public enum ProductBase implements ProductDao{
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Product> allProducts = new HashMap<>();

    @Override
    public Product getById(long id) {
        return allProducts.get(id);
    }

    @Override
    public Product create(String name, int price, String comments) {
        Product product = new Product(idGenerator.incrementAndGet(), name, price, comments);
        allProducts.put(product.getId(), product);
        return product;
    }

    @Override
    public void update(Product product) {
        allProducts.put(product.getId(), product);
    }

    @Override
    public boolean delete(long id) {
        Product removedProduct = allProducts.remove(id);
        return removedProduct != null;
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(allProducts.values());
    }

}
