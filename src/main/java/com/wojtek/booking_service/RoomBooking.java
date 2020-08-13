package com.wojtek.booking_service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

    @NotNull
    @NotEmpty
    @NotBlank
    String roomName;

    @NotNull
    @NotEmpty
    @NotBlank
    String userLogin;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateStart;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateEnd;

}