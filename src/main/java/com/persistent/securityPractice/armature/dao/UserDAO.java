package com.persistent.securityPractice.armature.dao;

import java.util.Map;

import com.persistent.securityPractice.armature.dto.User;

public interface UserDAO {
	public User getUserByUserName(String userName);

	public Number addUser(User user);

	public void updateUser(User updatedUser);

	public boolean checkUserExists(String userName);

	public User getUserByUser(Long userId) throws Exception;

	public Map<String, String> getUserPasswordAndSalt(String userName);

	public boolean isFirstLogin(String userName);
}
