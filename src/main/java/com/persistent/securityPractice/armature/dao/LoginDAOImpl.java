package com.persistent.securityPractice.armature.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class LoginDAOImpl implements LoginDAO {
	// private Logger LOGGER = Logger.getLogger(UseLogger.class.getName());

	public boolean isNewUser(String userName, String userPwd) {
		boolean yes = false;
		String t = "0";
		String f = "1";
		String user = null;
		// String passwd=null;
		String checkFstUsr = "SELECT is_new_user FROM user_details WHERE user_name=? AND hashed_pwd=?";
		try {
			/*
			 * Connection con = DBConection.getConnection(); PreparedStatement
			 * pstmt = null; pstmt= con.prepareStatement(checkFstUsr);
			 * 
			 * pstmt.setString(1,userName); pstmt.setString(2,userPwd);
			 * ResultSet rs = null; rs = pstmt.executeQuery(); while(rs.next()){
			 * user= rs.getString(1); //passwd=rs.getString(2);
			 * 
			 * }
			 */
		} catch (Exception ex) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, ex.toString());
			 */

		}
		if (null != user) {
			if (user.equals(t)) {
				return yes;
			} else if (user.equals(f)) {
				yes = true;
				return yes;
			} else {
				return yes;
			}
		} else {
			return yes;
		}
	}

	public String getHash(String uPwd) {
		String hashedString = null;
		String userPassword = uPwd.trim();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(userPassword.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashedString = number.toString(16);
			// String Password= null;

			while (hashedString.length() < 32) {
				hashedString = "0" + hashedString;
			}
			// System.out.println("Hashed pwd of "+newPwd1+" is "+hashtext+" for pid"+pid);
		} catch (NoSuchAlgorithmException ex) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, ex.toString());
			 */

		}
		return hashedString;
	}

	public boolean isValidUser(String username, String hashedString) {
		int i = 0;
		String userName = username.trim();
		String userDeatails = "SELECT * FROM user_details where user_name=? ";
		int db_pid = 0;
		String db_userName = null;
		String db_passWord = null;
		String db_is__new_user = null;
		try {
			/*
			 * Connection con = DBConection.getConnection(); 
			 * PreparedStatement pstmt = null; 
			 * pstmt= con.prepareStatement(userDeatails);
			 * pstmt.setString(1, userName); 
			 * //pstmt.setInt(2,pid); 
			 * ResultSet rs = null; 
			 * rs = pstmt.executeQuery(); 
			 * while(rs.next()){ 
			 * db_pid= rs.getInt(1); 
			 * db_userName = rs.getString(2); 
			 * db_passWord= rs.getString(3); 
			 * db_is__new_user= rs.getString(4); 
			 *}
			 * if(userName.equals(db_userName) &&
			 * hashedString.equals(db_passWord) ){ i=1; }
			 * 
			 * else{ i=0; }
			 */} catch (Exception ex) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, ex.toString());
			 */

		}

		if (i == 1)
			return true;
		else if (i == 0)
			return false;
		else
			return true;
	}

	public int getProjectId(String userName) {
		int pid = 0;
		String query = "SELECT pid FROM user_details WHERE user_name=?";
		try {
			/*
			 * Connection con = DBConection.getConnection(); PreparedStatement
			 * pstmt = null; pstmt= con.prepareStatement(query);
			 * pstmt.setString(1, userName);
			 * 
			 * ResultSet rs = null; rs = pstmt.executeQuery(); while(rs.next()){
			 * pid= rs.getInt(1);
			 * 
			 * }
			 */
		} catch (Exception ex) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, ex.toString());
			 */

		}
		return pid;
	}

	public boolean matchNewPwd(String newPwd1, String newPwd2, String oldPwd,
			int pid) {
		boolean matchPwd = false;
		String db_oldPwd = null;
		String checkOld = "SELECT hashed_pwd FROM user_details WHERE pid=? ";
		try {
			/*
			 * Connection con = DBConection.getConnection(); PreparedStatement
			 * pstmt =null; pstmt = con.prepareStatement(checkOld); ResultSet rs
			 * =null; pstmt.setInt(1, pid); rs=pstmt.executeQuery();
			 * while(rs.next()){ db_oldPwd=rs.getString(1); }
			 */

		} catch (Exception ex) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, ex.toString());
			 */

		}

		if (newPwd1.equals(newPwd2) && oldPwd.equals(db_oldPwd)) {
			matchPwd = true;
			return matchPwd;
		} else {
			return matchPwd;
		}
	}
}
