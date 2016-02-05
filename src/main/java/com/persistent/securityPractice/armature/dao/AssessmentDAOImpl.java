package com.persistent.securityPractice.armature.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.persistent.securityPractice.armature.dto.Assessment;
import com.persistent.securityPractice.armature.queries.AssessmentQueries;

@Component
public class AssessmentDAOImpl implements AssessmentDAO {
	private NamedParameterJdbcTemplate npJdbcTemplate;
	private SimpleJdbcInsert assessmentInsert;

	@Autowired(required = true)
	public void setAssessmentInsert(DataSource dataSource) {
		this.assessmentInsert = new SimpleJdbcInsert(dataSource).withTableName(
				"central_tracker.assessments").usingGeneratedKeyColumns("id");
	}

	@Autowired(required = true)
	public void setNpJdbcTemplate(DataSource dataSource) {
		this.npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Number createAssessment(Assessment assessment) throws Exception {
		Number id = null;
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(
				assessment);
		paramSource.registerSqlType("status", Types.VARCHAR);
		paramSource.registerSqlType("type", Types.VARCHAR);
		id = assessmentInsert.executeAndReturnKey(paramSource);
		
		return id;
	}

	public long updateAssessment(Assessment assessment) {
		long id = 0;
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(
				assessment);
		paramSource.registerSqlType("status", Types.VARCHAR);
		paramSource.registerSqlType("type", Types.VARCHAR);
		id = npJdbcTemplate.update(AssessmentQueries.UPDATE_ASSESSMENT_BY_ID,
				paramSource);
		return id;
	}

}
