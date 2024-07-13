package com.onlineshoppingservice.orderservice.dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Long price;
    private int quantity;
}
