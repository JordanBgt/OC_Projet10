package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.UserWaitingListDto;
import com.openclassrooms.library.entity.UserWaitingList;

public class UserWaitingListFixture {

    public static UserWaitingList buildUserWaitingList() {
        return new UserWaitingList(1L, UserFixture.buildUser(), WaitingListFixture.buildWaitingList(), 1L, null);
    }

    public static UserWaitingListDto buildUserWaitingListDto() {
        return new UserWaitingListDto(1L, UserFixture.buildUserDto(), WaitingListFixture.buildWaitingListDto(), null, null);
    }
}
