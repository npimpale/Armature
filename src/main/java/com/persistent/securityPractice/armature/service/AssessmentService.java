package com.persistent.securityPractice.armature.service;

import com.persistent.securityPractice.armature.dto.Assessment;

public interface AssessmentService {
	public Number createAssessment(Assessment assessment) throws Exception;

	public long updateAssessment(Assessment assessment) throws Exception;
}
