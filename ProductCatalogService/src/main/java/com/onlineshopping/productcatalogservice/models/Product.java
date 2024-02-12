package com.onlineshopping.productcatalogservice.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Product extends BaseModel {

    private String description;
    private Category category;
    private Long price;

}
