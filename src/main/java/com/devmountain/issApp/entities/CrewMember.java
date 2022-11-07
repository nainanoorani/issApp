package com.devmountain.issApp.entities;

import com.devmountain.issApp.dtos.CrewMemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="CrewMembers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crewId;

    @Column
    private String crewName;

    @Column
    private String country;

    @Column
    private String specialization;

    @Column
    private String mission;

    @Column
    private String crewPic;

    @Column
    private boolean current;



    public CrewMember(CrewMemberDto crewMemberDto){
        if(crewMemberDto.getCrewId() != null){
            this.crewId=crewMemberDto.getCrewId();
        }
        if(crewMemberDto.getCrewName() != null){
            this.crewName = crewMemberDto.getCrewName();
        }
        if(crewMemberDto.getCountry() != null){
                this.country = crewMemberDto.getCountry();
            }
        if(crewMemberDto.getSpecialization() != null){
                this.specialization = crewMemberDto.getSpecialization();
            }
        if(crewMemberDto.getMission() != null){
            this.mission = crewMemberDto.getMission();
        }
        if(crewMemberDto.getCrewPic() != null){
            this.crewPic = crewMemberDto.getCrewPic();
        }
        this.current = crewMemberDto.isCurrent();

    }


}
