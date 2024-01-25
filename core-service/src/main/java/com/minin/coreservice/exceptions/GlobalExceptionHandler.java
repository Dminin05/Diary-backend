package com.minin.coreservice.exceptions;

import com.minin.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleAlreadyExistsException(AlreadyExistsException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleConflictException(ConflictException ex) {
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), ex.getMessage()), HttpStatus.CONFLICT);
    }

}
