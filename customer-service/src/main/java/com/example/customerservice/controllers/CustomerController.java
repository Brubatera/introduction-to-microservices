package com.example.customerservice.controllers;

import com.example.customerservice.domain.Customer;
import com.example.customerservice.feign.OrderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    private List<Customer> customerList = Arrays.asList(
            Customer.builder()
                    .Id(1)
                    .name("Bruno")
                    .build(),

            Customer.builder()
                    .Id(2)
                    .name("Elisa")
                    .build()
    );

    private OrderClient orderClient;

    public CustomerController(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @GetMapping("/{id}/orders")
    public Object getOrdersForCustomer(@PathVariable int id) {
        return orderClient.getOrdersForCustomer(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerList;
    }

    @GetMapping("/{id}")
    public Customer getCutomerById(@PathVariable int id) {
        return customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
