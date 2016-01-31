package com.persistent.securityPractice.armature.dao;

import com.persistent.securityPractice.armature.dto.Assessment;

public interface AssessmentDAO {
	public Number createAssessment(Assessment assessment) throws Exception;

	public long updateAssessment(Assessment assessment) throws Exception;

}
