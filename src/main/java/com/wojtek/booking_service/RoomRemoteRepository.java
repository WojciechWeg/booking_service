package com.wojtek.booking_service;


import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoomRemoteRepository {
    public <T> Optional<T> findById(String roomName) {
        return null;
    }
}