package com.codex.cxcarz.customer.register.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codex.cxcarz.customer.register.dto.CustomerDTO;
import com.codex.cxcarz.customer.register.repository.CustomerRegisterRepository;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationServiceImpl.class);
    
	@Autowired
	private CustomerRegisterRepository repository;
	public CustomerDTO preSave(CustomerDTO dto) throws Exception {
		try {
			LOGGER.debug("Entering preSave method " + dto);
			if (!StringUtils.isEmpty(dto.getEmailId()) && !StringUtils.isEmpty(dto.getFirstName())
					&& !StringUtils.isEmpty(dto.getLastName())) {
				LOGGER.debug("Completed validation for String types");
				CustomerDTO dtoPresent= repository.searchByMailId(dto.getEmailId());
				if(dtoPresent!=null) {
					return null;
				}
				if(Objects.nonNull(dto.getPhoneNumber())&&Objects.nonNull(dto.getDateOfBirth()))
				{
					
					LOGGER.debug("Completed validation for non-String types");
					return dto;
				    	
				}

			}
		} catch (Exception e) {

		}
		return null;
	}
	public CustomerDTO save(CustomerDTO dto) {
		repository.save(dto);
		return dto;
	}

}
