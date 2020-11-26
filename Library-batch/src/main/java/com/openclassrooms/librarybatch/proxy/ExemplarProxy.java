package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.ExemplarAvailable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client to request the API Library exemplars endpoints
 */
@FeignClient(url = "${app.apiUrl}/exemplars", name = "exemplar-api")
public interface ExemplarProxy {

    @GetMapping
    List<ExemplarAvailable> getAllAvailableExemplarsByDocumentId(@RequestParam Long documentId);
}
