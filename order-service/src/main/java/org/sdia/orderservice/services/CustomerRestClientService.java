package org.sdia.orderservice.services;

import org.sdia.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("customer-service")
public interface CustomerRestClientService {
    @GetMapping("/customers/{id}?projection=full-customer")
    public Customer customerById(@PathVariable Long id);
    @GetMapping("customers")
    public PagedModel<Customer> AllCustomers();
}
