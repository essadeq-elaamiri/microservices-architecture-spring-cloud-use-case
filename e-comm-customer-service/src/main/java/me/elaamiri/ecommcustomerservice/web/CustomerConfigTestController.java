package me.elaamiri.ecommcustomerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerConfigTestController {
    // Injecting the configuration values
    //@Value("${cusomer.params.c1}")
    private String c1="val"; // dev param
    @Value("${global.parmas.globlaName}")
    private String globalName; // dev param

    @GetMapping("/configParams")
    Map<String, String> getConfigParams(){
        return Map.of("c1", c1, "globalName", globalName); //java 17
    }
}
