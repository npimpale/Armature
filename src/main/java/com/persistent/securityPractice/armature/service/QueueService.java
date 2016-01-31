package com.persistent.securityPractice.armature.service;

import com.persistent.securityPractice.armature.dto.Assessment;

public interface QueueService {

	boolean addAssessmentToQueue(String zipFilePath, Assessment assessment);

}
