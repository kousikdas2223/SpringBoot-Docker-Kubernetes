package com.springboottutorial.accounts.service;

import com.springboottutorial.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - customer DTO object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto findCustomerByMobileNumber(String mobileNumber);

    boolean updateCustomer(CustomerDto customerDto);

    boolean deleteCustomer(String mobileNumber);
}
