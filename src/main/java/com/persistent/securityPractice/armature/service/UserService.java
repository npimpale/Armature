package com.persistent.securityPractice.armature.service;

import java.util.Map;

import com.persistent.securityPractice.armature.dto.User;

public interface UserService {
	public User getUserByUserName(String userName);

	public boolean checkUserExists(String userName);

	public User registerUser(User user);

	public void updateUserPassword(String userName, String previousPassword,
			String newPassword);

	public User getUserByUser(Long userId) throws Exception;

	Map<String, String> getUserDbPasswordAndSalt(String userName);

	public boolean isFirstLogin(String userName);
}
