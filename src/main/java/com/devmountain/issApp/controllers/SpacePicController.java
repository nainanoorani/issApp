package com.devmountain.issApp.controllers;

import com.devmountain.issApp.dtos.SpacePicDto;
import com.devmountain.issApp.services.SpacePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spacePic")
public class SpacePicController {

    @Autowired
    private SpacePicService spacePicService;

    @GetMapping("/user/{userId}")
    public List<SpacePicDto> getFavoriteSpacePicsByUser(@PathVariable Long userId) {
        return spacePicService.getFavoriteSpacePicsByUser(userId);
    }

    @GetMapping
    public List<SpacePicDto> getAllSpacePics() {
        return spacePicService.getAllSpacePics();
    }

    @PostMapping("/user/{userId}")
    public void addSpacePic(@RequestBody SpacePicDto spacePicDto, @PathVariable Long userId) {
        spacePicService.addSpacePic(spacePicDto, userId);
    }

    //This is different from note app (passing in dto)
//    @DeleteMapping("/{spacePicId}")
//    public void deleteSpacePicById(@RequestBody SpacePicDto spacePicDto, @PathVariable Long spacePicId) {
//        spacePicService.deleteSpacePicById(spacePicDto, spacePicId);
//    }

    //how to make it so they can only delete their own uploaded pictures
    @DeleteMapping("/{spacePicId}")
    public void deleteSpacePicById(@PathVariable Long spacePicId, @PathVariable Long userId) {
        spacePicService.deleteSpacePicById(spacePicId, userId);
    }

    //should I use dto or id here??
//    @PutMapping
//    public void updateFavoriteSpacePic(@RequestBody SpacePicDto spacePicDto) {
//        spacePicService.updateFavoriteSpacePic(spacePicDto);
//    }
    @PutMapping("/{spacePicId}")
    public void updateFavoriteSpacePic(@PathVariable Long spacePicId) {
        spacePicService.updateFavoriteSpacePic(spacePicId);
    }
}
