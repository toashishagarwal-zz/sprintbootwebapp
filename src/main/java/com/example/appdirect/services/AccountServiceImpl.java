package com.example.appdirect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdirect.domain.Account;
import com.example.appdirect.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired private AccountRepository accountRepo;
	
	@Override
	public Account save(Account a) {
		return accountRepo.save(a);
	}

	@Override
	public void delete(Integer id) {
		accountRepo.delete(id);
	}

}
