package dev.nila.bookstore.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods("*") // * means all
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(false);
    }
}
