package com.persistent.securityPractice.armature.queries;

public class ProjectQueries {

	public static final String GET_PROJS_BY_USER_ID = "select p.* "
			+ "from central_tracker.project_info p where p.user_id = :user_id";

	public static final String GET_PROJS_BY_USER_NAME = "select p.* "
			+ "from central_tracker.project_info p, central_tracker.users u "
			+ "where u.id = p.user_id and  u.user_name = :user_name";

	public static final String CHECK_PROJECT_EXISTS = "select true as isExists "
			+ "from central_tracker.project_info p "
			+ "where p.name = 'sampleproject'";

	public static final String GET_PROJECT_BY_ID = "select p.* "
			+ "from central_tracker.project_info p " + "where p.id = :project_id";
}
