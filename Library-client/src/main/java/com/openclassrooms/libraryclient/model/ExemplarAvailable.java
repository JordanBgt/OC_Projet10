package com.openclassrooms.libraryclient.model;

public class ExemplarAvailable {

    private Integer number;
    private String libraryName;

    public ExemplarAvailable() {
    }

    public ExemplarAvailable(Integer number, String libraryName) {
        this.number = number;
        this.libraryName = libraryName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
