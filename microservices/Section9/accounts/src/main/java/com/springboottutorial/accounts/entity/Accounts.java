package com.springboottutorial.accounts.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Entity

public class Accounts extends BaseEntity {

    @Column(name = "customer_id")
    private int customerId;

    @Id
    @Column(name = "account_number")

    private long account_number;

    @Column(name = "account_type")
    private String account_type;

    @Column(name = "branch_address")
    private String branch_address;

}
