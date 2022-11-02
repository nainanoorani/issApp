package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.SpacePicDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SpacePicService {
    //automatically favorite pictures added by user
    @Transactional
    void addSpacePic(SpacePicDto spacePicDto, Long userId);

    @Transactional
    void deleteSpacePicById(Long spacePicId);
//    String deleteSpacePicById(Long spacePicId, Long userId);

    //hardcode boolean as true. for find by favorite
    @Transactional
//    void updateFavoriteSpacePic(SpacePicDto spacePicDto);
    void updateFavoriteSpacePic(Long spacePicId, Long userId);


    List<SpacePicDto> getFavoriteSpacePicsByUser(Long userId);

    //return all space pictures added by any user

    List<SpacePicDto> getAllSpacePics();

    Optional<SpacePicDto> getSpacePicById(Long spacePicId);

    }
