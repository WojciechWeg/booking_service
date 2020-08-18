package com.wojtek.booking_service;



import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
@Service
public class RoomRemoteRepository {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public Optional<Room> findById(String roomName) {
        String getRoomUrl = "http://localhost:8081/room/";
        Room room = restTemplate().getForObject(getRoomUrl + roomName, Room.class);
        return Optional.ofNullable(room);
    }
}
