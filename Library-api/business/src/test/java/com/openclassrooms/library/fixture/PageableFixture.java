package com.openclassrooms.library.fixture;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFixture {

    public static Pageable buildDefaultDocumentPageable() {
        return PageRequest.of(0, 20, Sort.Direction.ASC, "title");
    }
}
