package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.UserWaitingList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client to request the API Library waitingLists endpoints
 */
@FeignClient(url = "${app.apiUrl}/waitingLists", name = "waitingList-api")
public interface WaitingListProxy {

    String AUTH_TOKEN = "Authorization";

    @GetMapping("/{userId}")
    List<UserWaitingList> getUserWaitingLists(@PathVariable Long userId, @RequestHeader(AUTH_TOKEN) String bearerToken);

    @PostMapping()
    ResponseEntity<Void> addUserToWaitingList(@RequestBody UserWaitingList userWaitingList, @RequestHeader(AUTH_TOKEN) String bearerToken);

    @DeleteMapping("/{userWaitingListId}")
    ResponseEntity<Void> deleteUserWaitingList(@PathVariable Long userWaitingListId, @RequestHeader(AUTH_TOKEN) String bearerToken);


}
