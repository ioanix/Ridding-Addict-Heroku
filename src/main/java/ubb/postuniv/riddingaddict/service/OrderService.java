package ubb.postuniv.riddingaddict.service;

import ubb.postuniv.riddingaddict.model.pojo.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order finalizePurchase(Order order);
}
