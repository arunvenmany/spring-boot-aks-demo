package com.spring.handson.service;

import com.spring.handson.model.User;
import com.spring.handson.model.UserDto;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(String id);

    User findOne(String username);

    User findById(String id);

    UserDto update(UserDto userDto);
}
