package com.tradevan.pdis.helpdesk.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class SpringSecurityPwdHashTest {
	
	private static BCryptPasswordEncoder passwordEncoder;
	
	@BeforeClass
	public static void runClassWildSetup(){
		passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@AfterClass
	public static void runClassWildTearDown(){
		
	}
	
	@Before
	public void runBeforeEveryTest() {

	}
	
	@After
	public void runAfterEveryTest() {

	}
	
	/**
	 * 對同一個字串hash會得到不同結果，因為ss會每次使用不同salt
	 */
	@Test
	public void testHashSameStringThenGetDifferentResult(){
		int i = 0;
		while (i < 10) {
			String password = "123456";
			String hashedPassword = passwordEncoder.encode(password); 
			System.out.println(password+" ==> "+hashedPassword);
			i++;
		}		
	}
	
	/**
	 * 用來產生已存在帳號的hash密碼，本測試程式沒有使用spring context，所以長度是預設的60位
	 */
	@Test
	public void testHashedPwdGenerator(){
		//password of ssuser
//		String password = "ssuser";
//		String hashedPassword = passwordEncoder.encode(password); 
//		System.out.println(password+" ==> "+hashedPassword);
		//$2a$10$inYUJQIkg8yaNN34T/OR2e093k1nIa0LhX6TONmWoMPUytIur7WD2
		
		//password of ntvfadmin
		String password = "ntvfadmin";
		String hashedPassword = passwordEncoder.encode(password); 
		System.out.println(password+" ==> "+hashedPassword);
		//$2a$10$inYUJQIkg8yaNN34T/OR2e093k1nIa0LhX6TONmWoMPUytIur7WD2
	}
	
	

	
	
	
	
}
