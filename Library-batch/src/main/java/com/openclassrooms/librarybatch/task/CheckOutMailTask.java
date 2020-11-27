package com.openclassrooms.librarybatch.task;

import com.openclassrooms.librarybatch.model.*;
import com.openclassrooms.librarybatch.proxy.AuthProxy;
import com.openclassrooms.librarybatch.proxy.DocumentProxy;
import com.openclassrooms.librarybatch.proxy.ExemplarProxy;
import com.openclassrooms.librarybatch.proxy.UserWaitingListProxy;
import com.openclassrooms.librarybatch.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Scheduled tast to delete all expired userWaitingList and to send email to the first userWaitingList when an exemplar is available
 */
@Component
public class CheckOutMailTask {

    @Autowired
    private AuthProxy authProxy;

    @Autowired
    private DocumentProxy documentProxy;

    @Autowired
    private UserWaitingListProxy userWaitingListProxy;

    @Autowired
    private ExemplarProxy exemplarProxy;

    @Autowired
    private EmailService emailService;

    private JwtResponse jwtResponse;

    /**
     * Each day, this task delete expired userWaitingList, check if an exemplar reserved by a user is available,
     * and if it's true, it send an email to the first user in the waiting list
     */
    @Scheduled(cron = "${app.periodicityCron}", zone = "Europe/Paris")
    public void sendMail() {
        jwtResponse = authProxy.authenticateUser(new LoginRequest("admin", "admin")).getBody();

        deleteExpiredUserWaitingList();

        List<DocumentLight> documents = documentProxy
                .getAllDocuments(true)
                .getContent();
        List<DocumentLight> documentsWithAvailableExemplars = new ArrayList<>();
        List<UserWaitingList> userWaitingLists = new ArrayList<>();

        // among all the documents, we want to keep only those that have available exemplars
        for (DocumentLight document : documents) {
            List<ExemplarAvailable> exemplars = exemplarProxy.getAllAvailableExemplarsByDocumentId(document.getId());
            if (exemplars != null && exemplars.size() > 0) {
                documentsWithAvailableExemplars.add(document);
            }
        }

        // among all the documents that have available exemplars, we get the first user in the waiting list if he has not yet been notified by email
        for (DocumentLight document : documentsWithAvailableExemplars) {
            UserWaitingList userWaitingList =
                    userWaitingListProxy.getFirstUserWaitingListByWaitingListId("Bearer " + jwtResponse.getToken(),
                            document.getWaitingListId());
            if (userWaitingList != null && userWaitingList.getMailingDate() == null) {
                userWaitingList.setDocumentTitle(document.getTitle());
                userWaitingLists.add(userWaitingList);
            }
        }
        // we send an email to each first user in the waiting list to inform them of the availability of an exemplar.  And we update UserWaitingList
        for (UserWaitingList userWaitingList : userWaitingLists) {
            try {
                emailService.sendCheckOutMail(userWaitingList);
                userWaitingList.setMailingDate(LocalDate.now());
                userWaitingListProxy.updateUserWaitingList("Bearer " + jwtResponse.getToken(), userWaitingList);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to delete all expired UserWaitingList
     */
    private void deleteExpiredUserWaitingList() {
        userWaitingListProxy.deleteExpiredUserWaitingList("Bearer " + jwtResponse.getToken());
    }

}
