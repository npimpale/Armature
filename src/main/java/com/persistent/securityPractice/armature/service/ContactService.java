package com.persistent.securityPractice.armature.service;

import java.util.List;
import java.util.Map;

import com.persistent.securityPractice.armature.dto.Contact;

public interface ContactService {
	public Long addContact(Contact contact) throws Exception;
	
	public List<Contact> fetchContactsByUser(long userId);
	
	public Contact fetchContactbyProduct(String productName);
	
	public Contact fetchContactbyProduct(long productId);

	public Map<Long, Contact> fetchContactsByProductIds(List<Long> productIDList);

}
