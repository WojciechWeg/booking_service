package com.wojtek.booking_service.repository;


import com.wojtek.booking_service.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Long> {

    @Query("select rB from RoomBookingEntity rB where rB.dateStart >= ?1 and rB.dateEnd <= ?2")
    List<RoomBookingEntity> getAllBookingsWithIn(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart > ?1")
    List<RoomBookingEntity> getAllBookingsInPast(@Param("dateEndParam") LocalDateTime dateEndParam);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart < ?1")
    List<RoomBookingEntity> getAllBookingsInFuture(@Param("dateStartParam") LocalDateTime dateStartParam);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart >= ?1 and rB.dateEnd <= ?2 and rB.roomName = ?3")
    List<RoomBookingEntity> getAllBookingsWithInDateFrameAndRoom(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam, @Param("roomName") String roomName);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart > ?1 and rB.roomName = ?2 ")
    List<RoomBookingEntity> getAllBookingsInPastAndRoom(LocalDateTime dateEnd, String roomName);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart < ?1 and rB.roomName = ?2")
    List<RoomBookingEntity> getAllBookingsInFutureAndRoom(LocalDateTime dateStart, String roomName);

    @Query("select rB from RoomBookingEntity rB where rB.roomName = ?1")
    List<RoomBookingEntity> getAllBookingsInRoom(String roomName);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart >= ?1 and rB.dateEnd <= ?2 and rB.userLogin = ?3")
    List<RoomBookingEntity> getAllBookingsWithInDateFrameAndUser(@Param("dateStartParam") LocalDateTime dateStartParam, @Param("dateEndParam") LocalDateTime dateEndParam, @Param("roomName") String roomName);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart > ?1 and rB.userLogin = ?2")
    List<RoomBookingEntity> getAllBookingsInPastAndUser(LocalDateTime dateEnd, String login);

    @Query("select rB from RoomBookingEntity rB where rB.dateStart < ?1 and rB.userLogin = ?2")
    List<RoomBookingEntity> getAllBookingsInFutureAndUser(LocalDateTime dateStart, String login);

    @Query("select rB from RoomBookingEntity rB where  rB.userLogin = ?1")
    List<RoomBookingEntity> getAllBookingsForUser(String login);

}
