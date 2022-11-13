package me.elaamiri.ecommorderservice.entities.projections;

import me.elaamiri.ecommorderservice.entities.Order;
import me.elaamiri.ecommorderservice.entities.ProductItem;
import me.elaamiri.ecommorderservice.enumerations.OrderStatus;
import me.elaamiri.ecommorderservice.model.Customer;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Projection(name = "fullOrder", types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    OrderStatus getOrderStatus();
    Long getCustomerID();
}
