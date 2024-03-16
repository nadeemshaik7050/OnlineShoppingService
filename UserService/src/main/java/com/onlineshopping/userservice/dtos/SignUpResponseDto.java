package com.onlineshopping.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private String username;
    private String email;
    private boolean isEmailVerified;
}
