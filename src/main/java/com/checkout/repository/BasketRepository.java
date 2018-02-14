package com.checkout.repository;

import com.checkout.domain.Basket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketRepository extends CrudRepository<Basket,Long> {
    void removeById(Long id);
    List<Basket> findByStatus(String status);
}
