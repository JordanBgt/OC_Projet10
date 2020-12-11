package com.openclassrooms.library.fixture;

import com.openclassrooms.library.entity.Exemplar;

public class ExemplarFixture {

    public static Exemplar buildExemplar() {
        return new Exemplar(1L, "reference", DocumentFixture.buildDocument(), LibraryFixture.buildLibrary());
    }

}
