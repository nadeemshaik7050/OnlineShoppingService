package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String username;
    private String hashedPassword;
    private String email;
    private boolean isEmailVerified;
    @ManyToMany
    private List<Role> roles;

}
