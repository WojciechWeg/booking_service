package com.wojtek.booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserRemoteRepository {

    @Autowired
    private ApplicationContext appContext;

    public Optional<User> findById(String userLogin) {
        String getRoomUrl = "http://localhost:8082/user/";
        RestTemplate restTemplate =  appContext.getBean("restTemplate", RestTemplate.class);
        User user = restTemplate.getForObject(getRoomUrl + userLogin, User.class);
        return Optional.ofNullable(user);
    }
}
