package com.persistent.securityPractice.armature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.UserDAO;
import com.persistent.securityPractice.armature.dao.UserDAOImpl;
import com.persistent.securityPractice.armature.dto.Project;
import com.persistent.securityPractice.armature.dto.User;
import com.persistent.securityPractice.armature.utility.UserUtility;

@Service
public class UserServiceImpl implements UserService {
	private UserDAO userDao;
	private ProjectService projectService;
	
	@Autowired(required=true)
	public void setUserDao(UserDAOImpl userDaoImpl) {
		this.userDao = userDaoImpl;
	}

	@Autowired(required = true)
	public void setProjectService(ProjectServiceImpl projectServiceImpl) {
		this.projectService = projectServiceImpl;
	}

	public UserServiceImpl() {

	}

	/*
	 * public void updateNewUser(int pid, String hashedString){
	 * userDao.updateNewUser(pid, hashedString); }
	 */

	public void updateUserPassword(String userName, String previousPassword,
			String newPassword) {
		User updatedUser = new User();
		//TODO check for previous password is correct beforeupdating the user password.
		
		updatedUser.setUserName(userName);
		updatedUser.setHashedPassword(newPassword);
		userDao.updateUser(updatedUser);
	}

	public User getUserByUserName(String userName) {
		User user = userDao.getUserByUserName(userName);
		user.setProjectList(populateUserProjects(user.getId()));
		return user;
	}

	public boolean checkUserExists(String userName) {
		return userDao.checkUserExists(userName);
	}

	public User registerUser(User user) {
		long userId = 0;
		String salt = UserUtility.getNewPasswordSalt();
		String userPassword = user.getHashedPassword();
		
		user.setHashedPassword(UserUtility.createHashedPassword(userPassword, salt));
		user.setSalt(salt);
		
		userId = userDao.addUser(user).longValue();
		user.setId(userId);
		return user;
	}

	private List<Project> populateUserProjects(long userId) {
		List<Project> projectList = new ArrayList<Project>();
		projectList.addAll(projectService.getProjectListByUser(userId));
		return projectList;
	}

	public Map<String, String> getUserDbPasswordAndSalt(String userName) {
		Map<String, String> passwordAndSalt = userDao.getUserPasswordAndSalt(userName);
		return passwordAndSalt;
	}

	public User getUserByUser(Long userId) throws Exception {
		User user = null;
		try{
			user = userDao.getUserByUser(userId);
		}catch(Exception e){
			throw new Exception("Error while getting user in service.",e);
		}
		return user;
	}

	public boolean isFirstLogin(String userName) {
		return userDao.isFirstLogin(userName);
	}
}
