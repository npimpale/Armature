package com.persistent.securityPractice.armature.dto;

import java.util.ArrayList;
import java.util.List;

public class ReviewREcordBean {
	private List<Integer> queNo;
	private List<String> que;
	private List<String> category;
	private List<List<String>> choices = new ArrayList<List<String>>();
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setQueNo(List<Integer> queNo){
		this.queNo= queNo;
	}
	
	public List<Integer> getQueNo(){
		return queNo;
	}
	
	public void setQue(List<String> que){
		this.que= que;
	}
	
	public List<String> getQue(){
		return que;
	}
	
	public void setCategory(List<String> category){
		this.category=category;
	}
	
	public List<String> getCategory(){
		return category;
	}
	
	public void setChoices(List<List<String>> choices){
		this.choices= choices;
	}
	public List<List<String>> getChoices(){
		return choices;
	}
}
