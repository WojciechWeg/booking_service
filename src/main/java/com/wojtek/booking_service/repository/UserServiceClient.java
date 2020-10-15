package com.wojtek.booking_service.repository;

import com.wojtek.booking_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-service-eureka-client")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userLogin}")
    User getUser(@PathVariable("userLogin") String userLogin);

}
