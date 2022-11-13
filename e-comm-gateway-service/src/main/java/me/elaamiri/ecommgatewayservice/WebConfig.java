package me.elaamiri.ecommgatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.config.CorsRegistration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

//@Configuration
//@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    @Autowired
    private Environment environment;
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){

        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST" ,"PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true).maxAge(3600);


        //String urls = environment.getProperty("cors.urls"); // set of string separated by , commas
        //CorsRegistration corsRegistration = corsRegistry.addMapping("/**");

        /*
        String[] corsUrls = urls.split(",");
        for (String corsUrl : corsUrls){
            System.out.println(corsUrl);
            corsRegistration.allowedOrigins(corsUrl.trim())
                    .allowedHeaders("*")
                    .allowedMethods("GET","POST", "PUT", "DELETE" );
        }
        */
    }

}
