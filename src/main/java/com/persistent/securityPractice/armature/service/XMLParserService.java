package com.persistent.securityPractice.armature.service;

import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.dto.REPOSITORIES;

public interface XMLParserService {
	public String createJenkinsConfigFile(REPOSITORIES repo, String userName,
			Product product) throws Exception;
}
