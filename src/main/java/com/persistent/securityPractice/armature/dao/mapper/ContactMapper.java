package com.persistent.securityPractice.armature.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import com.persistent.securityPractice.armature.dto.Contact;

public class ContactMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setId(rs.getLong("id"));
		contact.setProject_id(rs.getLong("project_id"));
		contact.setName(rs.getString("poc"));
		contact.setOrganizationName(rs.getString("buname"));
		contact.setDeskPhone(rs.getString("desk_phone"));
		contact.setMobile(rs.getString("mobile"));
		contact.setEmail(rs.getString("email"));
		contact.setDesignation(rs.getString("designation"));
		contact.setComments(rs.getString("comments"));
		contact.setCreatedBy(rs.getString("created_by"));
		contact.setUpdatedBy(rs.getString("updated_by"));
		contact.setCreateDate(rs.getDate("create_date"));
		contact.setUpdateDate(rs.getDate("update_date"));
		return contact;
	}
}
