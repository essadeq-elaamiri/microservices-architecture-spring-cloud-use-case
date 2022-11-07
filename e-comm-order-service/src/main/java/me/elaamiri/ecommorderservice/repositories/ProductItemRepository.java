package me.elaamiri.ecommorderservice.repositories;

import me.elaamiri.ecommorderservice.entities.Order;
import me.elaamiri.ecommorderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
