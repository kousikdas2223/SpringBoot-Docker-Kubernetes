package com.springboottutorial.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number is required")
    @Pattern(regexp = "[0-9]+", message = "Account number must be a number")
    private long account_number;

    @NotEmpty(message = "Account type is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Account type must be a string")
    private String account_type;

    @NotEmpty(message = "Branch address is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Branch address must be a string")
    private String branch_address;
}
