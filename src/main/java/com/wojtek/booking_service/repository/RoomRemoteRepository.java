package com.wojtek.booking_service.repository;

import com.wojtek.booking_service.dto.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@EnableFeignClients
public class RoomRemoteRepository {

    @Autowired
    private RoomServiceClient roomServiceClient;

    public Optional<Room> findById(String roomName) {
        Room room = roomServiceClient.getRoom(roomName);
        return Optional.ofNullable(room);
    }
}