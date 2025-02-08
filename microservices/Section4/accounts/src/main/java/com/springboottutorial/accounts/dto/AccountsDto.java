package com.springboottutorial.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "This DTO holds the accounts related data"
)
public class AccountsDto {

    @Schema(
            description = "Bank account number of the customer"
    )
    @NotEmpty(message = "Account number is required")
    @Pattern(regexp = "[0-9]+", message = "Account number must be a number")
    private long account_number;

    @Schema(
            description = "Bank account type",
            example = "savings/current"
    )
    @NotEmpty(message = "Account type is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Account type must be a string")
    private String account_type;

    @Schema(
            description = "Branch address of the bank"
    )
    @NotEmpty(message = "Branch address is required")
    @Pattern(regexp = "[a-zA-Z]+", message = "Branch address must be a string")
    private String branch_address;
}
