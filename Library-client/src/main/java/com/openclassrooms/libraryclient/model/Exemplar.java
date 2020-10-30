package com.openclassrooms.libraryclient.model;

import java.time.LocalDate;

public class Exemplar {

    private Long id;
    private String reference;
    private Document document;
    private Library library;
    private LocalDate loanEndDate;
    private String documentTitle;

    public Exemplar() {
    }

    public Exemplar(Long id, String reference, Document document, Library library, LocalDate loanEndDate,
                    String documentTitle) {
        this.id = id;
        this.reference = reference;
        this.document = document;
        this.library = library;
        this.loanEndDate = loanEndDate;
        this.documentTitle = documentTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
}
