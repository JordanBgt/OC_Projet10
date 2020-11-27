package com.openclassrooms.library.dto;

import java.time.LocalDate;

public class WaitingListDto {

    private Long id;
    private Integer size;
    private boolean full;
    private String documentTitle;
    private Long documentId;
    private LocalDate endDateNextLoanReturn;
    private int numberUsersInWaitingList;


    public WaitingListDto() {}

    public WaitingListDto(Long id, Integer size, boolean full, String documentTitle, LocalDate endDateNextLoanReturn,
                          int numberUsersInWaitingList, Long documentId) {
        this.id = id;
        this.size = size;
        this.full = full;
        this.documentTitle = documentTitle;
        this.endDateNextLoanReturn = endDateNextLoanReturn;
        this.numberUsersInWaitingList = numberUsersInWaitingList;
        this.documentId = documentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public LocalDate getEndDateNextLoanReturn() {
        return endDateNextLoanReturn;
    }

    public void setEndDateNextLoanReturn(LocalDate endDateNextLoanReturn) {
        this.endDateNextLoanReturn = endDateNextLoanReturn;
    }

    public int getNumberUsersInWaitingList() {
        return numberUsersInWaitingList;
    }

    public void setNumberUsersInWaitingList(int numberUsersInWaitingList) {
        this.numberUsersInWaitingList = numberUsersInWaitingList;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }
}
