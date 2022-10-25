package com.devmountain.issApp.dtos;

import com.devmountain.issApp.entities.CrewMember;
import com.devmountain.issApp.entities.SpacePic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpacePicDto implements Serializable{

    private Long imageId;
    private String imageUrl;
    private String description;

    private boolean favoritePic;



    public SpacePicDto(SpacePic spacePic){
        if(spacePic.getImageId() != null){
            this.imageId=spacePic.getImageId();
        }
        if(spacePic.getImageUrl() != null){
            this.imageUrl = spacePic.getImageUrl();
        }
        if(spacePic.getDescription() != null){
            this.description = spacePic.getDescription();
        }
            this.favoritePic = spacePic.isFavoritePic();


    }
}




