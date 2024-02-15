package com.onlineshopping.productcatalogservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Product extends BaseModel {

    private String description;
    @ManyToOne
    private Category category;
    private Long price;


}
