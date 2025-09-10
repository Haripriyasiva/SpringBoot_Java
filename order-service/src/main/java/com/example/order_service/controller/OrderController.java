package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // ✅ Get all orders
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orders = service.getAll();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    // ✅ Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Optional<OrderResponse> order = service.getById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            throw new RuntimeException("Order not found with ID: " + id);
        }
    }


    // ✅ Create a new order
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Validated @RequestBody OrderRequest request) {
        OrderResponse created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ✅ Update order by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @Validated @RequestBody OrderRequest request) {
        OrderResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete order by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
