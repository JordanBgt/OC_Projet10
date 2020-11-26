package com.openclassrooms.librarybatch.model;

import java.time.LocalDate;

public class UserWaitingList {

    private Long id;
    private User user;
    private WaitingList waitingList;
    private Integer userPosition;
    private LocalDate mailingDate;
    private String documentTitle;

    public UserWaitingList() {}

    public UserWaitingList(Long id, User user, WaitingList waitingList, Integer userPosition, LocalDate mailingDate,
                           String documentTitle) {
        this.id = id;
        this.user = user;
        this.waitingList = waitingList;
        this.userPosition = userPosition;
        this.mailingDate = mailingDate;
        this.documentTitle = documentTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WaitingList getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(WaitingList waitingList) {
        this.waitingList = waitingList;
    }

    public Integer getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(Integer userPosition) {
        this.userPosition = userPosition;
    }

    public LocalDate getMailingDate() {
        return mailingDate;
    }

    public void setMailingDate(LocalDate mailingDate) {
        this.mailingDate = mailingDate;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
}
