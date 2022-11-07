package com.devmountain.issApp.dtos;

import com.devmountain.issApp.entities.CrewMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewMemberDto implements Serializable{
    private Long crewId;
    private String crewName;
    private String country;
    private String specialization;

    private String mission;

    private String crewPic;

    private boolean current;

    public CrewMemberDto(CrewMember crewMember){
        if(crewMember.getCrewId() != null){
            this.crewId=crewMember.getCrewId();
        }
        if(crewMember.getCrewName() != null){
            this.crewName = crewMember.getCrewName();
        }
        if(crewMember.getCountry() != null){
            this.country = crewMember.getCountry();
        }
        if(crewMember.getSpecialization() != null){
            this.specialization = crewMember.getSpecialization();
        }
        if(crewMember.getMission() != null){
            this.mission = crewMember.getMission();
        }
        if(crewMember.getCrewPic() != null){
            this.crewPic = crewMember.getCrewPic();
        }
        this.current = crewMember.isCurrent();

    }
}


