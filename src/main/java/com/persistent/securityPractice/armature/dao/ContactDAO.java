package com.persistent.securityPractice.armature.dao;

import java.util.List;

import com.persistent.securityPractice.armature.dto.Contact;

public interface ContactDAO {
	public Contact getContactByProduct(long productId);

	public Contact getContactByProduct(String productName);

	public List<Contact> getContactsByUser(long userId);

	public List<Contact> getContactsByProductIds(List<Long> productIDList);

	public Number addContact(Contact contact) throws Exception;

}
