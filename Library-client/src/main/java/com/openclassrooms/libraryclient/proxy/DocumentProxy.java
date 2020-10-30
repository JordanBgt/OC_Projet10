package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Document;
import com.openclassrooms.libraryclient.model.DocumentLight;
import com.openclassrooms.libraryclient.model.RestPageImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Feign client to request the API Library documents endpoints
 */
@FeignClient(url = "${app.apiUrl}/documents", name = "document-api")
public interface DocumentProxy {

    @GetMapping
    RestPageImpl<DocumentLight> getAllDocuments(@RequestParam(required = false) String title,
                                                @RequestParam(required = false) String isbn,
                                                @RequestParam(required = false) String authorName,
                                                @RequestParam(required = false) String publisherName,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) String category,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "20") Integer size,
                                                @RequestParam(defaultValue = "title") String sortBy,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction direction,
                                                @RequestParam(defaultValue = "false") boolean unpaged);

    @GetMapping("/{id}")
    Document getDocument(@PathVariable Long id);
}
