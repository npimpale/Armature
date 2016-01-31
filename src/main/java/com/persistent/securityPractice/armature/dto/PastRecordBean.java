package com.persistent.securityPractice.armature.dto;

public class PastRecordBean {

	private String messgage;
	
	public void setMessage(String message){
		this.messgage=message;
	}
	
	public String getMessage(){
		System.out.println("In get message:"+messgage);
		return messgage;
	}
}
