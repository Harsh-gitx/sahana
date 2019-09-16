package com.codex.cxcarz.customer.register.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codex.cxcarz.customer.register.dto.CustomerDTO;
import com.codex.cxcarz.customer.register.service.CustomerRegistrationService;

@RestController
@RequestMapping(value = "/")
public class CustomerRegistrationController {

	private Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);
	@Autowired
	private CustomerRegistrationService service;
	public CustomerRegistrationController() {
		System.out.println("inside Customer Register");
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/createCustomer.do", method = RequestMethod.POST)
	public String create(@RequestBody CustomerDTO dto) {
		try {
			System.out.println("inside create");
			System.out.println(dto.getPhoneNumber());
			logger.debug("Entering create method to save "+dto);
			CustomerDTO dtoAfterSave = service.preSave(dto);
			
			if (dtoAfterSave != null) {
				logger.debug("Successfully save the object "+dtoAfterSave);
				service.save(dto);
				//return new ResponseEntity<CustomerDTO>(dtoAfterSave, HttpStatus.CREATED);
				return "success";
			}
			else {
				return "Email Already Exists";
			}
		} catch (Exception e) {
			logger.error("Operation failed due to some exception");
			e.printStackTrace();
			//return new ResponseEntity<CustomerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
			return "Error";

		}
	}
}
