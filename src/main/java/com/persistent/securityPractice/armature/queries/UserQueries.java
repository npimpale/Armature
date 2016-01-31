package com.persistent.securityPractice.armature.queries;

public class UserQueries {
	public static final String GET_USER_BY_USERNAME = "SELECT u.* "
			+ "FROM central_tracker.users u "
			+ "WHERE u.userName = :userName " + "and u.isDeleted = 'N' ";

	public static final String GET_USER_PASSWORD_SALT = "select hashedPassword, salt "
			+ "from central_tracker.users "
			+ "where userName = :userName";

	public static final String GET_USER_BY_ID = "select u.* "
			+ "from central_tracker.users u " + "where u.id = :id";

	public static final String IS_FIRST_LOGIN = "select u.isFirstLogin from central_tracker.users u where u.userName = :userName";
}
