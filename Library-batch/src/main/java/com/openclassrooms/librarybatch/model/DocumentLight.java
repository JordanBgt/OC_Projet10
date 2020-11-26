package com.openclassrooms.librarybatch.model;

public class DocumentLight {

    private Long id;
    private String title;
    private String authorLastName;
    private String authorFirstName;
    private Long waitingListId;

    public DocumentLight() {
    }

    public DocumentLight(Long id, String title, String authorLastName, String authorFirstName, Long waitingListId) {
        this.id = id;
        this.title = title;
        this.authorLastName = authorLastName;
        this.authorFirstName = authorFirstName;
        this.waitingListId = waitingListId;
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

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public Long getWaitingListId() {
        return waitingListId;
    }

    public void setWaitingListId(Long waitingListId) {
        this.waitingListId = waitingListId;
    }
}
