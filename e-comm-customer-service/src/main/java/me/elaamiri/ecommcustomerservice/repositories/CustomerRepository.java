package me.elaamiri.ecommcustomerservice.repositories;

import me.elaamiri.ecommcustomerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    void deleteById(Long customerId);

}
