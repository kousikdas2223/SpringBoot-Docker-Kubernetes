package com.springboottutorial.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(
    name = "Cards Data Transfer Object",
   description = "Holds the card details"
)

public class CardsDto {

    @Column(name="mobile_number")
    @NotEmpty(message="Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message="Mobile number must be 10 digits")
    private String mobileNumber;

    @Column(name="card_number")
    @NotEmpty
    @Pattern(regexp = "^[0-9]{16}$", message="Card number must be 16 digits")
    private String cardNumber;

    @Column(name="card_type")
    @NotEmpty
    @Pattern(regexp = "^(Credit|Debit)$", message="Card type must be Credit or Debit")
    private String cardType;

//    @Column(name="card_limit")
//    @Positive(message="Card limit must be positive")
//    private int cardLimit;

    @Column(name="total_limit")
    @Positive(message="Total limit must be positive")
    private int totalLimit;

    @Column(name="amount_used")
    @PositiveOrZero(message="Amount used must be non-negative")
    private int amountUsed;

    @Column(name="available_amount")
    @PositiveOrZero(message="Available amount must be non-negative")
    private int availableAmount;
}
