package com.persistent.securityPractice.armature.service;

import com.persistent.securityPractice.armature.dto.Assessment;

public interface ScanService {

	Boolean scan(Assessment assessment) throws Exception;

}
