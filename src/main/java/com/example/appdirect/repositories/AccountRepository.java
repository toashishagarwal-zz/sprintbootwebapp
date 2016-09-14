package com.example.appdirect.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.appdirect.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{

}
