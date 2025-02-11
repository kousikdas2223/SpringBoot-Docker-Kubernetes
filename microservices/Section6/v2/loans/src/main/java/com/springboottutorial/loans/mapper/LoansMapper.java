package com.springboottutorial.loans.mapper;

import com.springboottutorial.loans.dto.LoansDto;
import com.springboottutorial.loans.entity.Loans;

public class LoansMapper {

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoan_type(loansDto.getLoan_type());
        loans.setTotal_loan(loansDto.getTotal_loan());
        loans.setAmount_paid(loansDto.getAmount_paid());
        loans.setOutstanding_amount(loansDto.getOutstanding_amount());

        return loans;
    }

    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoan_type(loans.getLoan_type());
        loansDto.setTotal_loan(loans.getTotal_loan());
        loansDto.setAmount_paid(loans.getAmount_paid());
        loansDto.setOutstanding_amount(loans.getOutstanding_amount());

        return loansDto;
    }
}
