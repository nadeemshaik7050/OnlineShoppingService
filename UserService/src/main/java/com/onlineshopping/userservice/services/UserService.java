package com.onlineshopping.userservice.services;

import com.onlineshopping.userservice.exceptions.TokenException;
import com.onlineshopping.userservice.exceptions.UserAlreadyExistsException;
import com.onlineshopping.userservice.exceptions.UserDoesNotExistException;
import com.onlineshopping.userservice.models.Token;
import com.onlineshopping.userservice.models.User;

public interface UserService {
    Token Login(String email, String password) throws UserDoesNotExistException, TokenException;

    User signUp(String email, String password, String name) throws UserAlreadyExistsException;

    boolean logout(String tokenValue) throws TokenException;

    User validate(String token) throws TokenException;
}
