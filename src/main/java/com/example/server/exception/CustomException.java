package com.example.server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
