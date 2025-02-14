package com.springboottutorial.accounts.mapper;

import com.springboottutorial.accounts.dto.AccountsDto;
import com.springboottutorial.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){

        accountsDto.setAccount_number(accounts.getAccount_number());
        accountsDto.setAccount_type(accounts.getAccount_type());
        accountsDto.setBranch_address(accounts.getBranch_address());
        return accountsDto;

    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){

        accounts.setAccount_number(accountsDto.getAccount_number());
        accounts.setAccount_type(accountsDto.getAccount_type());
        accounts.setBranch_address(accountsDto.getBranch_address());
        return accounts;
    }
}
