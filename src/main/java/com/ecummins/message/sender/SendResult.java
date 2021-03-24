package com.ecummins.message.sender;

public class SendResult {

	private boolean isSuccess = false;

	private String description;

	public SendResult(boolean isSuccess, String description) {
		super();
		this.isSuccess = isSuccess;
		this.description = description;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getDescription() {
		return description;
	}

}
