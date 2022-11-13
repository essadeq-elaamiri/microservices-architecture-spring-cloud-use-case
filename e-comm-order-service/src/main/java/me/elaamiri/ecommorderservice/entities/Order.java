package me.elaamiri.ecommorderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.elaamiri.ecommorderservice.enumerations.OrderStatus;
import me.elaamiri.ecommorderservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderTable")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus orderStatus;
    private Long customerID;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItemList;
    @Transient
    private Customer customer;

    public double getTotal(){
        //return productItemList.stream().map(productItem -> productItem.getAmount()).reduce((amount, somme) -> somme + amount ).get();
        return productItemList.stream().map(ProductItem::getAmount).reduce(Double::sum).get();
    }

}
