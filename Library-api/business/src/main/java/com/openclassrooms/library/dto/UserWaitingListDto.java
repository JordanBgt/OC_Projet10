package com.openclassrooms.library.dto;

import java.time.LocalDate;

public class UserWaitingListDto {

    private Long id;
    private UserDTo user;
    private WaitingListDto waitingList;
    private Integer userPosition;
    private LocalDate mailingDate;

    public UserWaitingListDto() {}

    public UserWaitingListDto(Long id, UserDTo user, WaitingListDto waitingList, Integer userPosition, LocalDate mailingDate) {
        this.id = id;
        this.user = user;
        this.waitingList = waitingList;
        this.userPosition = userPosition;
        this.mailingDate = mailingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTo getUser() {
        return user;
    }

    public void setUser(UserDTo user) {
        this.user = user;
    }

    public WaitingListDto getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(WaitingListDto waitingList) {
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
}
