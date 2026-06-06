package com.app.shopping.service.product;

import com.app.shopping.dto.ProductRequest;
import com.app.shopping.exceptions.ProductNotFoundException;
import com.app.shopping.model.Category;
import com.app.shopping.model.Product;
import com.app.shopping.repository.CategoryRepository;
import com.app.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductRequest request) {
        Product product = new Product();
        apply(product, request);
        return productRepository.save(product);
    }

    private void apply(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setInventory(request.inventory());
        product.setDescription(request.description());
        product.setCategory(resolveCategory(request.category()));
    }

    private Category resolveCategory(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return categoryRepository.findByName(name).orElseGet(() -> {
            Category category = new Category();
            category.setName(name);
            return categoryRepository.save(category);
        });
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.findById(productId).
                ifPresentOrElse(productRepository::delete, () -> { throw new ProductNotFoundException("Product Not found"); });
    }

    @Override
    public Product updateProduct(ProductRequest request, Long productId) {
        Product product = getProductById(productId);
        apply(product, request);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
