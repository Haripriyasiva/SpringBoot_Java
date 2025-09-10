package com.example.customer_service.service.impl;

import com.example.customer_service.exception.ResourceNotFoundException;
import com.example.customer_service.model.Customer;
import com.example.customer_service.repository.CustomerRepository;
import com.example.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer save(Customer customer) {
        // For SQLite + unique email, a duplicate will throw on flush
        return repository.save(customer);
    }
}
