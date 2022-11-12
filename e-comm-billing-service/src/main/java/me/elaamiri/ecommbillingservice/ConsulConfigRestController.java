package me.elaamiri.ecommbillingservice;

import lombok.AllArgsConstructor;
import me.elaamiri.ecommbillingservice.configuration.ConsulConfig;
import me.elaamiri.ecommbillingservice.configuration.VaultConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ConsulConfigRestController {
    private ConsulConfig consulConfig; // injected using Constructor
    private VaultConfig vaultConfig;
    @GetMapping("/configValues")
    // just to show what we get
    public ConsulConfig getConfigValue(){
        return consulConfig;
    }

    @GetMapping("/vaultSecrets")
    public VaultConfig getVaultConfig(){
        return vaultConfig;
    }
}
