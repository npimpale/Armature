package com.persistent.securityPractice.armature.dao;

import java.util.List;

import com.persistent.securityPractice.armature.dto.Project;

public interface ProjectDAO {

	List<Project> getProjectsByUser(long id);

	List<Project> getProjectsByUser(String userName);

	Number addProject(Project project) throws Exception;

	boolean checkProjectExists(String projectName);

	Project getProjectByProject(Long projectId) throws Exception;

}
