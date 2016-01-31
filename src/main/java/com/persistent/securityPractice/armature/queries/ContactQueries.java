package com.persistent.securityPractice.armature.queries;

public class ContactQueries {
	public static final String GET_CONTACT_BY_PRODUCTID = "select c.* "
			+ "from central_tracker.contact_details c "
			+ "where c.product_id = :product_id";

	public static final String GET_CONTACT_BY_PRODUCTNAME = "select c.* "
			+ "from central_tracker.contact_details c, central_tracker.product_info p "
			+ "where c.product_id = p.id and p.name = :product_name";

	public static final String GET_CONTACTS_BY_PRODUCTIDS = "select c.* "
			+ "from central_tracker.contact_details c "
			+ "where c.product_id in :product_ids ";

	public static final String GET_CONTACTS_BY_USERID = "select c.* "
			+ "from central_tracker.contact_details c, central_tracker.users u "
			+ "where u.id = :user_id and u.user_name = c.created_by ";
}
