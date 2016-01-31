package com.persistent.securityPractice.armature.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StatusSerializer.class)
public enum STATUS {
	NEW("new"), IN_PROGRESS("In Progress"), COMPLETED("Completed"), FAILED("Failed");
	
	private final String value;
	
	STATUS(String value){
		this.value = value;
	}
	
	@JsonValue
	public String value(){
		return this.value;
	}
}
