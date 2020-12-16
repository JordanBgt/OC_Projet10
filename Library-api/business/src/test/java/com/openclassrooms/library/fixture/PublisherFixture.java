package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.PublisherDto;
import com.openclassrooms.library.entity.Publisher;

public class PublisherFixture {

    public static Publisher buildPublisher() {
        return new Publisher(1L, "publisher");
    }

    public static PublisherDto buildPublisherDto() {
        return new PublisherDto(1L, "publisher");
    }
}
