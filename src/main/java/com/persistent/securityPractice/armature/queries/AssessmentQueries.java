package com.persistent.securityPractice.armature.queries;

public class AssessmentQueries {

	public static final String UPDATE_ASSESSMENT_BY_ID = "update central_tracker.assessments a "
			+ "set a.user_id = IFNULL(:userId, a.user_id), "
			+ "a.user_name = IFNULL(:userName, a.user_name), "
			+ "a.project_id = IFNULL(:projectId, a.project_id), "
			+ "a.project_name = IFNULL(:projectName, a.project_name), "
			+ "a.product_id = IFNULL(:productId, a.product_id), "
			+ "a.product_name = IFNULL(:productName, a.product_name), "
			+ "a.product_version = IFNULL(:productVersion, a.product_version), "
			+ "a.assessment_type = IFNULL(:type, a.assessment_type), "
			+ "a.status = IFNULL(:status, a.status), "
			+ "a.startdate = IFNULL(:startDate, a.startdate), "
			+ "a.enddate = IFNULL(:endDate, a.enddate), "
			+ "a.updatedate = IFNULL(:updateDate, a.updatedate), "
			+ "a.created_by = IFNULL(:createdBy, a.created_by), "
			+ "a.updated_by = IFNULL(:updatedBy, a.updated_by) "
			+ "where a.id = :id ";
}
