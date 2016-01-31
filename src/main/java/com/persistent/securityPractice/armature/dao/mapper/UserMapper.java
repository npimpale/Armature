package com.persistent.securityPractice.armature.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.persistent.securityPractice.securityscanner.bean.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		// DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		user.setId(rs.getLong("id"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setUserName(rs.getString("userName"));
		user.setEmail(rs.getString("email"));
		user.setOrganization(rs.getString("organization"));
		user.setMobile(rs.getString("mobile"));
		user.setDeskPhone(rs.getString("deskPhone"));
		user.setTitle(rs.getString("title"));
		user.setComments(rs.getString("comments"));
		user.setHashedPassword(rs.getString("hashedPassword"));
		user.setSalt(rs.getString("salt"));
		user.setIsFirstLogin(rs.getBoolean("isFirstLogin"));
		user.setIsDeleted(rs.getBoolean("isDeleted"));
		user.setCreateDate(rs.getDate("createDate"));
		user.setUpdateDate(rs.getDate("updateDate"));
		return user;
	}

}
