package com.checkout.repository;

import com.checkout.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();
    @Override
    Optional<Product> findById(Long id);
}
