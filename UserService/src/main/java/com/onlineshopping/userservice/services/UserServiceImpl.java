package com.onlineshopping.userservice.services;

import com.onlineshopping.userservice.exceptions.TokenException;
import com.onlineshopping.userservice.exceptions.UserAlreadyExistsException;
import com.onlineshopping.userservice.exceptions.UserDoesNotExistException;
import com.onlineshopping.userservice.models.Token;
import com.onlineshopping.userservice.models.User;
import com.onlineshopping.userservice.repository.TokenRepo;
import com.onlineshopping.userservice.repository.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service("userService")
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public Token Login(String email, String password) throws UserDoesNotExistException, TokenException {
        Optional<User> userOptional = userRepo.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserDoesNotExistException("User does not exist with email: " + email);
        }
        User user = userOptional.get();
        if (bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            LocalDate date = LocalDate.now();
            LocalDate oneDayLater = date.plusDays(1);
            Date expiryDate = Date.from(oneDayLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Token token = Token.builder()
                    .user(user)
                    .value(RandomStringUtils.randomAlphabetic(128))
                    .expiryDate(expiryDate)
                    .build();
            tokenRepo.save(token);
            return token;
        } else {
            throw new TokenException("Invalid credentials");
        }


    }

    @Override
    public User signUp(String email, String password, String name) throws UserAlreadyExistsException {
        boolean isUserPresent = userRepo.findUserbyEmail(email);
        if (isUserPresent) {
            throw new UserAlreadyExistsException("User already exists with email: " + email);
        }
        User user = User.builder()
                .email(email)
                .username(name)
                .hashedPassword(bCryptPasswordEncoder.encode(password))
                .build();
        return userRepo.save(user);
    }

    @Override
    public boolean logout(String tokenValue) throws TokenException {
        Optional<Token> tokenOptional = tokenRepo.findByValue(tokenValue, false);
        if (tokenOptional.isEmpty()) {
            throw new TokenException("Token not found");
        }
        Token token = tokenOptional.get();
        token.setDeleted(true);
        tokenRepo.save(token);
        return true;
    }

    @Override
    public User validate(String token) throws TokenException {
        Optional<Token> tokenOptional = tokenRepo.findByValue(token, false);
        if (tokenOptional.isEmpty()) {
            throw new TokenException("Token not found");
        }
        Token token1 = tokenOptional.get();
        Date currentDate = new Date();
        Date tokenExpiryDate = token1.getExpiryDate();
        if (currentDate.after(tokenExpiryDate) || token1.isDeleted()) {
            throw new TokenException("Token is invalid");
        }
        return token1.getUser();
    }
}
