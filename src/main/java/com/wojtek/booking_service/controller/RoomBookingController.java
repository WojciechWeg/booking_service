package com.wojtek.booking_service.controller;

import com.wojtek.booking_service.dto.RoomBookingNameSurname;
import com.wojtek.booking_service.dto.RoomBooking;
import com.wojtek.booking_service.service.RoomBookingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(RoomBookingController.BASE_URL)
public class RoomBookingController {

    public static final String BASE_URL = "bookRoom";

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PutMapping()
    public void newBooking(@Valid @RequestBody RoomBooking roomBooking) {
        roomBookingService.bookTheRoom(roomBooking);
    }

    @GetMapping()
    public List<RoomBookingNameSurname> getBookingScheduleForAllRooms(@RequestParam String dateStart, @RequestParam String dateEnd){

        if(dateEnd.equals("") && dateStart.equals(""))
            return roomBookingService.getBookingScheduleForAllRooms(null, null);
        if(dateStart.equals(""))
            return roomBookingService.getBookingScheduleForAllRooms(null, LocalDateTime.parse(dateEnd));
        if(dateEnd.equals(""))
            return roomBookingService.getBookingScheduleForAllRooms(LocalDateTime.parse(dateStart), null);
        return roomBookingService.getBookingScheduleForAllRooms(LocalDateTime.parse(dateStart), LocalDateTime.parse(dateEnd));

    }

    @GetMapping({"/givenRoom"})
    public List<RoomBookingNameSurname> getBookingScheduleForGivenRoom(@RequestParam String dateStart, @RequestParam String dateEnd, @RequestParam String roomName){

        if(dateEnd.equals("") && dateStart.equals(""))
            return roomBookingService.getBookingScheduleForGivenRoom(null, null,roomName);
        if(dateStart.equals(""))
            return roomBookingService.getBookingScheduleForGivenRoom(null, LocalDateTime.parse(dateEnd),roomName);
        if(dateEnd.equals(""))
            return roomBookingService.getBookingScheduleForGivenRoom(LocalDateTime.parse(dateStart), null,roomName);
        return roomBookingService.getBookingScheduleForGivenRoom(LocalDateTime.parse(dateStart), LocalDateTime.parse(dateEnd),roomName);

    }

    @GetMapping({"/givenUser"})
    public List<RoomBookingNameSurname> getBookingScheduleForGivenUser(@RequestParam String dateStart, @RequestParam String dateEnd, @RequestParam String userLogin){

        if(dateEnd.equals("") && dateStart.equals(""))
            return roomBookingService.getBookingScheduleForGivenUser(null, null,userLogin);
        if(dateStart.equals(""))
            return roomBookingService.getBookingScheduleForGivenUser(null, LocalDateTime.parse(dateEnd),userLogin);
        if(dateEnd.equals(""))
            return roomBookingService.getBookingScheduleForGivenUser(LocalDateTime.parse(dateStart), null,userLogin);
        return roomBookingService.getBookingScheduleForGivenUser(LocalDateTime.parse(dateStart), LocalDateTime.parse(dateEnd),userLogin);

    }

}
