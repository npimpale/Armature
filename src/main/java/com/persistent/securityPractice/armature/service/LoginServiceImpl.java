package com.persistent.securityPractice.armature.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.LoginDAO;
import com.persistent.securityPractice.armature.dao.LoginDAOImpl;
import com.persistent.securityPractice.armature.utility.UserUtility;

import sun.misc.BASE64Encoder;

@Service
public class LoginServiceImpl implements LoginService {
	private LoginDAO loginDAO;
	private UserService userService;

	@Autowired(required = true)
	public void setUserService(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
	}

	@Autowired(required = true)
	public void setLoginDAO(LoginDAOImpl loginDAOImpl) {
		this.loginDAO = loginDAOImpl;
	}

	// Modify pid
	public boolean isNewUser(String userName, String userPwd) {
		boolean check = loginDAO.isNewUser(userName, userPwd);
		return check;
	}

	public String getHash(String uPwd) {
		String hashedString = loginDAO.getHash(uPwd);
		return hashedString;
	}

	// Modify pid
	public boolean isValidUser(String userName, String hashedString) {
		boolean check = loginDAO.isValidUser(userName, hashedString);
		return check;
	}

	public boolean matchNewPwd(String newPwd1, String newPwd2, String oldPwd,
			int pid) {
		boolean check = loginDAO.matchNewPwd(newPwd1, newPwd2, oldPwd, pid);
		return check;
	}

	public int getProjectId(String userName) {
		int pid = loginDAO.getProjectId(userName);
		return pid;
	}

	
	private String csrfTokenGenerator(){
		String hashtext=null; 
		String csrfToken=null; 
		String csrfRandom = UserUtility.getNewPasswordSalt();
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(csrfRandom.getBytes());
			BigInteger number = new BigInteger(1,messageDigest);
			hashtext = number.toString(16);
		}catch(NoSuchAlgorithmException nsae){
			nsae.printStackTrace();
		}
		csrfToken=Base64Encoder(hashtext);
		return csrfToken;
	}
	 

	private String Base64Encoder(String str) {
		String encodedBytes=null;
		BASE64Encoder encoder = new BASE64Encoder();
		try{
			encodedBytes = encoder.encodeBuffer(str.getBytes());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		 
		return encodedBytes;
	}

	public boolean authenticateUser(String userName, String userPassword) {
		boolean isAuthenticated = false;
		Map<String, String> passowrdAndSalt = userService
				.getUserDbPasswordAndSalt(userName);
		String dbHashedPassword = passowrdAndSalt.get("hashedPassword");
		String userHashedPassword = UserUtility.createHashedPassword(
				userPassword, passowrdAndSalt.get("salt"));
		if (!dbHashedPassword.isEmpty()
				&& dbHashedPassword.equals(userHashedPassword)) {
			isAuthenticated = true;
		} else {
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	@Override
	public boolean isFirstLogin(String userName) {
		return userService.isFirstLogin(userName);
	}

	@Override
	public String getCSRFToken(String userName) {
		String hashtext = null; 
		String csrfToken = userName + userService.getUserDbPasswordAndSalt(userName).get("salt");
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(csrfToken.getBytes());
			BigInteger number = new BigInteger(1,messageDigest);
			hashtext = number.toString(16);
		}catch(NoSuchAlgorithmException nsae){
			nsae.printStackTrace();
		}
		csrfToken=Base64Encoder(hashtext);
		return csrfToken;
	}

}
