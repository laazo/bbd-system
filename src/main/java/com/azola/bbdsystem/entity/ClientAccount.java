package com.azola.bbdsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "CLIENT_ACCOUNT")
public class ClientAccount {
    @Id
    @Column(name = "CLIENT_ACCOUNT_NUMBER")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_TYPE_CODE")
    private AccountType accountType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_CODE")
    private Currency currency;

    @Column(name = "DISPLAY_BALANCE")
    private BigDecimal displayBalance;
}
