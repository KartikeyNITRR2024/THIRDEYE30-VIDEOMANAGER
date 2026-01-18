package com.thirdeye3.video.manager.exceptions.handler;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<Void>> handleUserNotFound(ResourceNotFoundException ex) {
        Response<Void> response = new Response<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private <T> ResponseEntity<Response<T>> buildResponse(
            boolean success, int errorCode, String errorMessage, T body) {
        return ResponseEntity
                .status(errorCode)
                .body(new Response<>(success, errorCode, errorMessage, body));
    }
}
