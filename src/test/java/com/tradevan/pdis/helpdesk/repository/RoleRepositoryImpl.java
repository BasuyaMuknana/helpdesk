package com.tradevan.pdis.helpdesk.repository;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tradevan.pdis.helpdesk.model.Role;

import com.tradevan.pdis.helpdesk.repository.RoleRepository;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class RoleRepositoryImpl {
		
	@Autowired
	private RoleRepository roleRepository;
	private Role roleExample;
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
		roleExample = new Role();
		roleExample.setRoleName("ROLE_ADMIN");
		roleExample.setRoleDesc("admin role");
		roleExample.setId(1L);
	}	
	@After
	public void runAfterEveryTest() {
		roleExample = null;
	}	
	
	@Test
	public void testFindAll(){
		Collection<Role> roles = roleRepository.findAll();
		assertEquals(2,roles.size());
//		assertEquals(r.getRoleName(),roleExample.getRoleName());
//		assertEquals(r.getRoleDesc(),roleExample.getRoleDesc());
	}
	
	@Test
	public void testFindOneByExample(){
		Role r = roleRepository.findOneByExample(roleExample);
		assertSame(r.getId(),roleExample.getId());
		assertEquals(r.getRoleName(),roleExample.getRoleName());
		assertEquals(r.getRoleDesc(),roleExample.getRoleDesc());
	}
	
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}	
	
	
	
}
