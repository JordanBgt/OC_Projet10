package com.openclassrooms.library.controller;

import com.openclassrooms.library.dto.UserWaitingListDto;
import com.openclassrooms.library.service.UserWaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Controller to handle waitingLists and UserWaitingLists
 *
 * @see com.openclassrooms.library.dto.WaitingListDto
 * @see UserWaitingListDto
 */
@RestController
@RequestMapping("api/waitingLists")
public class UserWaitingListController {

    @Autowired
    private UserWaitingListService userWaitingListService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{userId}")
    public List<UserWaitingListDto> getUserWaitingListsByUserId(@PathVariable Long userId) {
        return userWaitingListService.findAllByUserId(userId);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/firstUser/{waitingListId}")
    public UserWaitingListDto getFirstUserWaitingListByWaitingListId(@PathVariable Long waitingListId) {
        return userWaitingListService.findFirstUserWaitingListByWaitingListId(waitingListId);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping
    public ResponseEntity<Void> addUserToWaitingList(@RequestBody UserWaitingListDto userWaitingListDto) {
        try {
            userWaitingListService.createUserWaitingList(userWaitingListDto);
        } catch (EntityNotFoundException | OperationNotSupportedException e){
            if (e instanceof EntityNotFoundException) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Void> updateUserWaitingList(@RequestBody UserWaitingListDto userWaitingListDto) {
        userWaitingListService.updateUserWaitingList(userWaitingListDto);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/{userWaitingListId}")
    public ResponseEntity<Void> deleteUserWaitingList(@PathVariable Long userWaitingListId) {
        userWaitingListService.deleteUserWaitingList(userWaitingListId);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/expired")
    public ResponseEntity<Void> getAllExpiredUserWaitingList() {
        userWaitingListService.deleteExpiredUserWaitingList();
        return  ResponseEntity.noContent().build();
    }
}
