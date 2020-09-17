package com.azola.bbdsystem.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class CurrencyRatePK implements Serializable {
    @OneToOne
    @JoinColumn(name = "CURRENCY_CODE")
    private Currency currency;
}
