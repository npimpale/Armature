package com.persistent.securityPractice.armature.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.securityPractice.armature.dao.mapper.ContactMapper;
import com.persistent.securityPractice.armature.dto.Contact;
import com.persistent.securityPractice.armature.queries.ContactQueries;

@Component
public class ContactDAOImpl implements ContactDAO {
	private NamedParameterJdbcTemplate npJdbcTemplate;
	private SimpleJdbcInsert contactInsert;

	@Autowired(required = true)
	public void setContactInsert(DataSource dataSource) {
		this.contactInsert = new SimpleJdbcInsert(dataSource).withTableName(
				"central_tracker.contact_details").usingGeneratedKeyColumns(
				"id");
	}

	@Autowired(required = true)
	public void setNpJdbcTemplate(DataSource dataSource) {
		this.npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public Contact getContactByProduct(long productId) {
		Contact contact = npJdbcTemplate.queryForObject(
				ContactQueries.GET_CONTACT_BY_PRODUCTID,
				new MapSqlParameterSource("product_id", productId),
				new ContactMapper());
		return contact;
	}

	@Override
	public Contact getContactByProduct(String productName) {
		Contact contact = npJdbcTemplate.queryForObject(
				ContactQueries.GET_CONTACT_BY_PRODUCTNAME,
				new MapSqlParameterSource("product_name", productName),
				new ContactMapper());
		return contact;
	}

	@Override
	public List<Contact> getContactsByUser(long userId) {
		List<Contact> contactList = npJdbcTemplate.query(
				ContactQueries.GET_CONTACTS_BY_USERID,
				new MapSqlParameterSource("user_id", userId),
				new ContactMapper());
		return contactList;
	}

	@Override
	public List<Contact> getContactsByProductIds(List<Long> productIDList) {
		List<Contact> contactList = npJdbcTemplate.query(
				ContactQueries.GET_CONTACTS_BY_PRODUCTIDS,
				new MapSqlParameterSource("product_ids", productIDList),
				new ContactMapper());
		return contactList;
	}

	@Override
	public Number addContact(Contact contact) throws Exception {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(contact);
		return contactInsert.executeAndReturnKey(parameterSource);
	}

}
