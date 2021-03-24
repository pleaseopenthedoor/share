package com.example.response;

public class BaseResponse<T> {
	
	private int status;
	
	private String message = "success";
	
	private T data;
	
	
	public BaseResponse(T data) {
		super();
		this.data = data;
	}
	

	public BaseResponse(int status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
