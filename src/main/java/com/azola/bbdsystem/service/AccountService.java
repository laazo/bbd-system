package com.azola.bbdsystem.service;

import com.azola.bbdsystem.entity.ClientAccount;

import java.util.List;

public interface AccountService {
    List<ClientAccount> getTransactionalAccountBalances();
    List<ClientAccount> getCurrencyAccounts();
    boolean withdraw(String accountNumber, double amount);
}
