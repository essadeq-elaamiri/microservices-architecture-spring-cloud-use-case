package me.elaamiri.ecomminventoryservice.entities.projections;

import me.elaamiri.ecomminventoryservice.entities.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProduct", types = Product.class)
public interface ProductProjection {
    public Long getId();
    public String getName();
    public double getPrice();
    public int getQuantity();
}
