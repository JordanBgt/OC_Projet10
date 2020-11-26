package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.*;
import com.openclassrooms.library.dto.UserWaitingListDto;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.entity.UserWaitingList;
import com.openclassrooms.library.entity.WaitingList;
import com.openclassrooms.library.mapper.UserWaitingListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Service to manage UserWaitingList
 *
 * @see UserWaitingList
 * @see UserWaitingListDto
 */
@Service
public class UserWaitingListService {

    @Autowired
    private UserWaitingListRepository userWaitingListRepository;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWaitingListMapper userWaitingListMapper;

    @Autowired
    private WaitingListService waitingListService;

    /**
     * Method to add a user to a waiting list. If there is no waitingList for the document, it create one. Add a user
     * only if the waiting list is not full, and set the user position compared to the number of people on the waiting list
     *
     * @param userWaitingListDto dto which contains waitingListId and userId
     *
     * @throws OperationNotSupportedException if the waiting list is full, it throws an OperationNotSupportedException
     */
    public void createUserWaitingList(UserWaitingListDto userWaitingListDto) throws OperationNotSupportedException {
        User user = userRepository.findById(userWaitingListDto.getUser().getId())
                .orElseThrow(EntityNotFoundException::new);
        WaitingList waitingList = waitingListRepository.findById(userWaitingListDto.getWaitingList().getId())
                .orElseThrow(EntityNotFoundException::new);
        waitingListService.checkIfWaitingListIsFull(waitingList);
        Long userWaitingListNumber = userWaitingListRepository.countByWaitingListId(waitingList.getId());
        if (!waitingList.isFull()) {
            UserWaitingList userWaitingList = new UserWaitingList();
            userWaitingList.setUser(user);
            userWaitingList.setUserPosition(userWaitingListNumber +1);
            userWaitingList.setWaitingList(waitingList);
            userWaitingListRepository.save(userWaitingList);
        } else {
            throw new OperationNotSupportedException("La liste d'attente est pleine");
        }

    }

    /**
     * Method to update a userWaitingList
     *
     * @param userWaitingListDto userWaitingList to save
     */
    public void updateUserWaitingList(UserWaitingListDto userWaitingListDto) {
        UserWaitingList userWaitingList = userWaitingListRepository.findById(userWaitingListDto.getId()).orElseThrow(EntityNotFoundException::new);
        userWaitingList.setUserPosition(Long.valueOf(userWaitingListDto.getUserPosition()));
        userWaitingList.setMailingDate(userWaitingListDto.getMailingDate());
        userWaitingListRepository.save(userWaitingList);
    }

    /**
     * Method to delete a user from a waiting list. Get all users further in the waiting list and moves them forward
     * one place
     * @param userWaitingListId userWaitingList id
     */
    public void deleteUserWaitingList(Long userWaitingListId) {
        UserWaitingList userWaitingList = userWaitingListRepository.findById(userWaitingListId)
                .orElseThrow(EntityNotFoundException::new);
        List<UserWaitingList> userWaitingListList = userWaitingListRepository
                .findAllByUserPositionGreaterThan(userWaitingList.getUserPosition());
        userWaitingListRepository.delete(userWaitingList);
        for(UserWaitingList user : userWaitingListList) {
            user.setUserPosition(user.getUserPosition() -1);
            userWaitingListRepository.save(user);
        }
    }

    /**
     * Method to get all userWaitingList by user id
     * @param userId user id
     *
     * @return list of UserWaitingListDto
     */
    public List<UserWaitingListDto> findAllByUserId(Long userId) {
        List<UserWaitingListDto> results = userWaitingListMapper.toListUserWaitingListDto(userWaitingListRepository.findAllByUserId(userId));
        if (results != null) {
            results.forEach(element -> waitingListService.setWaitingListDtoAttributes(element.getWaitingList()));
        }
        return results;
    }

    /**
     * Method to find all UserWaitingList by waitingList id
     *
     * @param waitingListId id of the watiitngList
     *
     * @return a list of UserWaitingListDto
     */
    public List<UserWaitingListDto> findAllByWaitingListId(Long waitingListId) {
        return userWaitingListMapper.toListUserWaitingListDto(userWaitingListRepository.findAllByWaitingListId(waitingListId));
    }

    /**
     * Method to find the userWaitingList with first position
     *
     * @param waitingListId id of the waitingList
     *
     * @return a UserWaitingListDto
     */
    public UserWaitingListDto findFirstUserWaitingListByWaitingListId(Long waitingListId) {
        return userWaitingListMapper.toUserWaitingListDto(userWaitingListRepository.findByWaitingListIdAndUserPosition(waitingListId, 1L));
    }

    /**
     * Method to delete all UserWaitingList where mailingDate is greater than or equal to 2 days
     */
    public void deleteExpiredUserWaitingList() {
        List<UserWaitingList> userWaitingLists = userWaitingListRepository.findAllWhereMailingDateIsGreaterThanOrEqualTo2Days();
        for (UserWaitingList userWaitingList : userWaitingLists) {
            deleteUserWaitingList(userWaitingList.getId());
        }
    }
}
