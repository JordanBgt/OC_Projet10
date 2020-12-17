package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.AuthorRepository;
import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dao.PublisherRepository;
import com.openclassrooms.library.dao.UserWaitingListRepository;
import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.entity.Author;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.Publisher;
import com.openclassrooms.library.entity.User;
import com.openclassrooms.library.fixture.AuthorFixture;
import com.openclassrooms.library.fixture.DocumentFixture;
import com.openclassrooms.library.fixture.LoanFixture;
import com.openclassrooms.library.fixture.PublisherFixture;
import com.openclassrooms.library.fixture.UserFixture;
import com.openclassrooms.library.fixture.UserWaitingListFixture;
import com.openclassrooms.library.mapper.DocumentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentService documentService;
    
    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentMapper documentMapper;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private WaitingListService waitingListService;

    @Mock
    private ExemplarService exemplarService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserWaitingListRepository userWaitingListRepository;

    @Test
    public void findByIdWhereIdExists() {
        // GIVEN
        Document document = DocumentFixture.buildDocument();
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        User user = UserFixture.buildUser();
        when(documentRepository.findById(document.getId())).thenReturn(Optional.of(document));
        when(documentMapper.toDocumentDto(document)).thenReturn(documentDto);

        // WHEN
        DocumentDto result = documentService.findById(document.getId(), user.getId());

        // THEN
        verify(documentRepository).findById(document.getId());
        verify(waitingListService).checkIfWaitingListIsFull(document.getWaitingList());
        verify(documentMapper).toDocumentDto(document);
        verify(waitingListService).setWaitingListDtoAttributes(documentDto.getWaitingList());
        assertThat(result).isEqualTo(documentDto);
    }

    @Test
    public void findByIdThrowEntityNotFoundException() {
        Long documentId = 15L;
        Long userId =1L;
        assertThatThrownBy(() -> documentService.findById(documentId, userId)).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void createDocument() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        documentDto.setId(null);
        Document document = DocumentFixture.buildDocument();
        Author author = AuthorFixture.buildAuthor();
        Publisher publisher = PublisherFixture.buildPublisher();
        when(publisherRepository.findById(documentDto.getPublisher().getId())).thenReturn(Optional.of(publisher));
        when(authorRepository.findById(documentDto.getAuthor().getId())).thenReturn(Optional.of(author));
        when(documentRepository.save(any(Document.class))).thenReturn(document);
        when(documentMapper.toDocumentDto(document)).thenReturn(documentDto);

        // WHEN
        DocumentDto result = documentService.createOrUpdate(documentDto);

        //THEN
        assertThat(result).isEqualTo(documentDto);
    }

    @Test
    public void updateDocument() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        Document document = DocumentFixture.buildDocument();
        Author author = AuthorFixture.buildAuthor();
        Publisher publisher = PublisherFixture.buildPublisher();
        when(documentRepository.findById(documentDto.getId())).thenReturn(Optional.of(document));
        when(publisherRepository.findById(documentDto.getPublisher().getId())).thenReturn(Optional.of(publisher));
        when(authorRepository.findById(documentDto.getAuthor().getId())).thenReturn(Optional.of(author));
        when(documentRepository.save(any(Document.class))).thenReturn(document);
        when(documentMapper.toDocumentDto(document)).thenReturn(documentDto);

        // WHEN
        DocumentDto result = documentService.createOrUpdate(documentDto);

        //THEN
        verify(documentRepository).findById(document.getId());
        assertThat(result).isEqualTo(documentDto);
    }

    @Test
    public void checkIfDocumentCanBeReserved_WhenExemplarAvailableDtoListIsEmpty_ReturnTrue() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        Long userId = 1L;
        when(exemplarService.findAllAvailableByDocumentId(documentDto.getId())).thenReturn(new ArrayList<>());

        // THEN
        assertThat((boolean) ReflectionTestUtils.invokeMethod(documentService, "checkIfDocumentCanBeReserved", documentDto, userId)).isEqualTo(true);
    }

    @Test
    public void checkIfDocumentCanBeReserved_WhenWaitingListIsFull_ReturnFalse() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        documentDto.getWaitingList().setFull(true);
        Long userId = 1L;
        when(exemplarService.findAllAvailableByDocumentId(documentDto.getId())).thenReturn(new ArrayList<>());

        // THEN
        assertThat((boolean) ReflectionTestUtils.invokeMethod(documentService, "checkIfDocumentCanBeReserved", documentDto, userId)).isEqualTo(false);
    }

    @Test
    public void checkIfDocumentCanBeReserved_WhenUserIdIsNull_ReturnFalse() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        Long userId = null;
        when(exemplarService.findAllAvailableByDocumentId(documentDto.getId())).thenReturn(new ArrayList<>());

        // THEN
        assertThat((boolean) ReflectionTestUtils.invokeMethod(documentService, "checkIfDocumentCanBeReserved", documentDto, userId)).isEqualTo(false);
    }

    @Test
    public void checkIfDocumentCanBeReserved_WhenUserBorrowedDocument_ReturnFalse() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        Long userId = 1L;
        when(exemplarService.findAllAvailableByDocumentId(documentDto.getId())).thenReturn(new ArrayList<>());
        when(loanRepository.findLoandByDocumentIdAndUserId(documentDto.getId(), userId)).thenReturn(LoanFixture.buildLoan());

        // THEN
        assertThat((boolean) ReflectionTestUtils.invokeMethod(documentService, "checkIfDocumentCanBeReserved", documentDto, userId)).isEqualTo(false);
    }

    @Test
    public void checkIfDocumentCanBeReserved_WhenUserReservedDocument_ReturnFalse() {
        // GIVEN
        DocumentDto documentDto = DocumentFixture.buildDocumentDto();
        Long userId = 1L;
        when(exemplarService.findAllAvailableByDocumentId(documentDto.getId())).thenReturn(new ArrayList<>());
        when(userWaitingListRepository.findByWaitingListIdAndUserId(documentDto.getWaitingList().getId(), userId)).thenReturn(UserWaitingListFixture.buildUserWaitingList());

        // THEN
        assertThat((boolean) ReflectionTestUtils.invokeMethod(documentService, "checkIfDocumentCanBeReserved", documentDto, userId)).isEqualTo(false);
    }

}
