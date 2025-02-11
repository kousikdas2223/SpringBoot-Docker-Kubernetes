package com.springboottutorial.loans.service;

import com.springboottutorial.loans.dto.LoansDto;
import org.springframework.http.ResponseEntity;

public interface ILoansService {

    void createLoans(String mobileNumber);

    LoansDto fetchLoansDetails(String mobileNumber);

    boolean updateLoanDetails(LoansDto loansDto);

    boolean deleteLoanDetails(String mobileNumber);
}
