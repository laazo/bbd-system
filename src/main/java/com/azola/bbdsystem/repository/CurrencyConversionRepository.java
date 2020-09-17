package com.azola.bbdsystem.repository;

import com.azola.bbdsystem.entity.CurrencyConversionRate;
import com.azola.bbdsystem.entity.CurrencyRatePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversionRate, CurrencyRatePK> {
    @Query("select cr from CurrencyConversionRate cr where cr.currency.currency.currencyCode =(:code)")
    CurrencyConversionRate getCurrencyConversionRateByCode(@Param("code") String currencyCode);
}
