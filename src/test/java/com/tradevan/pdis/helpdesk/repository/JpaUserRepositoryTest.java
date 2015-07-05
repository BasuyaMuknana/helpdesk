package com.tradevan.pdis.helpdesk.repository;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.model.User;

import com.tradevan.pdis.helpdesk.repository.RoleRepository;
import com.tradevan.pdis.helpdesk.repository.UserRepository;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class JpaUserRepositoryTest {
		
	@Autowired
	private UserRepository userRepository;
	private User userExample;
//	private long id;
//	private String firstName;
//	private String lastName;
//	private String lastNameOfFirstName;
//	private String firstNameOfLastName;
//	private String newbieFirstName;
//	private String newbieLastName;
	
	@BeforeClass
	public static void runClassWildSetup(){
		
	}	
	@AfterClass
	public static void runClassWildTearDown(){
		
	}	
	@Before
	public void runBeforeEveryTest() {
		userExample = new User();
		//userExample.setId(2L);
		userExample.setUserName("pdisadmin");
		userExample.setPassword("5mn8qvcidb9pmYMWsSVZEyTBjdbfcEZ1LHeP2OojyxE0iPYFx+XQwQ==");
		userExample.setEmail("yun.wu@tradevan.com.tw");
	}	
	@After
	public void runAfterEveryTest() {
		userExample = null;
	}		
	@Test
	public void testFindOneByExample(){
		User u = userRepository.findOneByExample(userExample);
		//assertSame(u.getId(),userExample.getId());
		assertEquals(u.getUserName(),userExample.getUserName());
		assertEquals(u.getEmail(),userExample.getEmail());
		assertEquals(u.getPassword(),userExample.getPassword());
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	
}
