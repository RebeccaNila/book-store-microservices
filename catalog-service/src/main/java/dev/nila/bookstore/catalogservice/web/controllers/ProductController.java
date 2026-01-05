package dev.nila.bookstore.catalogservice.web.controllers;

import dev.nila.bookstore.catalogservice.domain.PagedResult;
import dev.nila.bookstore.catalogservice.domain.Product;
import dev.nila.bookstore.catalogservice.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
//product controller doesn't need to be public, because spring boot invoke this class nobody does,
// controller class, handler method doesn't need to be public
class ProductController {

    private final ProductService productService;

        @GetMapping
        PagedResult<Product> getProducts(@RequestParam(name="page", defaultValue = "1") int pageNo){
            return productService.getProducts(pageNo);
        }

}
