package com.usbank.user.accounts.loanmanager.advice;

import com.usbank.user.accounts.loanmanager.exception.ApplicationNotFoundException;
import com.usbank.user.accounts.loanmanager.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ApplicationNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleApplicationNotFoundException(ApplicationNotFoundException exception) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), "Failed to find the account", HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


}
