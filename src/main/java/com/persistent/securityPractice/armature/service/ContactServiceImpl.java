package com.persistent.securityPractice.armature.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.ContactDAO;
import com.persistent.securityPractice.armature.dao.ContactDAOImpl;
import com.persistent.securityPractice.armature.dto.Contact;

@Service
public class ContactServiceImpl implements ContactService{
	private ContactDAO contactDao;
	
	@Autowired(required=true)
	public void setContactDao(ContactDAOImpl contactDaoImpl) {
		this.contactDao = contactDaoImpl;
	}

	public Contact fetchContactbyProduct(long productId){
		Contact contact = contactDao.getContactByProduct(productId);
		return contact; 
	}
	
	public Contact fetchContactbyProduct(String productName){
		Contact contact = contactDao.getContactByProduct(productName);
		return contact;
	}
	
	public List<Contact> fetchContactsByUser(long userId){
		List<Contact> contactList = contactDao.getContactsByUser(userId);
		return contactList;
	}
	
	public Long addContact(Contact contact) throws Exception{
		Long id = null;
		try{
			id = contactDao.addContact(contact).longValue();
		}catch(Exception e){
			throw new Exception("Error in ContactService while adding contact", e);
		}
		return id;
	}

	public Map<Long, Contact> fetchContactsByProductIds(List<Long> productIDList) {
		Map<Long, Contact> productContactMap = new HashMap<Long, Contact>();
		List<Contact> contactList = contactDao.getContactsByProductIds(productIDList);
		//TODO remaining some operations
		
		return productContactMap;
	}
}
