package com.openclassrooms.libraryclient.model;

import java.time.LocalDate;

public class UserWaitingList {

    private Long id;
    private User user;
    private WaitingList waitingList;
    private Integer userPosition;
    private LocalDate date;

    public UserWaitingList() {
    }

    public UserWaitingList(Long id, User user, WaitingList waitingList, Integer userPosition, LocalDate date) {
        this.id = id;
        this.user = user;
        this.waitingList = waitingList;
        this.userPosition = userPosition;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
