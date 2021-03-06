package com.checkout.service;

import com.checkout.domain.Product;
import com.checkout.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }
}
