package me.elaamiri.ecommorderservice.web;

import lombok.AllArgsConstructor;
import me.elaamiri.ecommorderservice.entities.Order;
import me.elaamiri.ecommorderservice.model.Customer;
import me.elaamiri.ecommorderservice.model.Product;
import me.elaamiri.ecommorderservice.repositories.OrderRepository;
import me.elaamiri.ecommorderservice.repositories.ProductItemRepository;
import me.elaamiri.ecommorderservice.services.CustomerRestClientService;
import me.elaamiri.ecommorderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor // for dependency injection
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("No Order Found ...!"));
        Customer customer = customerRestClientService.getCustomerById(order.getCustomerID());
        order.setCustomer(customer);
        order.getProductItemList().forEach(productItem -> {
            Product product = inventoryRestClientService.getProductById(productItem.getProductID());
            productItem.setProduct(product);
        }); // bricoulage : that should be done via DTOs and Service Layer
        return order;
    }
}
