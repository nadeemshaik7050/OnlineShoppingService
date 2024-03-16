package com.onlineshopping.userservice.dtos;

import com.onlineshopping.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TokenResponseDto {
    private String value;
    private User user;
    private Date expiryDate;
}
