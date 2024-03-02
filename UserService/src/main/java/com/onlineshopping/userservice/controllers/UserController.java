package com.onlineshopping.userservice.controllers;


import com.onlineshopping.userservice.models.Token;
import com.onlineshopping.userservice.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    public Token Login(){
        //TODO:Check if user exists in DB and if not, return 404
        return new Token();
    }

    public User signUp(){
        //TODO:Store user in DB
        return new User();
    }

    public ResponseEntity<Void> logout(){
        //TODO:Remove token from DB and return 200 or 404 if token not found
        return ResponseEntity.ok().build();
    }
}
