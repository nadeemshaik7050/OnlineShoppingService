package com.onlineshopping.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends BaseModel{
    private String username;
    private String hashedPassword;
    private String email;
    private boolean isEmailVerified;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
