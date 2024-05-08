package com.workintech.s19d2.service;

import com.workintech.s19d2.dao.AccountRepository;
import com.workintech.s19d2.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }
    public List<Account> findAllAccount(){
        return accountRepository.findAll();
    }


    public Optional<Account> findAccountId(Long id){
        return accountRepository.findById(id);
    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}
