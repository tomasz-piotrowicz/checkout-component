package com.checkout.controller;

import com.checkout.domain.ProductDto;
import com.checkout.mapper.ProductMapper;
import com.checkout.service.ProductService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductMapper mapper;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldGetAllProducts() throws Exception {
        //Given
        List<ProductDto> productList = new ArrayList<>();
        productList.add(new ProductDto(1L, "Pegasus2", 10.0, 100, 4.0, 2));

        when(mapper.mapToProductDtoList(ArgumentMatchers.anyList())).thenReturn(productList);
        //When & Then
        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Pegasus2")))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andExpect(jsonPath("$[0].unit", is(100)))
                .andExpect(jsonPath("$[0].specialPrice", is(4.0)))
                .andExpect(jsonPath("$[0].specialPriceCondition", is(2)));
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        //Given
        ProductDto productDto = new ProductDto(1L, "Polystation", 100.0, 40, 70.0, 3);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(productDto);

        //When & Then
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
