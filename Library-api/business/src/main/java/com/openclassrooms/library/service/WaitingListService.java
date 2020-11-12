package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.UserWaitingListRepository;
import com.openclassrooms.library.dao.WaitingListRepository;
import com.openclassrooms.library.dto.WaitingListDto;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

/**
 * Service to manage WaitingList
 *
 * @see WaitingList
 * @see WaitingListDto
 */
@Service
public class WaitingListService {

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private UserWaitingListRepository userWaitingListRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LoanService loanService;

    /**
     * Method to create a waitingList
     *
     * @param documentId id of the document
     *
     * @return waitingList created
     */
    public WaitingList createWaitingList(Long documentId) {
        Document document = documentRepository.findById(documentId).orElseThrow(EntityNotFoundException::new);
        WaitingList waitingList = new WaitingList();
        setWaitingListSize(waitingList, documentId);
        return waitingListRepository.save(waitingList);
    }

    /**
     * Method to set the size of the waitingList
     *
     * @param waitingList waitingList for which we want to give the size
     */
    public void setWaitingListSize(WaitingList waitingList, Long documentId) {
        Long exemplarNumber = exemplarRepository.countByDocumentId(documentId);
        waitingList.setSize(exemplarNumber * 2);
    }

    public void setWaitingListDtoAttributes(WaitingListDto waitingListDto) {
        int numberUsersInWaitingList = userWaitingListRepository.findAllByWaitingListId(waitingListDto.getId()).size();
        waitingListDto.setNumberUsersInWaitingList(numberUsersInWaitingList);
        LocalDate endDateNextLoanReturn = loanService.findDateNextLoanReturnByDocumentId(waitingListDto.getDocumentId());
        waitingListDto.setEndDateNextLoanReturn(endDateNextLoanReturn);
    }

    /**
     * Method to check if the waitingList is full
     *
     * @param waitingList the waitingList to check
     */
    public void checkIfWaitingListIsFull(WaitingList waitingList) {
        Long userWaitingListNumber = userWaitingListRepository.countByWaitingListId(waitingList.getId());
        waitingList.setFull(userWaitingListNumber >= waitingList.getSize());
    }
}
