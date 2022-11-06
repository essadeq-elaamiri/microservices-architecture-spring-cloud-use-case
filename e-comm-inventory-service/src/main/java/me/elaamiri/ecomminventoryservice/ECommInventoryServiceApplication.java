package me.elaamiri.ecomminventoryservice;

import me.elaamiri.ecomminventoryservice.entities.Product;
import me.elaamiri.ecomminventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ECommInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommInventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			List.of("IMACX15", "Lenovo X14", "Infinix142", "R74", "XLa77").forEach(s -> {
				Product product = Product.builder()
						.name(s)
						.price((new Random()).nextDouble(500, 5000))
						.quantity((new Random()).nextInt(12,55))
						.build();
				productRepository.save(product);
			});
		};
	}
}
