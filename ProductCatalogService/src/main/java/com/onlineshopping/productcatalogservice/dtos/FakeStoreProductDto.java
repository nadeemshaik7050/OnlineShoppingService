package com.onlineshopping.productcatalogservice.dtos;

import com.onlineshopping.productcatalogservice.models.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private  Long price;
}
