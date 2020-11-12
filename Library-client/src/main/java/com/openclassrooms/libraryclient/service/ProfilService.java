package com.openclassrooms.libraryclient.service;

import com.openclassrooms.libraryclient.model.User;
import com.openclassrooms.libraryclient.model.UserProfil;
import com.openclassrooms.libraryclient.proxy.LoanProxy;
import com.openclassrooms.libraryclient.proxy.WaitingListProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilService {

    @Autowired
    private LoanProxy loanProxy;

    @Autowired
    private WaitingListProxy waitingListProxy;

    public UserProfil loadUserProfil(User user, String authToken) {
        UserProfil userProfil = new UserProfil();
        userProfil.setUser(user);
        userProfil.setLoans(loanProxy.getAllByUser(user.getId(), "Bearer " + authToken));
        userProfil.setUserWaitingLists(waitingListProxy.getUserWaitingLists(user.getId(), "Bearer " + authToken));
        return userProfil;
    }
}
