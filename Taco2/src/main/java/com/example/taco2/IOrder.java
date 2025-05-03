package com.example.taco2;

import java.util.List;

public interface IOrder {
    Order findOneOrder(long ordId);
    List<Order> findAll();
    Order saveOrder(Order order);
}
