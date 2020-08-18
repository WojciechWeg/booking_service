package com.wojtek.booking_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RoomIsOccupiedException extends RuntimeException {

    public RoomIsOccupiedException(String s) {
        super(s);
    }
}
