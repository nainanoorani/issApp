package com.devmountain.issApp.repositories;

import com.devmountain.issApp.entities.CrewMember;
import com.devmountain.issApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {
    List<CrewMember> findByCurrent(boolean current);
    List<CrewMember> findByMission(String mission);

}
