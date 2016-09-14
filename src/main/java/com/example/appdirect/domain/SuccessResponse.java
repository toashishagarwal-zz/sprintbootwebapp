package com.example.appdirect.domain;

public class SuccessResponse extends Response {
	boolean success;
	String accountIdentifier;
	
	public SuccessResponse(boolean success, String accountIdentifier) {
		super();
		this.success = success;
		this.accountIdentifier = accountIdentifier;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
}
