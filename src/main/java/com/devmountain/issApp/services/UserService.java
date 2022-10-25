package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.UserDto;

import java.util.List;

public interface UserService {
    List<String> addUser(UserDto userDto);

    List<String> retrieveUserData(UserDto userDto);
}
