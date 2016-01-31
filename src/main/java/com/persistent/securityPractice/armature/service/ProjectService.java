package com.persistent.securityPractice.armature.service;

import java.util.List;

import com.persistent.securityPractice.armature.dto.Project;

public interface ProjectService {
	public long addProject(Project project) throws Exception;

	public List<Project> getProjectListByUser(String userName);

	public List<Project> getProjectListByUser(long id);

	public Project getProjectByProject(Long projectId) throws Exception;
}
