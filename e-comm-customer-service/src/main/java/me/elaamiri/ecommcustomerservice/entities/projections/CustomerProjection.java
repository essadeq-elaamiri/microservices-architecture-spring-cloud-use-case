package me.elaamiri.ecommcustomerservice.entities.projections;

import me.elaamiri.ecommcustomerservice.entities.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = Customer.class)
public interface CustomerProjection {
    Long getId();
    String getName();
    String getEmail();
}
