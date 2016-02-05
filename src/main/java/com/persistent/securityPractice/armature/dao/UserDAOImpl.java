package com.persistent.securityPractice.armature.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.persistent.securityPractice.armature.dao.mapper.UserMapper;
import com.persistent.securityPractice.armature.dto.User;
import com.persistent.securityPractice.armature.queries.UserQueries;

@Component
public class UserDAOImpl implements UserDAO {
	// private Logger LOGGER = Logger.getLogger(UseLogger.class.getName());
	private SimpleJdbcInsert userInsert;
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;

	@Autowired(required = true)
	public void setUserInsert(DataSource dataSource) {
		this.userInsert = new SimpleJdbcInsert(dataSource).withTableName(
				"central_tracker.users").usingGeneratedKeyColumns("id");
	}

	@Autowired(required = true)
	public void setNamedParaJdbcTemplate(DataSource dataSource) {
		this.namedParaJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public User getUserByUserName(String userName) {
		SqlParameterSource parameter = new MapSqlParameterSource("userName",
				userName);
		User user = namedParaJdbcTemplate.queryForObject(
				UserQueries.GET_USER_BY_USERNAME, parameter, new UserMapper());
		return user;
	}

	public void updateUser(User updatedUser) {
		// TODO Auto-generated method stub

	}

	public boolean checkUserExists(String userName) {
		SqlParameterSource parameter = new MapSqlParameterSource("userName",
				userName);
		User user = namedParaJdbcTemplate.queryForObject(
				UserQueries.GET_USER_BY_USERNAME, parameter, new UserMapper());
		return (user != null) ? true : false;
	}

	public Number addUser(User user) {
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(
				user);
		parameterSource.registerSqlType("isFirstLogin",Types.BOOLEAN);
		parameterSource.registerSqlType("isDeleted",Types.BOOLEAN);
		return userInsert.executeAndReturnKey(parameterSource);
	}

	private String createPassword() {
		String password = null;
		int random = 0;
		random = getRandom();
		// String symbol= getSymbol();
		int place = getSymbol();
		password = genPwd(random, place);
		return password;

	}

	private int getSymbol() {
		// String sym=null;
		Random rand = new Random();
		int num = rand.nextInt(5);
		// System.out.println("Generated Random Number between 0 to 5 is : " +
		// num);
		return num;
	}

	private int getRandom() {
		int random = 0;

		// Generate Secure Random number
		try {
			SecureRandom securerandom = SecureRandom.getInstance("SHA1PRNG");
			random = securerandom.nextInt(34523);
			// System.out.println(random);
		} catch (NoSuchAlgorithmException nsae) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, nsae.toString());
			 */
		}
		return random;
	};

	private String genPwd(int random, int place) {
		// String pwd=null;
		String password = null;
		String s1 = null;
		String s2 = null;
		// Generates MD5 hashed string using random number
		try {
			String randomstring = Integer.toString(random);
			// System.out.println("Inside MD5Calculator");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(randomstring.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			// System.out.println("Hash value for pwd: "+randomstring+
			// " is "+hashtext);

			// byte [] hashedpwd;
			// String hi="Hi how r u";
			char[] pwd = new char[10];
			hashtext.getChars(0, 10, pwd, 0);
			password = new String(pwd);
			// password=password.concat(symbol);

			s1 = password.substring(0, place);
			s2 = password.substring(place + 1, password.length());
			String symbol = null;
			if (place == 1) {
				symbol = "!";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}
			if (place == 2) {
				symbol = "@";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}
			if (place == 3) {
				symbol = "#";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}
			if (place == 4) {
				symbol = "$";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}
			if (place == 5) {
				symbol = "*";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}

			if (place == 0) {
				symbol = "@";
				s1 = s1.concat(symbol);
				s1 = s1.concat(s2);
			}

			// System.out.println(pwd);
			// System.out.println("Ypur password is: "+s1);

		} catch (NoSuchAlgorithmException nsae) {

			/*
			 * MyLogger.setup(LOGGER); LOGGER.log(Level.INFO, nsae.toString());
			 */

		}
		return s1;
	}

	public Map<String, String> getUserPasswordAndSalt(String userName) {
		Map<String, String> result = namedParaJdbcTemplate.queryForObject(
				UserQueries.GET_USER_PASSWORD_SALT, new MapSqlParameterSource(
						"userName", userName),
				new RowMapper<Map<String, String>>() {
					public Map<String, String> mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Map<String, String> resultsMap = new HashMap<String, String>();
						resultsMap.put("hashedPassword",
								rs.getString("hashedPassword"));
						resultsMap.put("salt", rs.getString("salt"));
						return resultsMap;
					}
				});
		return result;
	}

	public User getUserByUser(Long userId) throws Exception {
		User user = null;
		try {
			user = namedParaJdbcTemplate.queryForObject(
					UserQueries.GET_USER_BY_ID, new MapSqlParameterSource(
							"id", userId), new UserMapper());
		} catch (Exception e) {
			throw new Exception("Error while getting User Dao", e);
		}
		return user;
	}

	public boolean isFirstLogin(String userName) {
		boolean isFirstLogin = false;
		isFirstLogin = namedParaJdbcTemplate.queryForObject(
				UserQueries.IS_FIRST_LOGIN, new MapSqlParameterSource(
						"userName", userName), Boolean.class);
		return isFirstLogin;
	}
}
