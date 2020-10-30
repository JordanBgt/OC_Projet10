package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Exemplar;
import com.openclassrooms.libraryclient.model.ExemplarAvailable;
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

    @GetMapping("/{id}")
    Exemplar getExemplar(@PathVariable Long id);
}
