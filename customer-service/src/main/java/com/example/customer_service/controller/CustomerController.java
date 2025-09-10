package com.example.customer_service.controller;

import com.example.customer_service.model.Customer;
import com.example.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    // GET /api/customers
    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    // GET /api/customers/{id}
    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /api/customers
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer customer) {
        // id ignored if present; repository will assign
        return service.save(customer);
    }
}
