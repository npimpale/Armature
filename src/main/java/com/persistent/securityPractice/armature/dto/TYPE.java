package com.persistent.securityPractice.armature.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TYPE {
	WHITEBOX("whitebox"), BLACKBOX("blackbox");
	
	private final String value;
	
	TYPE(String value){
		this.value = value;
	}
	
	@JsonValue
	public String value(){
		return this.value;
	}

}
