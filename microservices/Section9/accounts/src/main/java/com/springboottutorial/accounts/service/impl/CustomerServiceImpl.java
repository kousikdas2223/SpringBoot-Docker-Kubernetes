package com.springboottutorial.accounts.service.impl;

import com.springboottutorial.accounts.dto.AccountsDto;
import com.springboottutorial.accounts.dto.CardsDto;
import com.springboottutorial.accounts.dto.CustomerDetailsDto;
import com.springboottutorial.accounts.dto.LoansDto;
import com.springboottutorial.accounts.entity.Accounts;
import com.springboottutorial.accounts.entity.Customer;
import com.springboottutorial.accounts.exception.ResourceNotFoundException;
import com.springboottutorial.accounts.mapper.AccountsMapper;
import com.springboottutorial.accounts.mapper.CustomerMapper;
import com.springboottutorial.accounts.repository.AccountsRepository;
import com.springboottutorial.accounts.repository.CustomerRepository;
import com.springboottutorial.accounts.service.ICustomerService;
import com.springboottutorial.accounts.service.client.CardsFeignClient;
import com.springboottutorial.accounts.service.client.LoansFeighnClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {


    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeighnClient loansFeighnClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer Id", String.valueOf(customer.getCustomerId()))
        );


        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));



        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardsDetails(mobileNumber, correlationId);

        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeighnClient.fetchLoansDetails(mobileNumber, correlationId);

        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());


        return customerDetailsDto;
    }
}
