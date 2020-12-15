package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.UserRepository;
import com.openclassrooms.library.dao.UserWaitingListRepository;
import com.openclassrooms.library.dao.WaitingListRepository;
import com.openclassrooms.library.dto.UserWaitingListDto;
import com.openclassrooms.library.entity.UserWaitingList;
import com.openclassrooms.library.entity.WaitingList;
import com.openclassrooms.library.fixture.UserFixture;
import com.openclassrooms.library.fixture.UserWaitingListFixture;
import com.openclassrooms.library.fixture.WaitingListFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserWaitingListServiceTest {

    @InjectMocks
    private UserWaitingListService userWaitingListService;

    @Mock
    private UserWaitingListRepository userWaitingListRepository;

    @Mock
    private WaitingListRepository waitingListRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WaitingListService waitingListService;

    @Test
    public void createUserWaitingList_ThrowOperationNotSupportedExecption() {
        // GIVEN
        UserWaitingListDto userWaitingListDto = UserWaitingListFixture.buildUserWaitingListDto();
        WaitingList waitingList = WaitingListFixture.buildWaitingList();
        waitingList.setSize(2L);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(UserFixture.buildUser()));
        when(waitingListRepository.findById(anyLong())).thenReturn(Optional.of(waitingList));
        doNothing().when(waitingListService).checkIfWaitingListIsFull(waitingList);
        waitingList.setFull(true);
        when(userWaitingListRepository.countByWaitingListId(waitingList.getId())).thenReturn(2L);

        // THEN
        assertThatThrownBy(() -> userWaitingListService.createUserWaitingList(userWaitingListDto)).isInstanceOf(OperationNotSupportedException.class);
    }

    @Test
    public void deleteUserWaitingList() {
        // GIVEN
        UserWaitingList userWaitingList1 = UserWaitingListFixture.buildUserWaitingList();
        UserWaitingList userWaitingList2 = UserWaitingListFixture.buildUserWaitingList();
        userWaitingList2.setUserPosition(2L);
        UserWaitingList userWaitingList3 = UserWaitingListFixture.buildUserWaitingList();
        userWaitingList3.setUserPosition(3L);
        List<UserWaitingList> listOfUserWaitingList = List.of(userWaitingList2, userWaitingList3);
        when(userWaitingListRepository.findById(userWaitingList1.getId())).thenReturn(Optional.of(userWaitingList1));
        when(userWaitingListRepository.findAllByUserPositionGreaterThan(userWaitingList1.getUserPosition())).thenReturn(listOfUserWaitingList);
        doNothing().when(userWaitingListRepository).delete(userWaitingList1);
        when(userWaitingListRepository.save(any(UserWaitingList.class))).thenReturn(null);

        // WHEN
        userWaitingListService.deleteUserWaitingList(userWaitingList1.getId());

        // THEN
        assertThat(listOfUserWaitingList.stream().map(UserWaitingList::getUserPosition)).containsExactlyInAnyOrder(1L, 2L);
    }

}
