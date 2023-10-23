package org.sdia.customerservice;

import org.sdia.customerservice.entities.Customer;
import org.sdia.customerservice.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
  @Bean
   CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.saveAll(List.of(
                    Customer.builder().name("sabile") .email("sab@gmail.com").build(),
                    Customer.builder().name("alaa") .email("alaa@gmail.com").build(),
                    Customer.builder().name("name") .email("name@gmail.com").build()
                    ));
            customerRepository.findAll().forEach(System.out::println);
        };
   }
}
