package com.openclassrooms.library.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false)
    @Type(type = "text")
    private String description;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Author author;

    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private EDocumentType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EDocumentCategory category;

    public Document() {}

    public Document(Long id, String title, String isbn, String description, LocalDate publicationDate,
                    Publisher publisher, Author author, Photo photo, EDocumentType type,
                    EDocumentCategory category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.author = author;
        this.photo = photo;
        this.type = type;
        this.category = category;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public EDocumentType getType() {
        return type;
    }

    public void setType(EDocumentType documentType) {
        this.type = documentType;
    }

    public EDocumentCategory getCategory() {
        return category;
    }

    public void setCategory(EDocumentCategory documentCategory) {
        this.category = documentCategory;
    }
}
