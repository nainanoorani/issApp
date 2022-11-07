package com.devmountain.issApp.repositories;

import com.devmountain.issApp.entities.SpacePic;
import com.devmountain.issApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpacePicRepository extends JpaRepository<SpacePic, Long> {

    List<SpacePic> findByImageId(Long spacePicId);

    List<SpacePic> findByUserAndFavoritePic(User user, boolean isFavorite);
    List<SpacePic> findByUserId(Long userId);


}
