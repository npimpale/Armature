package com.persistent.securityPractice.armature.dto;

public class LoginBean {
	private String message;
	private String len;
	private String caps;
	private String digis;
	private String syms;
	private String smalls;
	private String csrfToken;

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	public String getCaps() {
		return caps;
	}

	public void setCaps(String caps) {
		this.caps = caps;
	}

	public String getDigis() {
		return digis;
	}

	public void setDigis(String digis) {
		this.digis = digis;
	}

	public String getSyms() {
		return syms;
	}

	public void setSyms(String syms) {
		this.syms = syms;
	}

	public String getSmalls() {
		return smalls;
	}

	public void setSmalls(String smalls) {
		this.smalls = smalls;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
