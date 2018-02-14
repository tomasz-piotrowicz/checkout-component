package com.checkout.service;

import com.checkout.controller.exception.BasketNotFoundException;
import com.checkout.controller.exception.ProductNotFoundException;
import com.checkout.domain.Basket;
import com.checkout.domain.BasketDto;
import com.checkout.domain.Product;
import com.checkout.repository.BasketRepository;
import com.checkout.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BasketService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    public Basket openBasket(BasketDto basketDto) throws ProductNotFoundException {
        Basket basket = new Basket();
        Product product = productRepository.findById(basketDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        basket.setProduct(product);
        basket.setStatus(basketDto.getStatus());
        basket.setOrderDate(new Date());
        basket.setStock(basketDto.getStock());
        if (basketDto.getStock().equals(product.getSpecialPriceCondition())) {
            basket.setCost(product.getSpecialPrice() * (double)basketDto.getStock());
        } else {
            basket.setCost(product.getPrice() * (double)basketDto.getStock());
        }
        return basketRepository.save(basket);
    }

    public Basket updateBasket(BasketDto basketDto, Long id) throws BasketNotFoundException {
        Basket basketForUpdate = basketRepository.findById(id).orElseThrow(BasketNotFoundException::new);
        basketForUpdate.setStock(basketDto.getStock());
        basketForUpdate.setCost(basketForUpdate.getProduct().getPrice() * (double)basketDto.getStock());
        return basketRepository.save(basketForUpdate);
    }

    public void deleteProduct(Long id){
        basketRepository.removeById(id);
    }


    public void clearBasket() {
        basketRepository.deleteAll();
    }

    public void purchaseBasket(Long id) throws BasketNotFoundException {
        Basket basket = basketRepository.findById(id).orElseThrow(BasketNotFoundException::new);
        basket.setStatus("PURCHASED");
        basketRepository.save(basket);
    }

    public List<Basket> findAllActiveBaskets() {
        return basketRepository.findByStatus("NOT_PURCHASED");
    }

    public List<Basket> findByPurchasedBaskets() {
        return basketRepository.findByStatus("PURCHASED");
    }
}
