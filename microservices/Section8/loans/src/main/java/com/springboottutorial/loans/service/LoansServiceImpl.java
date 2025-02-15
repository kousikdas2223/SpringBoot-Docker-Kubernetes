package com.springboottutorial.loans.service;

import com.springboottutorial.loans.constants.LoansConstants;
import com.springboottutorial.loans.dto.LoansDto;
import com.springboottutorial.loans.entity.Loans;
import com.springboottutorial.loans.exception.LoansAlreadyExistException;
import com.springboottutorial.loans.exception.ResourceNotFoundException;
import com.springboottutorial.loans.mapper.LoansMapper;
import com.springboottutorial.loans.repository.LoansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    
    private LoansRepository loansRepository;

    @Override
    public void createLoans(String mobileNumber) {

        Optional<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);

        if (loans.isPresent()) {
            throw new LoansAlreadyExistException("Loan already exists for mobile number " + mobileNumber);
        }
        else {
            Loans loan = createLoan(mobileNumber);
            loan.setMobileNumber(mobileNumber);
            loansRepository.save(loan);
        }

    }

    private Loans createLoan(String mobileNumber) {
        Loans newLoan = new Loans();

        long randomLoanNumber = (long) (new Random().nextInt(90000) * 1000L);

        newLoan.setLoanNumber(String.valueOf(randomLoanNumber));
        newLoan.setLoan_type(LoansConstants.HOME_LOAN);
        newLoan.setTotal_loan(LoansConstants.HOME_LONE_LIMIT);
        newLoan.setAmount_paid(0);
        newLoan.setOutstanding_amount(LoansConstants.HOME_LONE_LIMIT);
        return newLoan;

    }

    @Override
    public LoansDto fetchLoansDetails(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
            return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoanDetails(LoansDto loansDto) {

        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Loan Number", loansDto.getLoanNumber())
        );

            LoansMapper.mapToLoans(loansDto, loans);
            loansRepository.save(loans);
            return true;
    }

    @Override
    public boolean deleteLoanDetails(String mobileNumber) {

        boolean isDeleted = false;

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );

        loansRepository.deleteById((long) loans.getLoan_id());
        isDeleted = true;
        return isDeleted;
    }
}
