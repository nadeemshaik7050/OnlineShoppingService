package com.onlineshopping.userservice.controllers.controllerAdvice;

import com.onlineshopping.userservice.dtos.ExceptionDto;
import com.onlineshopping.userservice.exceptions.TokenException;
import com.onlineshopping.userservice.exceptions.UserAlreadyExistsException;
import com.onlineshopping.userservice.exceptions.UserDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ExceptionDto> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        ExceptionDto exceptionDto = ExceptionDto.builder().status("Failed").message(e.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    private ResponseEntity<ExceptionDto> handleUserDoesNotExistException(UserDoesNotExistException e) {
        ExceptionDto exceptionDto = ExceptionDto.builder().status("Failed").message(e.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenException.class)
    private ResponseEntity<ExceptionDto> handleTokenException(TokenException e) {
        ExceptionDto exceptionDto = ExceptionDto.builder().status("Failed").message(e.getMessage()).build();
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
