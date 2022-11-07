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

			List<ProductItem> productItemList = new ArrayList<>();
			for (int i= 1; i<= (new Random()).nextInt(1, 10) ; i++ ){
				Long productId = (new Random()).nextLong(1L, 4L);
				ProductItem productItem = ProductItem.builder()
						.discount(52.2)
						.product(inventoryRestClientService.getProductById(productId))
						.productID(productId)
						.price(1548)
						.build();
				productItemList.add(productItem);
				productItemRepository.save(productItem);
			}

			customers.forEach(customer1 -> {
				System.out.println(productItemList);
				Order order = Order.builder()
						.createdAt(new Date())
						.customer(customer1)
						.customerID(customer1.getId())
						.orderStatus(OrderStatus.CREATED)
						.productItemList(productItemList)
						.build();
				orderRepository.save(order);
				productItemList.clear();
			});



		};
	}
}
