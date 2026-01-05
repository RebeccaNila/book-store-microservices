package dev.nila.bookstore.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
//@EnableAutoConfiguration(ApplicationProperties.class)
@ConfigurationPropertiesScan // automatically scan the root package for ConfigurationProperties class annotation
public class CatalogServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CatalogServiceApplication.class, args);
        System.out.println("It's running");
    }
}
