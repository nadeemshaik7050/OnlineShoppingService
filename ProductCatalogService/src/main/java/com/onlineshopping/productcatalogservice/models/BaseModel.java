package com.onlineshopping.productcatalogservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO: change to UUID
    private Long id;
    private String title;

}
