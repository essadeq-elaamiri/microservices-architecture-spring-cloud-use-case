package me.elaamiri.ecommorderservice;

import me.elaamiri.ecommorderservice.entities.Order;
import me.elaamiri.ecommorderservice.entities.ProductItem;
import me.elaamiri.ecommorderservice.enumerations.OrderStatus;
import me.elaamiri.ecommorderservice.model.Customer;
import me.elaamiri.ecommorderservice.repositories.OrderRepository;
import me.elaamiri.ecommorderservice.repositories.ProductItemRepository;
import me.elaamiri.ecommorderservice.services.CustomerRestClientService;
import me.elaamiri.ecommorderservice.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
@EnableFeignClients // without it will not inject the FeignRestClientServices
public class ECommOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommOrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							InventoryRestClientService inventoryRestClientService){
		return  args -> {
			Collection<Customer> customers = customerRestClientService.getCustomers().getContent();

			customers.forEach(customer1 -> {
				Order order = Order.builder()
						.createdAt(new Date())
						.customer(customer1)
						.customerID(customer1.getId())
						.orderStatus(OrderStatus.CREATED)
						.build();
				Order order1 = orderRepository.save(order);
				for (int i= 1; i<= (new Random()).nextInt(3, 15) ; i++ ){
					Long productId = (new Random()).nextLong(1L, 4L);
					ProductItem productItem = ProductItem.builder()
							.discount(52.2)
							.product(inventoryRestClientService.getProductById(productId))
							.productID(productId)
							.order(order1)
							.price(1548)
							.build();

					productItemRepository.save(productItem);
				}
			});




		};
	}
}
