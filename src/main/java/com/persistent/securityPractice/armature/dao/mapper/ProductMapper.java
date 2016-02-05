package com.persistent.securityPractice.armature.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.dto.REPOSITORIES;

public class ProductMapper implements RowMapper<Product> {

	public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setProjectId(rs.getLong("project_id"));
		product.setName(rs.getString("name"));
		product.setVersion(rs.getString("version"));
		switch (rs.getString("repository")) {
		case "svn":
			product.setRepository(REPOSITORIES.SVN);
			break;

		case "git":
			product.setRepository(REPOSITORIES.GIT);
			break;
			
		case "cvs":
			product.setRepository(REPOSITORIES.CVS);
			break;
			
		case "tfs":
			product.setRepository(REPOSITORIES.TFS);
			break;

		}
		product.setUrl(rs.getString("url"));
		product.setUserName(rs.getString("user_name"));
		product.setHashedPassword(rs.getString("hashed_password"));
		product.setCreatedBy(rs.getString("created_by"));
		product.setUpdatedBy(rs.getString("updated_by"));
		product.setCreateDate(rs.getDate("create_date"));
		product.setUpdateDate(rs.getDate("update_date"));

		return product;
	}

}
