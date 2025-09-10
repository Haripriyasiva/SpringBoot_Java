package com.example.order_service.service;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.OrderResponse;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderResponse> getAll();
    Optional<OrderResponse> getById(Long id);
    OrderResponse create(OrderRequest request);
    OrderResponse update(Long id, OrderRequest request);
    void delete(Long id);
}
