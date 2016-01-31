package com.persistent.securityPractice.armature.dto;

import java.util.List;

public class PastProductRecord {
	private List<String> product;
	private List<String> application;
	private List<String> roles;	
	
	private List<String> iprange;
	private List<String> url;
	private List<String> hostname;
	private List<String> prodVersion;
	public List<String> getProduct() {
		return product;
	}
	public List<String> getProdVersion() {
		return prodVersion;
	}
	public void setProdVersion(List<String> prodVersion) {
		this.prodVersion = prodVersion;
	}
	public void setProduct(List<String> product) {
		this.product = product;
	}
	public List<String> getApplication() {
		return application;
	}
	public void setApplication(List<String> application) {
		this.application = application;
	}
	public List<String> getIprange() {
		return iprange;
	}
	public void setIprange(List<String> iprange) {
		this.iprange = iprange;
	}
	public List<String> getUrl() {
		return url;
	}
	public void setUrl(List<String> url) {
		this.url = url;
	}
	public List<String> getHostname() {
		return hostname;
	}
	public void setHostname(List<String> hostname) {
		this.hostname = hostname;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
