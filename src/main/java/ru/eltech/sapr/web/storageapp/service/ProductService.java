package ru.eltech.sapr.web.storageapp.service;

import ru.eltech.sapr.web.storageapp.dao.H2ProductDao;
import ru.eltech.sapr.web.storageapp.dao.ProductDao;
import ru.eltech.sapr.web.storageapp.model.Product;

import java.util.List;

public class ProductService implements IProductService {
    private final ProductDao productdao;

    public ProductService(H2ProductDao h2ProductDao, ProductDao input_dao) {
        this.productdao = input_dao;
        //this.h2dao = h2ProductDao;
    }

    @Override
    public List<Product> getAll() {
        return productdao.getAll();
    }

    @Override
    public Product getById(long input_id) {
        return productdao.getById(input_id);
    }

    @Override
    public Product create(String input_name, int input_price, String input_comments) {
        return productdao.create(input_name, input_price, input_comments);
    }

    @Override
    public void update(Product input_Product) {
        productdao.update(input_Product);
    }

    @Override
    public boolean delete(long input_id) {
        return productdao.delete(input_id);
    }
}
