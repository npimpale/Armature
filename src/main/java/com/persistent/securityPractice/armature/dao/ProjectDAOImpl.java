package com.persistent.securityPractice.armature.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.persistent.securityPractice.armature.dao.mapper.ProjectMapper;
import com.persistent.securityPractice.armature.dto.Project;
import com.persistent.securityPractice.armature.queries.ProjectQueries;

@Component
public class ProjectDAOImpl implements ProjectDAO {
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	private SimpleJdbcInsert projectInsert;

	@Autowired(required = true)
	public void setProjectInsert(DataSource dataSource) {
		this.projectInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("central_tracker.projetcs").usingGeneratedKeyColumns("id");
	}

	@Autowired(required = true)
	public void setNamedParaJdbcTemplate(DataSource dataSource) {
		this.namedParaJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Project> getProjectsByUser(long id) {
		List<Project> projectList = new ArrayList<Project>();
		projectList.addAll(namedParaJdbcTemplate.query(
				ProjectQueries.GET_PROJS_BY_USER_ID, new MapSqlParameterSource(
						"user_id", id), new ProjectMapper()));
		return projectList;
	}

	public List<Project> getProjectsByUser(String userName) {
		List<Project> projectList = namedParaJdbcTemplate.query(
				ProjectQueries.GET_PROJS_BY_USER_NAME,
				new MapSqlParameterSource("user_name", userName),
				new ProjectMapper());
		return projectList;
	}

	public Number addProject(Project project) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("user_id", project.getUserId());
		parameters.put("name", project.getName());
		parameters.put("created_by", project.getCreatedBy());
		parameters.put("updated_by", project.getUpdatedBy());
		parameters.put("create_date", new Date());
		parameters.put("update_date", new Date());
		
		return projectInsert.executeAndReturnKey(parameters);
	}

	public boolean checkProjectExists(String projectName) {
		// TODO Auto-generated method stub
		boolean exists = namedParaJdbcTemplate.queryForObject(
				ProjectQueries.CHECK_PROJECT_EXISTS, new MapSqlParameterSource(
						"project_name", projectName), Boolean.class);
		return exists;
	}

	public Project getProjectByProject(Long projectId) throws Exception {
		Project project = null;
		try {
			project = namedParaJdbcTemplate.queryForObject(
					ProjectQueries.GET_PROJECT_BY_ID,
					new MapSqlParameterSource("project_id", projectId),
					new ProjectMapper());
		} catch (Exception e) {
			throw new Exception("Error in project dao while fetching project.",
					e);
		}
		return project;
	}
}
