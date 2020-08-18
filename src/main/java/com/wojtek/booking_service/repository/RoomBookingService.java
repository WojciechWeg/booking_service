package com.wojtek.booking_service.repository;

import com.wojtek.booking_service.dto.RoomBookingNameSurname;
import com.wojtek.booking_service.dto.User;
import com.wojtek.booking_service.dto.RoomBooking;
import com.wojtek.booking_service.entity.RoomBookingEntity;
import com.wojtek.booking_service.exception.DateMisfilled;
import com.wojtek.booking_service.exception.ResourceNotFoundException;
import com.wojtek.booking_service.exception.RoomIsOccupiedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;
    private final RoomRemoteRepository roomRemoteRepository;
    private final UserRemoteRepository userRemoteRepository;

    public RoomBookingService(RoomBookingRepository roomBookingRepository, RoomRemoteRepository roomRepository, UserRemoteRepository userRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.roomRemoteRepository = roomRepository;
        this.userRemoteRepository = userRepository;
    }

    public void bookTheRoom(RoomBooking roomBooking) {

        dateCheck(roomBooking.getDateStart(),roomBooking.getDateEnd());

        roomRemoteRepository.findById(roomBooking.getRoomName()).orElseThrow(() ->  new ResourceNotFoundException("No such room."));

        userRemoteRepository.findById(roomBooking.getUserLogin()).orElseThrow(() ->  new ResourceNotFoundException("No such user."));

        List<RoomBookingEntity> roomBookingEntityList =
                roomBookingRepository.getAllBookingsWithInDateFrameAndRoom(roomBooking.getDateStart(),roomBooking.getDateEnd(),roomBooking.getRoomName());

        if(!roomBookingEntityList.isEmpty())
            throw new RoomIsOccupiedException("Room is occupied at this time.");

        RoomBookingEntity roomBookingEntity = new RoomBookingEntity();
        roomBookingEntity.setRoomName(roomBooking.getRoomName());
        roomBookingEntity.setUserLogin(roomBooking.getUserLogin());
        roomBookingEntity.setDateStart(roomBooking.getDateStart());
        roomBookingEntity.setDateEnd(roomBooking.getDateEnd());

        roomBookingRepository.save(roomBookingEntity);
    }


    public List<RoomBookingNameSurname> getBookingScheduleForAllRooms(LocalDateTime dateStart, LocalDateTime dateEnd) {
        dateCheck( dateStart,  dateEnd);

        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateStart!=null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsWithIn(dateStart,dateEnd);

        if(dateStart==null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInPast(dateEnd);

        if (dateStart!=null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInFuture(dateStart);

        if(dateStart==null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.findAll();


        return mapBookings(roomBookingEntityList);

    }

    public List<RoomBookingNameSurname> getBookingScheduleForGivenRoom(LocalDateTime dateStart, LocalDateTime dateEnd, String roomName) {
        dateCheck( dateStart,  dateEnd);
        roomRemoteRepository.findById(roomName).orElseThrow(()-> new ResourceNotFoundException("No such room"));


        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateStart!=null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsWithInDateFrameAndRoom(dateStart,dateEnd,roomName);

        if(dateStart==null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInPastAndRoom(dateEnd,roomName);

        if (dateStart!=null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInFutureAndRoom(dateStart,roomName);

        if(dateStart==null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInRoom(roomName);


        return mapBookings(roomBookingEntityList);

    }

    public List<RoomBookingNameSurname> getBookingScheduleForGivenUser(LocalDateTime dateStart, LocalDateTime dateEnd, String userLogin) {
        dateCheck( dateStart,  dateEnd);

        userRemoteRepository.findById(userLogin).orElseThrow(()-> new ResourceNotFoundException("No such user"));

        List<RoomBookingEntity> roomBookingEntityList = new LinkedList<>();

        if(dateStart!=null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsWithInDateFrameAndUser(dateStart,dateEnd,userLogin);

        if(dateStart==null && dateEnd!=null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInPastAndUser(dateEnd,userLogin);

        if (dateStart!=null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsInFutureAndUser(dateStart,userLogin);

        if(dateStart==null && dateEnd==null)
            roomBookingEntityList = roomBookingRepository.getAllBookingsForUser(userLogin);


        return mapBookings(roomBookingEntityList);

    }

    private List<RoomBookingNameSurname> mapBookings(List<RoomBookingEntity> roomBookingEntityList) {
        List<RoomBookingNameSurname> roomBookingNameSurnameList = new LinkedList<>();
        for (RoomBookingEntity item : roomBookingEntityList) {

            RoomBookingNameSurname roomBookingNameSurname = new RoomBookingNameSurname();

            User userEntity = userRemoteRepository.findById(item.getUserLogin()).orElseThrow(() -> new ResourceNotFoundException("There is no such a user."));

            roomBookingNameSurname.setUserName(userEntity.getName());
            roomBookingNameSurname.setUserSurname(userEntity.getSurname());
            roomBookingNameSurname.setDateStart(item.getDateStart());
            roomBookingNameSurname.setDateEnd(item.getDateEnd());
            roomBookingNameSurname.setRoomName(item.getRoomName());

            roomBookingNameSurnameList.add(roomBookingNameSurname);

        }

        return roomBookingNameSurnameList;
    }

    private boolean dateCheck(LocalDateTime dateStart, LocalDateTime dateEnd) {

        if(dateStart==null || dateEnd == null)
            return false;

        if(dateEnd.isBefore(LocalDateTime.now()) || dateStart.isBefore(LocalDateTime.now()))
            throw new DateMisfilled("You cant book room in the past");

        if(dateEnd.equals(dateStart))
            throw new DateMisfilled("End date is the same like start date.");

        if(dateEnd.isBefore(dateStart))
            throw new DateMisfilled("End date can't be before start date.");

        return true;

    }


}
