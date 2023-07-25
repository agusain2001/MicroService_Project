package com.user.Service.services;

import com.user.Service.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();

    User getUser(String UserId);

    void deleteAllUser();

    void deleteUser(String User);

    User updateUser(User u);

}
