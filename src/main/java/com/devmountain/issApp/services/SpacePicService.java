package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.SpacePicDto;

import javax.transaction.Transactional;
import java.util.List;

public interface SpacePicService {
    //automatically favorite pictures added by user
    @Transactional
    void addSpacePic(SpacePicDto spacePicDto, Long userId);

    @Transactional
    void deleteSpacePicById(SpacePicDto spacePicDto, Long spacePicId);

    //hardcode boolean as true. for find by favorite
    @Transactional
    void updateFavoriteASpacePic(SpacePicDto spacePicDto);

    List<SpacePicDto> getFavoriteSpacePicsByUser(Long userId);

    //return all space pictures added by any user
    List<SpacePicDto> getAllSpacePics();
}
