package com.example.appdirect.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdirect.domain.Subscription;
import com.example.appdirect.repositories.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired private SubscriptionRepository subscriptionRepo;
	
	@Override
	public Subscription save(Subscription s) {
		return subscriptionRepo.save(s);
	}
}
