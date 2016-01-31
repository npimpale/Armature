package com.persistent.securityPractice.armature.service;

import java.io.IOException;

import javax.xml.transform.TransformerException;

public interface JenkinsJobService {
	public Integer createJob(String configFilePath, String jobName)
			throws TransformerException, IOException, Exception;

	public Boolean pullCode(String jobName) throws Exception;
}
