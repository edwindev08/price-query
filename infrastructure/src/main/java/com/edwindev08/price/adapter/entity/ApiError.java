package com.edwindev08.price.adapter.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiError(
        @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
        LocalDateTime timeStamp,
        int httpStatusCode,
        HttpStatus httpStatus,
        String reason,
        String message) {
}
