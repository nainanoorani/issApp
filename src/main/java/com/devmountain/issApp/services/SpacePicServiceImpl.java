package com.devmountain.issApp.services;

import com.devmountain.issApp.dtos.SpacePicDto;
import com.devmountain.issApp.entities.SpacePic;
import com.devmountain.issApp.entities.User;
import com.devmountain.issApp.repositories.SpacePicRepository;
import com.devmountain.issApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpacePicServiceImpl implements SpacePicService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpacePicRepository spacePicRepository;

    //automatically favorite pictures added by user
    @Override
    @Transactional
    public void addSpacePic(SpacePicDto spacePicDto, Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        SpacePic spacePic = new SpacePic(spacePicDto);
        userOptional.ifPresent(spacePic::setUser);
        spacePic.setFavoritePic(true);
        spacePicRepository.saveAndFlush(spacePic);

    }

    @Override
    @Transactional
    public void deleteSpacePicById(SpacePicDto spacePicDto, Long spacePicId){
        Optional<SpacePic> spacePicOptional = spacePicRepository.findById(spacePicDto.getImageId());
        spacePicOptional.ifPresent(spacePic -> deleteSpacePicById(spacePicDto, spacePicId));
    }

    //hardcode boolean as true. for find by favorite
    @Override
    @Transactional
    public void updateFavoriteASpacePic(SpacePicDto spacePicDto){
        Optional<SpacePic> spacePicOptional = spacePicRepository.findById(spacePicDto.getImageId());
        spacePicOptional.ifPresent(spacePic -> {
            //if false make it true
            if(spacePicDto.isFavoritePic()==false){
                spacePic.setFavoritePic(true);}
            //if true make it false
            else {
                spacePic.setFavoritePic(false);
            }
        });
    }


    @Override
    public List<SpacePicDto> getFavoriteSpacePicsByUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<SpacePic> spacePicList = spacePicRepository.findByUserAndFavoritePic(userOptional.get(), true);
            //arrow function. grab space pics and make new dto for each space pic.
            return spacePicList.stream().map(spacePic->new SpacePicDto(spacePic)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    //return all space pictures added by any user
    @Override
    public List<SpacePicDto> getAllSpacePics(){
        List<SpacePic> spacePicList = spacePicRepository.findAll();
        //is this correct? and same for get all crew members?
        return spacePicList.stream().map(spacePic->new SpacePicDto(spacePic)).collect(Collectors.toList());


    }

}
