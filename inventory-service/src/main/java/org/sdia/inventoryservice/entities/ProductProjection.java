package org.sdia.inventoryservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "full-product",types = Product.class)
public interface ProductProjection {
    Long getId();
    String getName();
    Double getPrix();
    int getQuantity();
}
