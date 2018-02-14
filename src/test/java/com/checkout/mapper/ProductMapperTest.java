package com.checkout.mapper;

import com.checkout.domain.Product;
import com.checkout.domain.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    @InjectMocks
    private ProductMapper productMapper;

    @Test
    public void testMapToProduct() {
        //Given
        ProductDto productDto = new ProductDto(1L, "Pegasus2", 10.0, 100, 4.0, 2);
        //When
        Product testedProduct = productMapper.mapToProduct(productDto);
        //Then
        assertEquals(productDto.getName(), testedProduct.getName());
    }

    @Test
    public void testMapToProductDtoList() {
        //Given
        List<Product> productList = new ArrayList<>();
        Product product = new Product(1L, "Pegasus2", 10.0, 100, 4.0, 2);
        productList.add(product);
        //When
        List<ProductDto> testedList = productMapper.mapToProductDtoList(productList);
        //Then
        assertEquals(1, testedList.size());
    }
}
