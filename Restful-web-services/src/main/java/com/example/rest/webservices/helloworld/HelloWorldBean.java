package com.example.rest.webservices.helloworld;

public class HelloWorldBean {

	private String message;
	public HelloWorldBean(String message) {
		
		this.message=message;
	}
	//we always need a getter for automatic conversion ....otherwise it will throw an error
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	
}
