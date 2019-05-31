package ru.eltech.sapr.web.storageapp.service;

import ru.eltech.sapr.web.storageapp.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    Order getById(long id);

    Order create(String customerName, String customerPhone, String customerOrder);

    void update(Order order);

    boolean delete(long id);
}
