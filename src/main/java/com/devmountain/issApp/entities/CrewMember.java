package com.devmountain.issApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Crew Members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crew_id;

    @Column
    private String crewName;

    @Column
    private String country;

    @Column
    private String specialization;

    @ManyToOne
    @JsonBackReference
    private User user;


//    public CrewMember(CrewMemberDto crewMemberDto){
//        if(crewMemberDto.getCrewName() != null){
//            this.crewName = crewMemberDto.getCrewName();
//        }
//    if(crewMemberDto.getCountry() != null){
//            this.country = crewMemberDto.getCountry();
//        }
//    if(crewMemberDto.getSpecialization() != null){
//            this.specialization = crewMemberDto.getSpecialization();
//        }
//    }


}