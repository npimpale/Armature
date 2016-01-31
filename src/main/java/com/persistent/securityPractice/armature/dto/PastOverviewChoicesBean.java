package com.persistent.securityPractice.armature.dto;

import java.util.ArrayList;
import java.util.List;

public class PastOverviewChoicesBean {
	private List<Integer> pastchoices = new ArrayList<Integer>();
	
	public void setPastchoices(List<Integer> pastchoices){
		this.pastchoices= pastchoices;
	}
	
	public List<Integer> getPastchoices(){
		return pastchoices;
	}
}
