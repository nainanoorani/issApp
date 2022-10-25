package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.UserDto;
import com.devmountain.issApp.entities.User;
import com.devmountain.issApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //autowire any beans from config class

    @Override
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("User Added Successfully");
        return response;
    }

    @Override
    public List<String> retrieveUserData(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByName(userDto.getName());
        if(userOptional.isPresent()){
            response.add("User name exists");
        }
        return response;
    }

}
