package com.persistent.securityPractice.armature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.persistent.securityPractice.armature.dto.Contact;
import com.persistent.securityPractice.armature.exceptions.ContactException;
import com.persistent.securityPractice.armature.service.ContactService;
import com.persistent.securityPractice.armature.service.ContactServiceImpl;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {
	private ContactService contactService;

	@Autowired(required = true)
	public ContactController(ContactServiceImpl contactServiceImpl) {
		this.contactService = contactServiceImpl;
	}

	@RequestMapping(value = "/{contact}", method = RequestMethod.POST)
	public @ResponseBody
	String addContact(@RequestParam("contact") Contact contact)
			throws ContactException {
		return "contact";

	}

}
