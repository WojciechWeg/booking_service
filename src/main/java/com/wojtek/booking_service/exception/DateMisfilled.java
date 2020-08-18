package com.wojtek.booking_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateMisfilled extends RuntimeException {

    public DateMisfilled(String s) {
        super(s);
    }
}
