package com.azola.bbdsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CURRENCY")
public class Currency {
    @Id
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Basic
    @Column(name = "DECIMAL_PLACES")
    private Integer decimalPlaces;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
}
