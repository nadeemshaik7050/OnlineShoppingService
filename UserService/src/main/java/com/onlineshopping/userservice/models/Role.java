package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Role extends BaseModel{
    private String name;
}
