package com.tradevan.pdis.helpdesk.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tradevan.pdis.helpdesk.model.User;
import com.tradevan.pdis.helpdesk.service.UserService;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class UserServiceTest {
	
	
	@Autowired
	private UserService userService;
	
	private User userExample;
	
	@BeforeClass
	public static void runClassWildSetup(){
		
	}
	
	@AfterClass
	public static void runClassWildTearDown(){
		
	}
	
	@Before
	public void runBeforeEveryTest() {
//		userExample = new User();
//		userExample.setId(3L);
//		userExample.setUserName("JunitTestRegister");
//		userExample.setPassword("junittestregister");
//		userExample.setEmail("junit_register@tradevan.com.tw");
	}
	
	@After
	public void runAfterEveryTest() {
		//id = 1L;
	}

    @Test
    public void test(){

    }
	
	
	

	
	
	
	
}
