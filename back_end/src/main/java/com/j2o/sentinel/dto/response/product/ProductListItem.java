package com.j2o.sentinel.dto.response.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductListItem {
    private int id;
    private String name;
    private double price;
}
