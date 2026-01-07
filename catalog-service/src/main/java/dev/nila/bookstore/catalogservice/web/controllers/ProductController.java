package dev.nila.bookstore.catalogservice.web.controllers;

import dev.nila.bookstore.catalogservice.domain.PagedResult;
import dev.nila.bookstore.catalogservice.domain.Product;
import dev.nila.bookstore.catalogservice.domain.ProductNotFoundException;
import dev.nila.bookstore.catalogservice.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
// product controller doesn't need to be public, because spring boot invoke this class nobody does,
// controller class, handler method doesn't need to be public
class ProductController {

    private final ProductService productService;

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
