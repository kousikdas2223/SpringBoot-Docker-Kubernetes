package com.springboottutorial.accounts.service.client;

import com.springboottutorial.accounts.dto.LoansDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeighnClient {

    @GetMapping(value="/api/fetchLoan")
    public ResponseEntity<LoansDto> fetchLoansDetails(@Valid @RequestParam String mobileNumber);

}
