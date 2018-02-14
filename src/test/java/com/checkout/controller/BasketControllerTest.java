package com.checkout.controller;

import com.checkout.domain.Basket;
import com.checkout.domain.BasketDto;
import com.checkout.domain.Product;
import com.checkout.service.BasketService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BasketController.class)
public class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BasketService basketService;

    @Test
    public void shouldCreateBasket() throws Exception {
        //Given
        BasketDto basketDto = new BasketDto(1L, 2, "not_purchased");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(basketDto);

        //When & Then
        mockMvc.perform(post("/basket").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldPurchaseBasket() throws Exception {
        //Given & When & Then
        mockMvc.perform(post("/basket/buy/{id}", 1l).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetPurchased() throws Exception {
        //Given
        List<Basket> basketList = new ArrayList<>();
        Product product = new Product(1L, "Pegasus2", 10.0, 100, 4.0, 2);
        Basket basket = new Basket(1L, product, 2, 20.0, new Date(), "PURCHASED");
        basketList.add(basket);

        when(basketService.findByPurchasedBaskets()).thenReturn(basketList);

        //When & Then
        mockMvc.perform(get("/basket/purchased").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldGetActiveBasket() throws Exception {
        //Given
        List<Basket> basketList = new ArrayList<>();
        Product product = new Product(1L, "Pegasus2", 10.0, 100, 4.0, 2);
        Basket basket = new Basket(1L, product, 2, 20.0, new Date(), "NOT_PURCHASED");
        basketList.add(basket);

        when(basketService.findAllActiveBaskets()).thenReturn(basketList);

        //When & Then
        mockMvc.perform(get("/basket").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldUpdateBasket() throws Exception {
        BasketDto basketDto = new BasketDto(1L, 2, "not_purchased");

        when(basketService.updateBasket(basketDto, 1L)).thenCallRealMethod();

        Gson gson = new Gson();
        String jsonContent = gson.toJson(basketDto);

        mockMvc.perform(put("/basket/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteBasket() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/basket/{id}", 1l).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldClearBasket() throws Exception {
        //Given & When & Then
        mockMvc.perform(delete("/basket/clear").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
