package com.user_service.user_service.services;

import com.user_service.user_service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String userId);
    //delete
    //update
}
