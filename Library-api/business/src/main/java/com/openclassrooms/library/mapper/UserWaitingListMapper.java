package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.UserWaitingListDto;
import com.openclassrooms.library.entity.UserWaitingList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, WaitingListMapper.class})
public interface UserWaitingListMapper {

    UserWaitingListDto toUserWaitingListDto(UserWaitingList userWaitingList);
    List<UserWaitingListDto> toListUserWaitingListDto(List<UserWaitingList> userWaitingListList);
}
