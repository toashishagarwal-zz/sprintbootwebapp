package com.example.appdirect.services;

import com.example.appdirect.domain.Account;

public interface AccountService {
	Account save(Account a);
	void delete(Integer id);
}
