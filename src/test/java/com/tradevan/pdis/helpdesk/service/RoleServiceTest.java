package com.tradevan.pdis.helpdesk.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;




import com.tradevan.pdis.helpdesk.model.Role;
import com.tradevan.pdis.helpdesk.service.RoleService;




import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class RoleServiceTest {
	
	
	@Autowired
	private RoleService roleService;
	
	
	
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
	public void testReadAllRoles(){
		Collection<Role> roles = roleService.readAllRoles();
		assertEquals(2,roles.size());
	}
	
	
//	@Test
//	@Transactional
//	public void testRegister(){
//		User u = userService.register(userExample);
//		User userPersis = userService.queryOneByUsername(userExample.getUserName());
//		//assertSame(u.getId(), userPersis.getId()); //auto generated
//		assertSame(u.getEmail(), userPersis.getEmail());
//		assertSame(u.getPassword(), userPersis.getPassword());
//		assertSame(u.getUserName(), userPersis.getUserName());
//	}

	
	
	
	
	
}
