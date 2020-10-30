package com.openclassrooms.librarybatch.proxy;

import com.openclassrooms.librarybatch.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * Feign client to request the API Library loans endpoints
 */
@FeignClient(url = "${app.apiUrl}/loans", name = "loan-api")
public interface LoanProxy {

    @GetMapping("/ended")
    List<Loan> getAllEndedLoans(@RequestHeader("Authorization") String bearerToken);
}
