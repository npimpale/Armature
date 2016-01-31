package com.persistent.securityPractice.armature.dao;

public interface LoginDAO {
	// Modify pid
	public boolean isNewUser(String userName, String userPwd);

	public String getHash(String uPwd);

	// Modify pid
	public boolean isValidUser(String userName, String hashedString);

	public int getProjectId(String userName);

	public boolean matchNewPwd(String newPwd1, String newPwd2, String oldPwd,
			int pid);
}
