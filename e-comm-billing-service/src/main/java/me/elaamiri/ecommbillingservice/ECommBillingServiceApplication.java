package me.elaamiri.ecommbillingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.Versioned;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ECommBillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommBillingServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(VaultTemplate vaultTemplate){
		return args -> {
			// Write a secret
			Map<String, String> data = new HashMap<>();
			data.put("password", "Hashi123");
			vaultTemplate.opsForVersionedKeyValue("secret")
					.put("billing-service", data);
			// Read a secret
			Versioned<Map<String, Object>> readSecrets = vaultTemplate
					.opsForVersionedKeyValue("secrets")
					.get("billing-service");

			if (readSecrets != null && readSecrets.hasData()){
				System.out.println(readSecrets.getVersion());
				System.out.println(readSecrets.getData().get("password"));
			}

		};
	}
}
