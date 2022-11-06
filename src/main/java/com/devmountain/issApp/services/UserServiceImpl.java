package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.UserDto;
import com.devmountain.issApp.entities.User;
import com.devmountain.issApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //autowire any beans from config class

    @Override
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        return response;
    }

    @Override
    public List<String> retrieveUserData(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByName(userDto.getName());
        if(userOptional.isPresent()){
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/index.html");
                response.add(String.valueOf(userOptional.get().getId()));

            } else{
                response.add("Username or password incorrect");
            }
        }
        else{
            response.add("Username or password incorrect");
        }
        return response;
    }

}
