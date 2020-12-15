package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.entity.Exemplar;

public class ExemplarFixture {

    public static Exemplar buildExemplar() {
        return new Exemplar(1L, "reference", DocumentFixture.buildDocument(), LibraryFixture.buildLibrary());
    }

    public static ExemplarDto buildNewExemplarDto() {
        return new ExemplarDto(null, null, DocumentFixture.buildDocumentDto(), LibraryFixture.buildLibraryDto());
    }

}
