package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.WaitingListDto;
import com.openclassrooms.library.entity.WaitingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WaitingListMapper {

    @Mappings({
            @Mapping(target = "documentTitle", source = "document.title"),
            @Mapping(target = "endDateNextLoanReturn", defaultValue = "null"),
            @Mapping(target = "numberUsersInWaitingList", defaultValue = "null"),
            @Mapping(target = "documentId", source = "document.id")
    })
    WaitingListDto toWaitingListDto(WaitingList waitingList);
}
