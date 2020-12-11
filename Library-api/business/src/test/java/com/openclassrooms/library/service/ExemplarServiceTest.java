package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.DocumentRepository;
import com.openclassrooms.library.dao.ExemplarRepository;
import com.openclassrooms.library.dao.LibraryRepository;
import com.openclassrooms.library.entity.Document;
import com.openclassrooms.library.entity.Exemplar;
import com.openclassrooms.library.fixture.DocumentFixture;
import com.openclassrooms.library.fixture.ExemplarFixture;
import com.openclassrooms.library.mapper.ExemplarMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
    private ExemplarMapper exemplarMapper;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private LibraryRepository libraryRepository;

    @Mock
    private WaitingListService waitingListService;

    @Test
    private void whenDeleteExemplar_updateWaitingListSize() {
        // GIVEN
        Long exemplarId = 1L;
        Document document = DocumentFixture.buildDocument();
        when(exemplarRepository.findById(exemplarId)).thenReturn(Optional.of(ExemplarFixture.buildExemplar()));
        when(documentRepository.findById(document.getId())).thenReturn(Optional.of(document));
        doNothing().when(exemplarRepository).delete(any(Exemplar.class));

        // WHEN
        exemplarService.delete(exemplarId);

        // THEN
        ver
        verify(ReflectionTestUtils.invokeMethod(exemplarService, "updateWaitingListSize", document));
    }
}
