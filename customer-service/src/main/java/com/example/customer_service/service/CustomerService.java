package com.example.customer_service.service;


import com.example.customer_service.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    Customer getById(Long id);
    Customer save(Customer customer);
}
