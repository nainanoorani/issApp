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


    //how to make it so they can only delete their own uploaded pictures
//    @DeleteMapping("/{spacePicId}/{userId}")
//    public void deleteSpacePicById(@PathVariable Long spacePicId, @PathVariable Long userId) {
//        spacePicService.deleteSpacePicById(spacePicId, userId);
//    }
    @DeleteMapping("/{spacePicId}")
    public void deleteSpacePicById(@PathVariable Long spacePicId) {
        spacePicService.deleteSpacePicById(spacePicId);
    }

    @GetMapping("/{spacePicId}")
    public void getSpacePicById(@PathVariable Long spacePicId) {
        spacePicService.getSpacePicById(spacePicId);

    }

    @PutMapping("/{spacePicId}")
    public void updateFavoriteSpacePic(@PathVariable Long spacePicId) {
        spacePicService.updateFavoriteSpacePic(spacePicId);
    }
}
