package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.UserWaitingList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${app.apiUrl}/waitingLists", value = "userWaitingList-api")
public interface UserWaitingListProxy {

    @GetMapping("/firstUser/{waitingListId}")
    UserWaitingList getFirstUserWaitingListByWaitingListId(@RequestHeader("Authorization") String bearerToken,
                                                           @PathVariable Long waitingListId);

    @PutMapping
    void updateUserWaitingList(@RequestHeader("Authorization") String bearerToken,
                               @RequestBody UserWaitingList userWaitingList);

    @DeleteMapping("/expired")
    void deleteExpiredUserWaitingList(@RequestHeader("Authorization") String bearerToken);

}
