package com.devmountain.issApp.dtos;

import com.devmountain.issApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String password;

    private Set<SpacePicDto> spacePicDtoSet = new HashSet<>();

    public UserDto(User user){
        if(user.getId() != null){
            this.id=user.getId();
        }
        if(user.getName() != null){
            this.name = user.getName();
        }
        if(user.getPassword() != null){
            this.password=user.getPassword();
        }

    }
}
