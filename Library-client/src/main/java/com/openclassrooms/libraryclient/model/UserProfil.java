package com.openclassrooms.libraryclient.model;

import java.util.List;

public class UserProfil {

    private User user;
    private List<Loan> loans;
    private List<UserWaitingList> userWaitingLists;

    public UserProfil() {
    }

    public UserProfil(User user, List<Loan> loans, List<UserWaitingList> userWaitingLists) {
        this.user = user;
        this.loans = loans;
        this.userWaitingLists = userWaitingLists;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public List<UserWaitingList> getUserWaitingLists() {
        return userWaitingLists;
    }

    public void setUserWaitingLists(List<UserWaitingList> userWaitingLists) {
        this.userWaitingLists = userWaitingLists;
    }
}
