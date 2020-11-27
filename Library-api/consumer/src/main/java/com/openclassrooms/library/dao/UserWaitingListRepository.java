package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.UserWaitingList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWaitingListRepository extends CrudRepository<UserWaitingList, Long> {

    List<UserWaitingList> findAllByWaitingListId(Long waitingListId);

    Long countByWaitingListId(Long waitingListId);

    UserWaitingList findByWaitingListIdAndUserId(Long waitingListId, Long userId);

    List<UserWaitingList> findAllByUserPositionGreaterThan(Long position);

    List<UserWaitingList> findAllByUserId(Long userId);

    UserWaitingList findByWaitingListIdAndUserPosition(Long waitingListId, Long userPosition);

    @Query(value = "SELECT * FROM library_2.user_waiting_list\n" +
            "WHERE (SELECT DATEDIFF(NOW(), mailing_date)) >= 2", nativeQuery = true)
    List<UserWaitingList> findAllWhereMailingDateIsGreaterThanOrEqualTo2Days();
}
