package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.DocumentLight;
import com.openclassrooms.librarybatch.model.RestResponsePage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${app.apiUrl}/documents", value = "documents-api")
public interface DocumentProxy {

    @GetMapping
    RestResponsePage<DocumentLight> getAllDocuments(@RequestParam(defaultValue = "false") boolean unpaged);
}
