package com.app.shopping.service.product;

import com.app.shopping.model.Product;

import java.util.List;

public interface IProductService {
    void addProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long productId);
    void updateProduct(Product product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
