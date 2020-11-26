package com.openclassrooms.library.mapper;

import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, PublisherMapper.class, WaitingListMapper.class})
public interface DocumentMapper {

    @Mappings({
            @Mapping(target = "type", source = "type.label"),
            @Mapping(target = "category", source = "category.label"),
            @Mapping(target = "canBeReserved", defaultValue = "true")
    })
    DocumentDto toDocumentDto(Document document);

    @Mappings({
            @Mapping(target = "authorFirstName", source = "author.firstName"),
            @Mapping(target = "authorLastName", source = "author.lastName"),
            @Mapping(target = "waitingListId", source = "waitingList.id")
    })
    DocumentLightDto toDocumentLightDto(Document document);
}
