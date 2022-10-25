package com.devmountain.issApp.repositories;

import com.devmountain.issApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //since name is unique we use optional. list if name is not unique (can have multiple names)
    Optional<User> findByName(String name);
}
