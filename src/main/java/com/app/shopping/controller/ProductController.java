package com.app.shopping.controller;

import com.app.shopping.dto.ProductRequest;
import com.app.shopping.model.Product;
import com.app.shopping.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public List<Product> getAll(
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String name
    ) {
        if (category != null && brand != null) {
            return productService.getProductsByCategoryAndBrand(category, brand);
        }
        if (category != null) return productService.getProductsByCategory(category);
        if (brand != null) return productService.getProductsByBrand(brand);
        if (name != null) return productService.getProductsByName(name);
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody ProductRequest request) {
        return productService.updateProduct(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
