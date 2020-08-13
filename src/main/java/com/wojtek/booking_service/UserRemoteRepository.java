package com.wojtek.booking_service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRemoteRepository {
    public <T> Optional<T> findById(String userLogin) {
        return null;
    }
}
