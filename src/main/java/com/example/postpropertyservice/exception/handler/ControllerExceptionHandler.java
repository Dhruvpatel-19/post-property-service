package com.example.postpropertyservice.exception.handler;

import com.example.postpropertyservice.exception.NotAuthenticatedOwner;
import com.example.postpropertyservice.exception.OwnerNotFoundException;
import com.example.postpropertyservice.exception.PropertyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = PropertyNotFoundException.class)
    public ResponseEntity<Object> PropertyNotFoundException(PropertyNotFoundException exception) {
        return new ResponseEntity<>("Property not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OwnerNotFoundException.class)
    public ResponseEntity<Object> OwnerNotFoundException(OwnerNotFoundException exception) {
        return new ResponseEntity<>("Owner not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotAuthenticatedOwner.class)
    public ResponseEntity<Object> notAuthenticatedOwner(NotAuthenticatedOwner exception) {
        return new ResponseEntity<>("Property is owned by another user", HttpStatus.FORBIDDEN);
    }
}