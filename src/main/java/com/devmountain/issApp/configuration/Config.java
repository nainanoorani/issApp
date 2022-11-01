package com.devmountain.issApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//Bean Factory
@Configuration
public class Config {
    //objects needed to run the app that haven't been defined elsewhere
    //BCryptPasswordEncoder is a build in spring class
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
