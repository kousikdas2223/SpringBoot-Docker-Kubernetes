package com.springboottutorial.accounts.service;

import com.springboottutorial.accounts.constants.Accountsconstants;
import com.springboottutorial.accounts.dto.AccountsDto;
import com.springboottutorial.accounts.entity.Accounts;
import com.springboottutorial.accounts.entity.Customer;
import com.springboottutorial.accounts.exception.CustomeralreadyExistsException;
import com.springboottutorial.accounts.exception.ResourceNotFoundException;
import com.springboottutorial.accounts.mapper.AccountsMapper;
import com.springboottutorial.accounts.mapper.CustomerMapper;
import com.springboottutorial.accounts.repository.AccountsRepository;
import com.springboottutorial.accounts.dto.CustomerDto;
import com.springboottutorial.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto) {

        System.out.println("################# Reached the service implementation  #################");

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if(customerOptional.isPresent()){

            throw new CustomeralreadyExistsException("Cuatomer already registered with mobile number "+customer.getMobileNumber());

        }

//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Admin");

        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));


    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto findCustomerByMobileNumber(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer Id", String.valueOf(customer.getCustomerId()))
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateCustomer(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){

            Accounts accounts = accountsRepository.findById(accountsDto.getAccount_number()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Id", String.valueOf(accountsDto.getAccount_number()))
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            int customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "Customer Id", String.valueOf(customerId))
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteCustomer(String mobileNumber) {

        boolean isDeleted = false;

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        int customerId = customer.getCustomerId();


        accountsRepository.deleteByCustomerId(customerId);

        customerRepository.deleteById(customerId);

        isDeleted = true;

        return isDeleted;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccountNumber = (long) (new Random().nextInt(900000000) * 1000000000L);

        newAccount.setAccount_number(randomAccountNumber);
        newAccount.setAccount_type(Accountsconstants.SAVINGS);
        newAccount.setBranch_address(Accountsconstants.ADDRESS);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Admin");
        return newAccount;

    }



}
