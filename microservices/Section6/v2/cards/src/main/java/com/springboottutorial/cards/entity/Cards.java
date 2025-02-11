package com.springboottutorial.cards.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cards extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "card_id")
    private int cardId;

    @Column(name="mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name="card_number", nullable = false)
    private String cardNumber;

    @Column(name="card_type", nullable = false)
    private String cardType;

    @Column(name="card_limit", nullable = false)
    private int cardLimit;

    @Column(name="total_limit", nullable = false)
    private int totalLimit;

    @Column(name="amount_used", nullable = false)
    private int amountUsed;

    @Column(name="available_amount", nullable=false)
    private int availableAmount;
}

