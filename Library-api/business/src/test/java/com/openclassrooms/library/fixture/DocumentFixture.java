package com.openclassrooms.library.fixture;

import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;

import java.util.List;

public class DocumentFixture {

    public static Document buildDocument() {
     Document document = new Document();
     document.setId(1L);
     document.setTitle("title");
     document.setDescription("description");
     document.setAuthor(AuthorFixture.buildAuthor());
     document.setPublisher(PublisherFixture.buildPublisher());
     document.setCategory(EDocumentCategory.LITERATURE);
     document.setIsbn("isbn");
     document.setType(EDocumentType.BOOK);
     document.setWaitingList(WaitingListFixture.buildWaitingList());
     return document;
    }

    public static DocumentDto buildDocumentDto() {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(1L);
        documentDto.setTitle("title");
        documentDto.setDescription("description");
        documentDto.setAuthor(AuthorFixture.buildAuthorDto());
        documentDto.setPublisher(PublisherFixture.buildPublisherDto());
        documentDto.setCategory("Littérature");
        documentDto.setIsbn("isbn");
        documentDto.setType("Livre");
        documentDto.setWaitingList(WaitingListFixture.buildWaitingListDto());
        return documentDto;
    }

    public static List<Document> buildDocumentList() {
        Document document1 = DocumentFixture.buildDocument();
        Document document2 = DocumentFixture.buildDocument();
        document2.setId(2L);
        document2.setTitle("title 2");
        return List.of(document1, document2);
    }

    public static List<DocumentLightDto> buildDocumentLightDtoList() {
        DocumentLightDto document1 = new DocumentLightDto(1L, "title 1",
                AuthorFixture.buildAuthor().getLastName(), AuthorFixture.buildAuthor().getFirstName(), null,
                1L);
        DocumentLightDto document2 = new DocumentLightDto(2L, "title 2", "authorLastName",
                "authorFirstName", null, 2L);
        return List.of(document1, document2);
    }
}
