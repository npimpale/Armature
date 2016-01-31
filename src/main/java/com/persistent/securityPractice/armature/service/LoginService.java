package com.persistent.securityPractice.armature.service;

public interface LoginService {
	public boolean authenticateUser(String userName, String password);

	public boolean isFirstLogin(String userName);

	public String getCSRFToken(String userName);
}
