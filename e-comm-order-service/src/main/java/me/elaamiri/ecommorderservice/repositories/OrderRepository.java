package me.elaamiri.ecommorderservice.repositories;

import me.elaamiri.ecommorderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
    // to be accessable via rest
    @RestResource(path = "/byCustomerId")
    List<Order> findByCustomerID(@Param("customerId") Long customerId);
}
