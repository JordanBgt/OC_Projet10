package com.openclassrooms.library.fixture;

import com.openclassrooms.library.entity.Library;

public class LibraryFixture {

    public static Library buildLibrary() {
        return new Library(1L, "Library name");
    }
}
