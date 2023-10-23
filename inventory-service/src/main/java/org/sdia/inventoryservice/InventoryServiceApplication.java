package org.sdia.inventoryservice;

import org.sdia.inventoryservice.entities.Product;
import org.sdia.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository){
        return args -> {
            for (int i = 0; i < 8; i++) {
                productRepository.save(
                        Product.builder().name("PC"+i+i) .prix(Math.random()*10000).quantity(1+(int) (Math.random()*200)).build()
                        );
            }

            productRepository.findAll().forEach(System.out::println);
        };
    }
}
