package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.AuthorRepository;
import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.LoanRepository;
import com.openclassrooms.library.dao.PublisherRepository;
import com.openclassrooms.library.dao.UserWaitingListRepository;
import com.openclassrooms.library.dao.predicate.DocumentPredicateBuilder;
import com.openclassrooms.library.dto.DocumentDto;
import com.openclassrooms.library.dto.DocumentLightDto;
import com.openclassrooms.library.entity.Author;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.EDocumentCategory;
import com.openclassrooms.library.entity.EDocumentType;
import com.openclassrooms.library.entity.ExemplarAvailableDto;
import com.openclassrooms.library.entity.Publisher;
import com.openclassrooms.library.entity.criteria.DocumentSearch;
import com.openclassrooms.library.mapper.DocumentMapper;
import com.querydsl.core.types.Predicate;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Service to manage documents
 *
 * @see Document
 * @see DocumentDto
 * @see DocumentLightDto
 * @see DocumentRepository
 * @see DocumentMapper
 * @see AuthorRepository
 * @see PublisherRepository
 * @see FileStorageService
 */
@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private WaitingListService waitingListService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserWaitingListRepository userWaitingListRepository;

    @Autowired
    private ExemplarService exemplarService;

    /**
     * Method to retrieve all documents. For each document, we convert the photo file to base 64
     *
     * @param searchCriteria search criteria to filter the search
     * @param page the requested page
     * 
     * @return a page of documents
     * @see DocumentRepository#findAll(Predicate, Pageable) 
     * @see DocumentService#convertFileToBase64String(Resource)
     * @see FileStorageService#load(String)
     */
    public Page<DocumentLightDto> findAll(DocumentSearch searchCriteria, Pageable page) {
        Page<DocumentLightDto> results = documentRepository.findAll(DocumentPredicateBuilder.buildSearch(searchCriteria), page)
                .map(documentMapper::toDocumentLightDto);

        results.forEach(document -> {
            if (document.getPhoto() != null) {
                String base64String = this.convertFileToBase64String(fileStorageService.load(document.getPhoto().getName()));
                document.getPhoto().setFileToBase64String(base64String);
            }
        });

        return results;
    }

    /**
     * Method to retrieve one document by its id. We convert its photo file to base 64
     *
     * @param documentId id of the requested document
     * @param userId id of the connected user
     * @return a document
     * @see DocumentRepository#findById(Object)
     * @see DocumentService#convertFileToBase64String(Resource)
     */
    public DocumentDto findById(Long documentId, Long userId) {
        Document document = documentRepository.findById(documentId).orElseThrow(EntityNotFoundException::new);
        waitingListService.checkIfWaitingListIsFull(document.getWaitingList());
        DocumentDto documentDto = documentMapper.toDocumentDto(document);
        waitingListService.setWaitingListDtoAttributes(documentDto.getWaitingList());
        if (documentDto.getPhoto() != null) {
            String base64String = this.convertFileToBase64String(fileStorageService.load(documentDto.getPhoto().getName()));
            documentDto.getPhoto().setFileToBase64String(base64String);
        }
        documentDto.setCanBeReserved(checkIfDocumentCanBeReserved(documentDto, userId));

        return documentDto;
    }

    /**
     * Method to create or update a document
     *
     * @param documentDto the document to save
     *
     * @return the saved document
     * @see DocumentRepository#save(Object)
     */
    public DocumentDto createOrUpdate(DocumentDto documentDto) {
        Document document;
        if (documentDto.getId() != null) {
            document = documentRepository.findById(documentDto.getId()).orElseThrow(EntityNotFoundException::new);
        } else {
            document = new Document();
        }
        Author author = authorRepository.findById(documentDto.getAuthor().getId()).orElseThrow(EntityNotFoundException::new);
        Publisher publisher = publisherRepository.findById(documentDto.getPublisher().getId()).orElseThrow(EntityNotFoundException::new);
        document.setIsbn(documentDto.getIsbn());
        document.setAuthor(author);
        document.setTitle(documentDto.getTitle());
        document.setCategory(EDocumentCategory.get(documentDto.getCategory()));
        document.setType(EDocumentType.get(documentDto.getType()));
        document.setDescription(documentDto.getDescription());
        document.setPublicationDate(documentDto.getPublicationDate());
        document.setPublisher(publisher);

        return documentMapper.toDocumentDto(documentRepository.save(document));
    }

    /**
     * Method to delete a document by its id
     *
     * @param documentId id of the document to delete
     * @see DocumentRepository#deleteById(Object)
     */
    public void delete(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    /**
     * Method to convert a file to base 64 string in order to be used by the client
     *
     * @param file the file to convert
     *
     * @return a base 64 string
     */
    private String convertFileToBase64String(Resource file) {
        byte[] photoToByteArray = new byte[0];
        try {
            photoToByteArray = IOUtils.toByteArray(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(photoToByteArray);
    }

    /**
     * Method to check if a document can be reserved
     *
     * @param document document to check
     * @param userId the user who wants to reserve (or null if the user is not connected)
     *
     * @return boolean
     */
    private boolean checkIfDocumentCanBeReserved(DocumentDto document, Long userId) {
        List<ExemplarAvailableDto> exemplarAvailableDtoList = exemplarService.findAllAvailableByDocumentId(document.getId());
        if (!exemplarAvailableDtoList.isEmpty()) {
            return false;
        }
        if (document.getWaitingList().isFull()) {
            return false;
        } else if (userId == null) {
            return false;
        } else if (loanRepository.findLoandByDocumentIdAndUserId(document.getId(), userId) != null) {
            return false;
        } else return userWaitingListRepository.findByWaitingListIdAndUserId(document.getWaitingList().getId(), userId) == null;
    }

}
