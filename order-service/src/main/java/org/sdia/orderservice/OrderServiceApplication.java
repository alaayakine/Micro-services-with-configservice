package org.sdia.orderservice;

import org.sdia.orderservice.entities.Order;
import org.sdia.orderservice.entities.ProductItem;
import org.sdia.orderservice.enums.OrderStatus;
import org.sdia.orderservice.model.Customer;
import org.sdia.orderservice.model.Product;
import org.sdia.orderservice.repo.OrderRepository;
import org.sdia.orderservice.repo.ProductItemRepository;
import org.sdia.orderservice.services.CustomerRestClientService;
import org.sdia.orderservice.services.ProductRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, ProductRestClientService productRestClientService){
        return args -> {
            List<Customer> customers=customerRestClientService.AllCustomers().getContent().stream().toList();
            List<Product> products=productRestClientService.AllProducts().getContent().stream().toList();
            Customer  customer=customerRestClientService.customerById(1L);
            for (int i = 0; i < 15; i++) {
                Order  order=Order.builder()
                        .customerId(customers.get(new Random().nextInt(customers.size())).getId()).status(Math.random()>0.5?OrderStatus.CREATED:OrderStatus.PENDING)
                        .createdAt(new Date())
                        .build();
                 Order orderSv= orderRepository.save(order);
                for (int j = 0; j < products.size(); j++) {
                    if(Math.random()>0.67){
                    ProductItem productItem=ProductItem.builder().
                    order(orderSv).productId(products.get(j).getId())
                            .price(products.get(j).getPrix()).discount(Math.random()).quantity(new Random().nextInt(10)).build();
                      productItemRepository.save(productItem);
                    }}


            }
        };
    }
}
