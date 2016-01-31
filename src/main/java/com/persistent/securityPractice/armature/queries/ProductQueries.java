package com.persistent.securityPractice.armature.queries;

public class ProductQueries {

	public static final String GET_PRODUCT_BY_PROJECT_ID = "select p.* "
			+ "from central_tracker.product_info p"
			+ "where p.project_id = :project_id";

	public static final String GET_PRODUCT_BY_PROJECT_NAME = "select pr.* "
			+ "from central_tracker.product_info pr, central_tracker.project_info p "
			+ "where pr.project_id = p.id and p.name = :project_name";

	public static final String GET_PROJECT_PRODUCTS = "select p.* "
			+ "from central_tracker.product_info p, "
			+ "(select max(id) as id, version "
			+ "from product_info where project_id = :project_id "
			+ "group by name) t " + "where p.id = t.id order by p.id";

	public static final String GET_ALL_PROJECTS_PRODUCTS = "select p.* "
			+ "from central_tracker.product_info p, "
			+ "(select max(id) as id, version from product_info where project_id in (:project_ids) group by name) t "
			+ "where p.id = t.id order by p.id;";

	public static final String CHECK_PRODUCT_EXISTS_NAME = "select true as isExists "
			+ "from central_tracker.product_info p "
			+ "where p.name = :product_name limit 1;";

	public static final String CHECK_PRODUCT_EXISTS_VERSION = "select true as isExists "
			+ "from central_tracker.product_info p "
			+ "where p.name = :product_name "
			+ "and p.version = :version limit 1";

	public static final String GET_PRODUCT_BY_ID = "select p.* "
			+ "from central_tracker.product_info p " + "where p.id = :product_id";

}
