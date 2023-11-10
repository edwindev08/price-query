package com.edwindev08.price.rest.adviser;

import com.edwindev08.price.adapter.entity.ApiError;
import com.edwindev08.price.model.exception.PriceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger log;

    public ExceptionControllerAdvice() {
        this.log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ApiError> priceNotFoundException(PriceNotFoundException exception) {
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> badRequestException(Exception exception) {
        this.log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    private ResponseEntity<ApiError> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new ApiError(LocalDateTime.now(), httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
    }
}
