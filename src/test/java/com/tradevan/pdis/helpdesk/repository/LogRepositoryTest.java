package com.tradevan.pdis.helpdesk.repository;

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


import com.tradevan.pdis.helpdesk.model.Content;
import com.tradevan.pdis.helpdesk.model.Log;

import com.tradevan.pdis.helpdesk.repository.ContentRepository;
import com.tradevan.pdis.helpdesk.repository.LogRepository;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class LogRepositoryTest {
		
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private ContentRepository contentRepository;

	
	@BeforeClass
	public static void runClassWildSetup(){
		
	}	
	@AfterClass
	public static void runClassWildTearDown(){
		
	}	
	@Before
	public void runBeforeEveryTest() {
		
	}	
	@After
	public void runAfterEveryTest() {
//		userExample = null;
	}		
	@Test
	public void testFindFilteredLogs(){
		Content contentEx = new Content();
		contentEx = contentRepository.findById(19L);
		
		Collection<Log> logs = logRepository.findFilteredLogs(contentEx);
		assertEquals(logs.size(), 8);
		for(Log l : logs){
			System.out.println("log id["+l.getId()+"]");
		}
	}

	
	
	
	
}
