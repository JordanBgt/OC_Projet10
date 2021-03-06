package com.openclassrooms.library.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_waiting_list")
public class UserWaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "waiting_list_id")
    private WaitingList waitingList;

    @Column(name = "user_position")
    private Long userPosition;

    @Column(name = "mailing_date")
    private LocalDate mailingDate;

    public UserWaitingList() {
    }

    public UserWaitingList(Long id, User user, WaitingList waitingList, Long userPosition, LocalDate mailingDate) {
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

    public Long getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(Long userPosition) {
        this.userPosition = userPosition;
    }

    public LocalDate getMailingDate() {
        return mailingDate;
    }

    public void setMailingDate(LocalDate date) {
        this.mailingDate = date;
    }
}
