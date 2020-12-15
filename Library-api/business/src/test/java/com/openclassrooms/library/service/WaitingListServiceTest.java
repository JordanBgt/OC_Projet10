package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.UserWaitingListRepository;
import com.openclassrooms.library.entity.WaitingList;
import com.openclassrooms.library.fixture.WaitingListFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaitingListServiceTest {

    @InjectMocks
    private WaitingListService waitingListService;

    @Mock
    private UserWaitingListRepository userWaitingListRepository;

    @Mock
    private ExemplarRepository exemplarRepository;

    @Test
    public void setWaitingListSize() {
        // GIVEN
        WaitingList waitingList = WaitingListFixture.buildWaitingList();
        when(exemplarRepository.countByDocumentId(1L)).thenReturn(4L);

        // WHEN
        waitingListService.setWaitingListSize(waitingList, 1L);

        // THEN
        assertThat(waitingList.getSize()).isEqualTo(8);
    }

    @Test
    public void checkIfWaitingListIsFull_returnFalse() {
        // GIVEN
        WaitingList waitingList = WaitingListFixture.buildWaitingList();
        waitingList.setSize(4L);
        when(userWaitingListRepository.countByWaitingListId(waitingList.getId())).thenReturn(3L);

        // WHEN
        waitingListService.checkIfWaitingListIsFull(waitingList);

        // THEN
        assertThat(waitingList.isFull()).isFalse();
    }

    @Test
    public void checkIfWaitingListIsFull_returnTrue() {
        // GIVEN
        WaitingList waitingList = WaitingListFixture.buildWaitingList();
        waitingList.setSize(4L);
        when(userWaitingListRepository.countByWaitingListId(waitingList.getId())).thenReturn(4L);

        // WHEN
        waitingListService.checkIfWaitingListIsFull(waitingList);

        // THEN
        assertThat(waitingList.isFull()).isTrue();
    }
}
