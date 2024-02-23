package com.onlineshopping.productcatalogservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

}
