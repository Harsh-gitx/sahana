package com.codex.cxcarz.customer.register.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codex.cxcarz.customer.register.dto.CustomerDTO;

@Repository
public class CustomerRegisterRepositoryImpl implements CustomerRegisterRepository {
   
	private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterRepositoryImpl.class);
	
	
	@Autowired
	private SessionFactory factory;
	public CustomerDTO searchByMailId(String emailId) throws Exception {
	 Session session = null;
		try {
	logger.debug("Entering searchByMailId method");	
	session = factory.openSession();

	Criteria criteria = session.createCriteria(CustomerDTO.class);
	criteria.add(Restrictions.ilike("emailId", emailId));
	CustomerDTO dtoPresent =(CustomerDTO)criteria.uniqueResult();
	return dtoPresent;
		} catch (Exception e) {
	logger.error("Operation failed due to some exception");
	e.printStackTrace();
	}finally {
		session.close();
	}
		return null;
	}
	public CustomerDTO save(CustomerDTO dto) {
		 Session session = null;
			try {
		logger.debug("Entering searchByMailId method");	
		session = factory.openSession();
		session.save(dto);
		session.beginTransaction().commit();
			
			}
			finally {
				
			}
			return dto;

	}
}
