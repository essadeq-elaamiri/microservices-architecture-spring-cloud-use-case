package me.elaamiri.ecommorderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.ecommorderservice.model.Customer;
import me.elaamiri.ecommorderservice.model.Product;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private int quantity;
    private double discount;
    private Long productID;
    @ManyToOne
    private Order order;
    @Transient // not to be persistent
    private Product product;
}
