package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseModel{
    private String name;
}
