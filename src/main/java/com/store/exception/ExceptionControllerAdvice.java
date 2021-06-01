package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> resourceNotFound(RuntimeException ex) {
        ExceptionResponse response = buildExceptionResponse(ex, "NOT_FOUND");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ResourceAlreadyExist.class})
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(RuntimeException ex) {
        ExceptionResponse response = buildExceptionResponse(ex, "CONFLICT");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ExceptionResponse buildExceptionResponse(Exception ex, String errorCode) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(new Date());
        return response;
    }
}
