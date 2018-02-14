package com.checkout.mapper;

import com.checkout.domain.Product;
import com.checkout.domain.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getUnit(),
                productDto.getSpecialPrice(),
                productDto.getSpecialPriceCondition()
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), p.getUnit(), p.getSpecialPrice(), p.getSpecialPriceCondition()))
                .collect(Collectors.toList());
    }
}
