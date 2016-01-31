package com.persistent.securityPractice.armature.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StatusSerializer.class)
public enum REPOSITORIES {
	SVN("svn"), GIT("git"), CVS("cvs"), TFS("tfs");
	
	private final String value;
	
	REPOSITORIES(String value){
		this.value = value;
	}
	
	@JsonValue
	public String value(){
		return this.value;
	}
}
