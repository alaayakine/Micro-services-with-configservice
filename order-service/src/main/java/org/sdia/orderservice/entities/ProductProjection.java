package org.sdia.orderservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full-product",types = Order.class)
public interface ProductProjection {
    Long getId();
    String getName();
    Double getPrix();
    int getQuantity();
}
