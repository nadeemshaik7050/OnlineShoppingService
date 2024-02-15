package com.onlineshopping.productcatalogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;
    private String title;

}
