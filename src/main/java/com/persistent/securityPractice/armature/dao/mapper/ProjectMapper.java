package com.persistent.securityPractice.armature.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.persistent.securityPractice.armature.dto.Project;

public class ProjectMapper implements RowMapper<Project> {

	public Project mapRow(ResultSet rs, int arg1) throws SQLException {
		Project project = new Project();
		project.setId(rs.getLong("id"));
		project.setUserId(rs.getLong("user_id"));
		project.setName(rs.getString("name"));
		project.setCreatedBy(rs.getString("created_by"));
		project.setUpdatedBy(rs.getString("updated_by"));
		project.setCreateDate(rs.getDate("create_date"));
		project.setUpdateDate(rs.getDate("update_date"));
		return project;
	}

}
