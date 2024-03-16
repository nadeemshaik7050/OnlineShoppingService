package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
public class Role extends BaseModel{
    private String name;
}
