package com.springboottutorial.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
        name = "Loans Data Object",
        description = "Loan details"
)
public class LoansDto {

    @Schema(description = "Mobile number of the customer", example = "1234567890")
    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "[0-9]{10}", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Loan number of the customer", example = "John Doe")
    @NotEmpty(message = "Loan number is required")
    @Pattern(regexp = "[0-9]{12}", message = "Loan number should be 12 digits")
    private String loanNumber;

    @Schema(description = "Loan type of the customer", example = "Home Loan")
    @NotEmpty(message = "Loan type is required")
    private String loan_type;

    @Schema(description = "Loan amount of the customer", example = "99999")
    @Positive(message = "Loan amount should be > 0")
    private int total_loan;

    @Schema(description = "Amount paid by the customer", example = "99999")
    @PositiveOrZero(message = "Amount paid should be greater than or equal to 0")
    private int amount_paid;

    @Schema(description = "Outstanding amount of the customer", example = "99999")
    @PositiveOrZero(message = "Outstanding amount should be greater than or equal to 0")
    private int outstanding_amount;

}
