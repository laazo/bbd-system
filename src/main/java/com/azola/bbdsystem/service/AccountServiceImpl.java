package com.azola.bbdsystem.service;

import com.azola.bbdsystem.entity.ClientAccount;
import com.azola.bbdsystem.entity.CurrencyConversionRate;
import com.azola.bbdsystem.repository.ClientAccountRepository;
import com.azola.bbdsystem.repository.CurrencyConversionRepository;
import com.azola.bbdsystem.util.ConversionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ClientAccountRepository clientAccountRepo;

    @Autowired
    private CurrencyConversionRepository conversionRepo;

    @Override
    public List<ClientAccount> getTransactionalAccountBalances() {
        List<ClientAccount> accounts = clientAccountRepo.getClientAccountsByAccountType(true);
        accounts.forEach(clientAccount -> {
            clientAccount.setDisplayBalance(clientAccount.getDisplayBalance().setScale(clientAccount.getCurrency().getDecimalPlaces()));
        });
        return accounts;
    }

    @Override
    public List<ClientAccount> getCurrencyAccounts() {
        List<ClientAccount> accounts = clientAccountRepo.getClientCurrencyAccounts();
        accounts.forEach(clientAccount -> {
            BigDecimal currencyBalance = clientAccount.getDisplayBalance();
            CurrencyConversionRate rate = conversionRepo.getCurrencyConversionRateByCode(clientAccount.getCurrency().getCurrencyCode());
            clientAccount.setDisplayBalance(ConversionHelper.convertToRand(currencyBalance, rate));
        });
        accounts.sort(Comparator.comparing(ClientAccount::getDisplayBalance).reversed());
        return accounts;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        ClientAccount account = clientAccountRepo.getOne(accountNumber);
        if(account.getDisplayBalance().compareTo(BigDecimal.valueOf(amount)) >= 0) {
            clientAccountRepo.withdraw(accountNumber, BigDecimal.valueOf(amount));
            return true;
        }
        if(account.getDisplayBalance().compareTo(BigDecimal.valueOf(amount)) < 0 &&"CHQ".equals(account.getAccountType().getAccountTypeCode())) {
            BigDecimal remaining = account.getDisplayBalance().subtract(BigDecimal.valueOf(amount));
            if(!(remaining.compareTo(BigDecimal.valueOf(-10000)) < 0)) {
                clientAccountRepo.withdraw(accountNumber, BigDecimal.valueOf(amount));
                return true;
            }
        }
        return false;
    }


}
