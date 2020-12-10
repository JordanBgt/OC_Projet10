package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.AuthorDto;
import com.openclassrooms.library.entity.Author;

public class AuthorFixture {

    public static Author buildAuthor() {
        return new Author(1L, "lastname", "firstname");
    }

    public static AuthorDto buildAuthorDto() {
        return new AuthorDto(1L, "lastname", "firstname");
    }
}
