package com.onlineshopping.userservice.controllers;


import com.onlineshopping.userservice.dtos.*;
import com.onlineshopping.userservice.exceptions.TokenException;
import com.onlineshopping.userservice.exceptions.UserAlreadyExistsException;
import com.onlineshopping.userservice.exceptions.UserDoesNotExistException;
import com.onlineshopping.userservice.models.Token;
import com.onlineshopping.userservice.models.User;
import com.onlineshopping.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenResponseDto Login(@RequestBody LoginRequestDto loginRequestDto) throws UserDoesNotExistException, TokenException {
        //TODO:Check if user exists in DB and if not, return 404
        Token token = this.userService.Login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return MapToTokenResponseDto(token);
    }

    @PostMapping("/signup")
    public SignUpResponseDto signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        User user = this.userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getName());
        return MapToSignUpResponseDto(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader String token) throws TokenException {
        //TODO:Remove token from DB and return 200 or 404 if token not found
        boolean flag = this.userService.logout(token);
        if (flag) {
            return ResponseEntity.status(200).body("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Logout failed! Token not found!");
    }

    @GetMapping("/validate/{token}")
    public ValidateResponseDto validate(@PathVariable String token) throws TokenException {
        User user = this.userService.validate(token);
        return MapToValidateResponseDto(user);
    }

    public SignUpResponseDto MapToSignUpResponseDto(User user) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setEmail(user.getEmail());
        signUpResponseDto.setEmailVerified(user.isEmailVerified());
        signUpResponseDto.setUsername(user.getUsername());
        return signUpResponseDto;
    }

    private TokenResponseDto MapToTokenResponseDto(Token token) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        User user = token.getUser();
        User tokenUser = new User();

        tokenUser.setEmail(user.getEmail());
        tokenUser.setUsername(user.getUsername());
        tokenUser.setEmailVerified(user.isEmailVerified());

        tokenResponseDto.setUser(tokenUser);
        tokenResponseDto.setValue(token.getValue());
        tokenResponseDto.setExpiryDate(token.getExpiryDate());

        return tokenResponseDto;
    }

    private ValidateResponseDto MapToValidateResponseDto(User user) {
        ValidateResponseDto validateResponseDto = new ValidateResponseDto();
        validateResponseDto.setEmail(user.getEmail());
        validateResponseDto.setUserName(user.getUsername());
        validateResponseDto.setEmailVerified(user.isEmailVerified());
        return validateResponseDto;
    }
}
