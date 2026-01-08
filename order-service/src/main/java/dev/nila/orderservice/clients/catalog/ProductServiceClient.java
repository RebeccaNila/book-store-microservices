package dev.nila.orderservice.clients.catalog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;
    // what we should notice here is if we perform both circuitbreaker and retry, you shouldn't add fallbackMethod in
    // circuritbreaker
    // if we add, it will be work as normal response and then retry won't work as we expected
    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallback")
    // if we want to use retry we shouldn't use retry
    // if we use try catch retry will understand it is not method execution
    // if we don't set properties it will try default 3 times
    public Optional<Product> getProductByCode(String code) {
        log.info("Fetching product for code: {}", code);
        var product =
                restClient.get().uri("/api/products/{code}", code).retrieve().body(Product.class);
        return Optional.ofNullable(product);
    }

    // what we can do here is cache the previous result and if fallback you can return the cache value
    Optional<Product> getProductByCodeFallback(String code, Throwable t) {
        log.info("catalog-service get product by code fallback: code:{}, Error: {} ", code, t.getMessage());
        return Optional.empty();
    }
}
