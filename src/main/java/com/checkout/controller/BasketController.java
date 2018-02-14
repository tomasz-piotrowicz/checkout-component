package com.checkout.controller;

import com.checkout.controller.exception.BasketNotFoundException;
import com.checkout.controller.exception.ProductNotFoundException;
import com.checkout.domain.Basket;
import com.checkout.domain.BasketDto;
import com.checkout.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public Basket addProductToBasket(@RequestBody BasketDto basketDto) throws ProductNotFoundException {
        return basketService.openBasket(basketDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buy/{id}" )
    public void purchaseBasket(@PathVariable Long id) throws BasketNotFoundException {
        basketService.purchaseBasket(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/purchased")
    public List<Basket> getPurchased() {
        return basketService.findByPurchasedBaskets();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Basket> getAllActive() {
        return basketService.findAllActiveBaskets();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public Basket updateBasket(@RequestBody BasketDto basketDto, @PathVariable Long id) throws BasketNotFoundException {
        return basketService.updateBasket(basketDto, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteProduct(@PathVariable Long id) {
        basketService.deleteProduct(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/clear")
    public void clearBasket() {
        basketService.clearBasket();
    }
}
