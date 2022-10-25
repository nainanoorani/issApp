package com.devmountain.issApp.entities;

import com.devmountain.issApp.dtos.SpacePicDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Space Pictures")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpacePic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String imageUrl;

    @Column(columnDefinition = "text")
    private String description;

    @Column
    private boolean favoritePic;

    @ManyToOne
    @JsonBackReference
    private User user;


    public SpacePic(SpacePicDto spacePicDto){
        if(spacePicDto.getImageUrl() != null){
            this.imageUrl = spacePicDto.getImageUrl();
        }
        if(spacePicDto.getDescription() != null){
            this.description = spacePicDto.getDescription();
        }

            this.favoritePic = spacePicDto.isFavoritePic();

    }

}
