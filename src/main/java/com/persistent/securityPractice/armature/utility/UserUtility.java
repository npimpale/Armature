package com.persistent.securityPractice.armature.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtility {
	
	public static String createHashedPassword(String userPassword, String salt) {
		String hashedPassword = null;
		userPassword = userPassword.trim();
		salt = salt.trim();
		String saltedPassword = salt + userPassword;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(saltedPassword.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashedPassword = number.toString(16);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return hashedPassword;
	}

	public static  String getNewPasswordSalt() {
		RandomStringUtils randomString = new RandomStringUtils();
		return randomString.randomAlphanumeric(32);
	}

}
