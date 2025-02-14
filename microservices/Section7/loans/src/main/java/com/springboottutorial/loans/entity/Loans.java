package com.springboottutorial.loans.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loans  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "loan_id")
    private int loan_id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "loan_number")
    private String loanNumber;

    @Column(name = "loan_type")
    private String loan_type;

    @Column(name = "total_loan")
    private int total_loan;

    @Column(name = "amount_paid")
    private int amount_paid;

    @Column(name = "outstanding_amount")
    private int outstanding_amount;

}

