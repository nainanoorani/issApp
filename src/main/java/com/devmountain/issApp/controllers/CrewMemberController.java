package com.devmountain.issApp.controllers;

import com.devmountain.issApp.dtos.CrewMemberDto;
import com.devmountain.issApp.services.CrewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewMemberController {
    @Autowired
    private CrewMemberService crewMemberService;

    @GetMapping("/crew/")
    public List<CrewMemberDto> getCurrentCrewMembers(){
        return crewMemberService.getCurrentCrewMembers();
    }

    @GetMapping("/crew/{mission}")
    public List<CrewMemberDto> getCrewMembersByMission(@PathVariable String mission){
        return crewMemberService.getCrewMembersByMission(mission);
    }




}
