package dev.nila.bookstore.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

    public static void main(String[] args) {
        SpringApplication.from(CatalogServiceApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
