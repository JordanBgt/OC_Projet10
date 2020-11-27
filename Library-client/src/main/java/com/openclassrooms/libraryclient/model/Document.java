package com.openclassrooms.libraryclient.model;

import java.time.LocalDate;

public class Document {
    private Long id;
    private String title;
    private String isbn;
    private String description;
    private LocalDate publicationDate;
    private Publisher publisher;
    private Author author;
    private String type;
    private String category;
    private Photo photo;
    private WaitingList waitingList;
    private boolean canBeReserved;

    public Document() {
    }

    public Document(Long id, String title, String isbn, String description, LocalDate publicationDate,
                    Publisher publisher, Author author, String type, String category, Photo photo,
                    WaitingList waitingList, boolean canBeReserved) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.author = author;
        this.type = type;
        this.category = category;
        this.photo = photo;
        this.waitingList = waitingList;
        this.canBeReserved = canBeReserved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public WaitingList getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(WaitingList waitingList) {
        this.waitingList = waitingList;
    }

    public boolean isCanBeReserved() {
        return canBeReserved;
    }

    public void setCanBeReserved(boolean canBeReserved) {
        this.canBeReserved = canBeReserved;
    }
}
