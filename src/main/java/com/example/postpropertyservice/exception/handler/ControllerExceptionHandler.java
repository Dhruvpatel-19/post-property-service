package com.example.postpropertyservice.exception.handler;

import com.example.postpropertyservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = PropertyNotFoundException.class)
    public ResponseEntity<Object> propertyNotFoundException(PropertyNotFoundException exception) {
        return new ResponseEntity<>("Property not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OwnerNotFoundException.class)
    public ResponseEntity<Object> ownerNotFoundException(OwnerNotFoundException exception) {
        return new ResponseEntity<>("Owner not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotAuthenticatedOwner.class)
    public ResponseEntity<Object> notAuthenticatedOwner(NotAuthenticatedOwner exception) {
        return new ResponseEntity<>("Property is owned by another user", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = JwtTokenExpiredException.class)
    public ResponseEntity<Object> jwtTokenExpiredException(JwtTokenExpiredException exception) {
        return new ResponseEntity<>("Jwt Token has been expired", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtSignatureException.class)
    public ResponseEntity<Object> jwtSignatureException(JwtSignatureException exception) {
        return new ResponseEntity<>("Jwt token is not properly formatted", HttpStatus.BAD_REQUEST);
    }
}