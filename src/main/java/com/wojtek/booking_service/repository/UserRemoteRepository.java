package com.wojtek.booking_service.repository;

import com.wojtek.booking_service.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRemoteRepository {

    @Autowired
    private UserServiceClient userServiceClient;

    public Optional<User> findById(String userLogin) {
        User user = userServiceClient.getUser(userLogin);
        return Optional.ofNullable(user);
    }

}
