package com.tecsup.MiniShop.service;

import com.tecsup.MiniShop.model.Product;
import com.tecsup.MiniShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final String ERROR_PRICE = "El precio debe ser mayor a cero";
    private static final String ERROR_STOCK = "El stock no puede ser negativo";
    private static final String ERROR_NAME = "El nombre no puede estar vacío";
    private static final String ERROR_PRODUCT_NOT_FOUND = "Producto no encontrado con id: ";

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(ERROR_PRODUCT_NOT_FOUND + id));
    }

    private void validateProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException(ERROR_PRICE);
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException(ERROR_STOCK);
        }

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_NAME);
        }
    }
}