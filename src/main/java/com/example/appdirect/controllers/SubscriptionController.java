package com.example.appdirect.controllers;

import java.io.StringReader;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appdirect.domain.Account;
import com.example.appdirect.domain.ErrorResponse;
import com.example.appdirect.domain.ErrorResponse.ErrorCode;
import com.example.appdirect.domain.Response;
import com.example.appdirect.domain.Subscription;
import com.example.appdirect.domain.SuccessResponse;
import com.example.appdirect.helpers.HttpHelper;
import com.example.appdirect.services.AccountService;
import com.example.appdirect.services.SubscriptionService;

@RestController
@RequestMapping("api/v1/subscription")
public class SubscriptionController {
	
	@Autowired private SubscriptionService subscriptionService;
	@Autowired private AccountService accountService;
	@Autowired private HttpHelper http;
	
	@RequestMapping("create")
	public ResponseEntity<Response> create(@RequestParam("url") String url) {
		System.out.println("Creating Subscription ....");
		String xmlResponse = http.sendGET(url);
		
		System.out.println("Processing the XML response ... ");
		org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
		int accountIdentifier = -1;
		try {
		    org.jdom.Document document = saxBuilder.build(new StringReader(xmlResponse));
		    Element root = document.getRootElement();
		    System.out.println("Type:" + root.getChildText("type"));
		    
	    	String email = root.getChild("creator").getChildText("email");
	    	String firstName = root.getChild("creator").getChildText("firstName");
	    	String lastName = root.getChild("creator").getChildText("lastName");
	    	
	    	System.out.println("\tEmail : " + email);
	    	System.out.println("\tFirst Name : " + firstName);
	    	System.out.println("\tLast Name : " + lastName);
		    
		    // Create Subscription
		    String companyName = root.getChild("payload").getChild("company").getChildText("name");
		    String editionCode = root.getChild("payload").getChild("order").getChildText("editionCode");
		    String marketPlaceBaseUrl = root.getChild("marketplace").getChildText("baseUrl");
		    
		    System.out.println("\tCompany Name : " + companyName);
	    	System.out.println("\tEdition Code : " + editionCode);
	    	System.out.println("\tMarketPlace BaseUrl : " + marketPlaceBaseUrl);
	    	
		    int subscriptionId = createSubscription(companyName,editionCode,marketPlaceBaseUrl);
		    
		    // Create Account
		    accountIdentifier = createAccount(email, firstName, lastName, subscriptionId);
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), ErrorCode.UNKNOWN_ERROR, false), HttpStatus.OK);
		} 
		return new ResponseEntity<>(new SuccessResponse(true, ""+accountIdentifier), HttpStatus.OK);
	}

	private int createAccount(String email, String firstName, String lastName, int subscriptionId) {
		Account account = new Account();
		account.setEmail(email);
		account.setFirstname(firstName);
		account.setLastname(lastName);
		account.setSubscriptionId(subscriptionId);
		account = accountService.save(account);
		
		return account.getId();
	}

	private int createSubscription(String companyName, String editionCode, String marketPlaceBaseUrl) {
		Subscription subscription = new Subscription();
		subscription.setCompanyName(companyName);
		subscription.setEditionCode(editionCode);
		subscription.setMarketPlaceBaseUrl(marketPlaceBaseUrl);
		subscription = subscriptionService.save(subscription);
		return subscription.getId();
	}

	@RequestMapping("cancel")
	public ResponseEntity<Response> cancel(@RequestParam("url") String url) {
		System.out.println("Cancelling Subscription ....");
		String xmlResponse = http.sendGET(url);
		
		System.out.println("Processing the XML response ... ");
		org.jdom.input.SAXBuilder saxBuilder = new SAXBuilder();
		try {
		    org.jdom.Document document = saxBuilder.build(new StringReader(xmlResponse));
		    Element root = document.getRootElement();
		    System.out.println("Type:" + root.getChildText("type"));
		    String email = root.getChild("creator").getChildText("email");
	    	String firstName = root.getChild("creator").getChildText("firstName");
	    	String lastName = root.getChild("creator").getChildText("lastName");
	    	
	    	System.out.println("\tEmail : " + email);
	    	System.out.println("\tFirst Name : " + firstName);
	    	System.out.println("\tLast Name : " + lastName);
	    	
	    	String accountId = root.getChild("payload").getChild("account").getChildText("accountIdentifier");
	    	accountService.delete(Integer.parseInt(accountId));
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), ErrorCode.UNKNOWN_ERROR, false), HttpStatus.OK);
		} 
		return new ResponseEntity<>(new SuccessResponse(true, ""), HttpStatus.OK);
	}
}
 