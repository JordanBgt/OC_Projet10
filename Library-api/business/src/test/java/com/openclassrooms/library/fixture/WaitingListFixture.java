package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.WaitingListDto;
import com.openclassrooms.library.entity.WaitingList;

public class WaitingListFixture {

    public static WaitingList buildWaitingList() {
        WaitingList waitingList =  new WaitingList();
        waitingList.setSize(4L);
        waitingList.setFull(false);
        waitingList.setId(1L);
        return waitingList;
    }

    public static WaitingListDto buildWaitingListDto() {
        WaitingListDto waitingListDto = new WaitingListDto();
        waitingListDto.setSize(4);
        waitingListDto.setFull(false);
        waitingListDto.setId(1L);
        return waitingListDto;
    }
}
