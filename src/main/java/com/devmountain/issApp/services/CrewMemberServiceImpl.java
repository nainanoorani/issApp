package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.CrewMemberDto;
import com.devmountain.issApp.entities.CrewMember;
import com.devmountain.issApp.repositories.CrewMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewMemberServiceImpl implements CrewMemberService {
    @Autowired
    private CrewMemberRepository crewMemberRepository;

    @Override
    @Transactional
    public List<CrewMemberDto> getAllCrewMembers(){
        List<CrewMember> crewMemberList = crewMemberRepository.findAll();
        return crewMemberList.stream().map(crewMember->new CrewMemberDto(crewMember)).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<CrewMemberDto> getCrewMembersByMission(String mission){
        List<CrewMember> crewMemberList = crewMemberRepository.findByMission(mission);
        return crewMemberList.stream().map(crewMember->new CrewMemberDto(crewMember)).collect(Collectors.toList());
    }
}
