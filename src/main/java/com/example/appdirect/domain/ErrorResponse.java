package com.example.appdirect.domain;

public class ErrorResponse extends Response {
	private String message;
	private boolean success;
	private ErrorCode errorCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorResponse(String msg, ErrorCode code, boolean success) {
		super();
		this.message = msg;
		this.errorCode = code;
		this.success = success;
	}
	
	public enum ErrorCode {
		ACCOUNT_EXISTS,
		ACCOUNT_NOTFOUND,
		UNKNOWN_ERROR
	}
}
