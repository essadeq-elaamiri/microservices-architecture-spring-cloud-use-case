package me.elaamiri.ecommbillingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConsulConfigRestController {
    // Inject the configuration value in a variable.
    // the same name as that in config service
    @Value("${token.accessTokenTimeout}")
    private long accessTokenTimeout;

    @GetMapping("/configValues")
    // just to show what we get
    public Map<String, Object> getConfigValue(){
        return Map.of("accessTokenTimeout", accessTokenTimeout);
    }
}
