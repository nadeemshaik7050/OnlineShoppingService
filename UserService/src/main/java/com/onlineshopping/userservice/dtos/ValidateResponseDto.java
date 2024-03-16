package com.onlineshopping.userservice.dtos;

import com.onlineshopping.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private String email;
    private String userName;
    private boolean isEmailVerified;
}
