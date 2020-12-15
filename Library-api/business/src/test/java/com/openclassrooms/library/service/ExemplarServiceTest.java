package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LibraryRepository;
import com.openclassrooms.library.dto.ExemplarDto;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.Exemplar;
import com.openclassrooms.library.fixture.DocumentFixture;
import com.openclassrooms.library.fixture.ExemplarFixture;
import com.openclassrooms.library.fixture.LibraryFixture;
import com.openclassrooms.library.mapper.ExemplarMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExemplarServiceTest {

    @InjectMocks
    private ExemplarService exemplarService;

    @Mock
    private ExemplarRepository exemplarRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private WaitingListService waitingListService;

    @Mock
    private ExemplarMapper exemplarMapper;

    @Test
    public void whenDeleteExemplar_updateWaitingListSize() {
        // GIVEN
        Exemplar exemplar = ExemplarFixture.buildExemplar();
        Document document = DocumentFixture.buildDocument();
        when(exemplarRepository.findById(exemplar.getId())).thenReturn(Optional.of(exemplar));
        when(documentRepository.findById(document.getId())).thenReturn(Optional.of(document));
        doNothing().when(exemplarRepository).delete(any(Exemplar.class));

        // WHEN
        exemplarService.delete(exemplar.getId());

        // THEN
        verify(waitingListService).setWaitingListSize(document.getWaitingList(), document.getId());
    }

    @Test
    public void createExemplar_UpdateWaitingList() {
        // GIVEN
        ExemplarDto exemplarDto = ExemplarFixture.buildNewExemplarDto();
        Document document = DocumentFixture.buildDocument();
        when(documentRepository.findById(exemplarDto.getDocument().getId())).thenReturn(Optional.of(document));
        when(libraryRepository.findById(exemplarDto.getLibrary().getId())).thenReturn(Optional.of(LibraryFixture.buildLibrary()));
        when(exemplarRepository.save(any(Exemplar.class))).thenReturn(ExemplarFixture.buildExemplar());
        when(exemplarMapper.toExemplarDto(any(Exemplar.class))).thenReturn(ExemplarFixture.buildNewExemplarDto());

        // WHEN
       exemplarService.createOrUpdate(exemplarDto);

        //THEN
        verify(waitingListService).setWaitingListSize(document.getWaitingList(), document.getId());
    }
}
