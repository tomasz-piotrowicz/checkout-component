package com.checkout.controller;

import com.checkout.domain.ProductDto;
import com.checkout.mapper.ProductMapper;
import com.checkout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDto> getAll() {
        return productMapper.mapToProductDtoList(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productMapper.mapToProduct(productDto));
    }
}
