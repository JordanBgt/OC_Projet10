package com.openclassrooms.libraryclient.proxy;

import com.openclassrooms.libraryclient.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client to request the API Library loans endpoints
 */
@FeignClient(url = "${app.apiUrl}/loans", name = "loan-api")
public interface LoanProxy {

    String AUTH_TOKEN = "Authorization";

    @GetMapping("/users/{userId}")
    List<Loan> getAllByUser(@PathVariable Long userId, @RequestHeader(AUTH_TOKEN) String bearerToken);

    @PostMapping
    Loan createLoan(@RequestBody Loan loan);

    @GetMapping("/{id}/renewal")
    ResponseEntity<Void> renewLoan(@PathVariable Long id, @RequestHeader(AUTH_TOKEN) String bearerToken);
}
