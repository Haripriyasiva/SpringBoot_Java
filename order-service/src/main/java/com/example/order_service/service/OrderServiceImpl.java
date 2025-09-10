package com.example.order_service.service;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<OrderResponse> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponse> getById(Long id) {
        return repository.findById(id).map(this::mapToResponse);
    }

    @Override
    public OrderResponse create(OrderRequest request) {
        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(request.getStatus() != null ? request.getStatus() : "PENDING")
                .orderDate(LocalDateTime.now()) // Always set current time
                .build();
        return mapToResponse(repository.save(order));
    }

    @Override
    public OrderResponse update(Long id, OrderRequest request) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));

        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setStatus(request.getStatus() != null ? request.getStatus() : order.getStatus());
        // Do not overwrite orderDate to preserve original creation timestamp

        return mapToResponse(repository.save(order));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .build();
    }
}
