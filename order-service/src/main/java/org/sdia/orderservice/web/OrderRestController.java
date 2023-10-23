package org.sdia.orderservice.web;

import lombok.AllArgsConstructor;
import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repo.OrderRepository;
import org.sdia.orderservice.repo.ProductItemRepository;
import org.sdia.orderservice.services.CustomerRestClientService;
import org.sdia.orderservice.services.ProductRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class OrderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private ProductRestClientService productRestClientService;



    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi->{
            Product product= productRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}