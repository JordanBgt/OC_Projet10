package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.LibraryDto;
import com.openclassrooms.library.entity.Library;

public class LibraryFixture {

    public static Library buildLibrary() {
        return new Library(1L, "Library name");
    }

    public static LibraryDto buildLibraryDto() {
        return new LibraryDto(1L, "Library name");
    }
}
