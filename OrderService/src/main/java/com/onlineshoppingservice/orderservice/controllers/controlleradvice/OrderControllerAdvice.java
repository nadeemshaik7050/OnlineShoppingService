package com.onlineshoppingservice.orderservice.controllers.controlleradvice;

import com.onlineshoppingservice.orderservice.dtos.ExceptionDto;
import com.onlineshoppingservice.orderservice.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(OrderNotFoundException e) {
        ExceptionDto exceptionDto = ExceptionDto.builder().status("Failed").message(e.getMessage()).build();
        ResponseEntity<ExceptionDto> responseEntity=new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
