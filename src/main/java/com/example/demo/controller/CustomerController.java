package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/customer/new")
    public String createCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return "Customer saved";

    }


    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id);

    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetail) {
        customerDetail = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        customerDetail.setName(customerDetail.getName());
        customerDetail.setUsername(customerDetail.getUsername());
        customerDetail.setPassword(customerDetail.getPassword());
        customerRepository.save(customerDetail);

        return ResponseEntity.ok(customerDetail);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

}