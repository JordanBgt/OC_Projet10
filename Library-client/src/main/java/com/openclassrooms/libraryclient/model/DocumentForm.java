package com.openclassrooms.libraryclient.model;

public class DocumentForm {

    private String title;
    private String isbn;
    private String authorName;
    private String publisherName;
    private String type;
    private String category;

    public DocumentForm() {
    }

    public DocumentForm(String title, String isbn, String authorName, String publisherName, String type, String category) {
        this.title = title;
        this.isbn = isbn;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.type = type;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.equals("") ? null : title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn.equals("") ? null : isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName.equals("") ? null : authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName.equals("") ? null : publisherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.equals("") ? null : type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category.equals("") ? null : category;
    }
}
