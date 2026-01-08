package dev.nila.orderservice.domain;

import dev.nila.orderservice.clients.catalog.Product;
import dev.nila.orderservice.clients.catalog.ProductServiceClient;
import dev.nila.orderservice.domain.models.CreateOrderRequest;
import dev.nila.orderservice.domain.models.OrderItem;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final ProductServiceClient client;

    void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();
        for (OrderItem item : items) {

            Product product = client.getProductByCode(item.code())
                    // return option of empty
                    .orElseThrow(() -> new InvalidOrderException("Invalid Product code:" + item.code()));
            System.out.println(product.price());
            // check item price and product price are same if not throw error
            if (item.price().compareTo(product.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price:{}, received price:{}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
