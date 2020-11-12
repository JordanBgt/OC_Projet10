package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.WaitingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitingListRepository extends CrudRepository<WaitingList, Long> {}
