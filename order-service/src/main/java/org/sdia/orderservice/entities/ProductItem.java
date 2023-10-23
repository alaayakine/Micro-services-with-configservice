package org.sdia.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sdia.orderservice.model.Product;

import javax.persistence.*;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long productId;
    @Transient
    private Product product;
    private Double price;
    private int quantity;
    private double discount;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order  order;
}
