package org.sdia.orderservice.services;

import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("inventory-service")
public interface ProductRestClientService {
    @GetMapping("/products/{id}?projection=full-product")
    public Product productById(@PathVariable Long id);
    @GetMapping("products?projection=full-product")
    public PagedModel<Product> AllProducts();
}
