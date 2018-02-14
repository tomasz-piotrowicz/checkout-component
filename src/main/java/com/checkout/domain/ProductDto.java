package com.checkout.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Integer unit;
    private Double specialPrice;
    private Integer specialPriceCondition;
}
