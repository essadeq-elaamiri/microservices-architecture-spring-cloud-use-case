package me.elaamiri.ecommcustomerservice;

import me.elaamiri.ecommcustomerservice.entities.Customer;
import me.elaamiri.ecommcustomerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ECommCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommCustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			List.of("Essadeq", "hamza", "soukaina").forEach(s -> {
				Customer customer = Customer.builder()
						.name(s)
						.email(String.format("%s@gmail.com", s))
						.build();
				customerRepository.save(customer);
			});
		};
	}
}
