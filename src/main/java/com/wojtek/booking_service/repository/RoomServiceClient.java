package com.wojtek.booking_service.repository;

import com.wojtek.booking_service.dto.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("room-service-eureka-client")
public interface RoomServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/room/{roomName}")
    Room getRoom(@PathVariable("roomName") String roomName);

}