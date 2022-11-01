package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.CrewMemberDto;

import javax.transaction.Transactional;
import java.util.List;

public interface CrewMemberService {
    @Transactional
    List<CrewMemberDto> getCurrentCrewMembers();

    @Transactional
    List<CrewMemberDto> getCrewMembersByMission(String mission);
}
