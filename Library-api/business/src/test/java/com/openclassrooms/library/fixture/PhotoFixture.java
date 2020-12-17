package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.PhotoDto;
import com.openclassrooms.library.entity.Photo;

public class PhotoFixture {

    public static Photo buildPhoto() {
        return new Photo(1L, "photo", "jpeg");
    }

    public static PhotoDto buildPhotoDto() {
        return new PhotoDto(1L, "photo", "jpeg", null);
    }
}
