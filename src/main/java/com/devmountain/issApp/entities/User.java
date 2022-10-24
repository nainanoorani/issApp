package com.devmountain.issApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    //check the cascade..and why hashset?
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<SpacePic> spacePicSet = new HashSet<>();
    private Set<CrewMember> crewMemberSet = new HashSet<>();


//    public User(UserDto userDto){
//        if(userDto.getName() != null){
//            this.name = userDto.getName();
//        }
//
//    }
}
