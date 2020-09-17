package com.azola.bbdsystem.repository;

import com.azola.bbdsystem.entity.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount, String> {

    //Defaulting to client id 1 since there is not ATM login function (yet)
    @Query("select ac from ClientAccount ac where ac.accountType.isTransactional = (:transc) and ac.client.clientId = 1 order by ac.displayBalance desc")
    List<ClientAccount> getClientAccountsByAccountType(@Param("transc") boolean isTransactional);

    @Query("select ac from ClientAccount ac where ac.accountType.isTransactional = false and ac.client.clientId = 1 and ac.currency.currencyCode <> 'ZAR'")
    List<ClientAccount> getClientCurrencyAccounts();

    @Modifying
    @Transactional
    @Query("update ClientAccount set displayBalance = displayBalance - (:amount) where accountNumber = (:acc)")
    void withdraw(@Param("acc") String account, @Param("amount") BigDecimal amount);
}
