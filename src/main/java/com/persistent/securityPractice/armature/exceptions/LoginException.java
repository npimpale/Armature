package com.persistent.securityPractice.armature.exceptions;

public class LoginException extends Exception{
	private String message;
	
	public LoginException(String message){
		this.message= message;
	}
	
	public String getMessage(){
		return message;
	}

}
