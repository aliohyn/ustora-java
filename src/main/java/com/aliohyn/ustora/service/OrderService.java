package com.aliohyn.ustora.service;

import com.aliohyn.ustora.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getItemsByPage(int page, int size);
    Long getCount();
    Order getItemById(Long id);
}
