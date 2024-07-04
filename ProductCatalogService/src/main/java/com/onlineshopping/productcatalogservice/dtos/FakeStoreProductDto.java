package com.onlineshopping.productcatalogservice.dtos;

import com.onlineshopping.productcatalogservice.models.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class FakeStoreProductDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private String category;
    private  Long price;
}
