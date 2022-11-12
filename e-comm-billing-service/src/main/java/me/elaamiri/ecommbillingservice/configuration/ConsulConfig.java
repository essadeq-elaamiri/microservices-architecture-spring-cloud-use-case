package me.elaamiri.ecommbillingservice.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
// Now no need to @Value()
@Data
public class ConsulConfig {
    private long accessTokenTimeout; // the same as the key in config service
}
