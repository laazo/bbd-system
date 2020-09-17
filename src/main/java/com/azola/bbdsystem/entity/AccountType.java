package com.azola.bbdsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType {
    @Id
    @Column(name = "ACCOUNT_TYPE_CODE")
    private String accountTypeCode;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "TRANSACTIONAL")
    private boolean isTransactional;
}
