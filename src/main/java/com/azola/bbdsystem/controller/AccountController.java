package com.azola.bbdsystem.controller;

import com.azola.bbdsystem.entity.ClientAccount;
import com.azola.bbdsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "atm";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(@RequestParam(name = "type") String type, @RequestParam(name = "number") String account) {
        return "withdraw";
    }

    @RequestMapping(value = "/withdrawProcess", method = RequestMethod.POST)
    public ModelAndView withdrawProcess(@RequestParam(name = "amount") Double amount, @RequestParam(name = "number") String account) {
        boolean isSuccess = accountService.withdraw(account, amount);
        ModelAndView map = new ModelAndView("withdraw");
        if(isSuccess) {
            map.addObject("message", "Success");
        }
        else {
            map.addObject("message", "Insufficient funds");
        }
        return map;
    }

    @RequestMapping(value="/transactional-accounts", method = RequestMethod.GET)
    public ModelAndView listTransactionalAccounts() {
        List<ClientAccount> accountList = accountService.getTransactionalAccountBalances();
        ModelAndView map = new ModelAndView("trans-accounts");
        map.addObject("accounts", accountList);
        return map;
    }
}
