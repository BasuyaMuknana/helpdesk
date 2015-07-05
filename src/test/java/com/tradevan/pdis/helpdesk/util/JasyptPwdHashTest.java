package com.tradevan.pdis.helpdesk.util;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.jasypt.spring.security3.PasswordEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
public class JasyptPwdHashTest {
	
	private PasswordEncoder pwdEncoder;
	
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

	}
	
	/**
	 * 用來產生已存在帳號的hash密碼，本測試程式沒有使用spring context，所以長度是預設的60位
	 */
	@Test
	public void testHashedPwdGenerator(){
		//password of pdisadmin
		String password = "pdisadmin";
		String hashedPassword = pwdEncoder.encodePassword(password, null);
		System.out.println(password+" ==> "+hashedPassword);
		//VBGMb9shaPSr+38311h2/yTDjSb2kin8EKUrOus7Sndc0WWMM7/Tqg==
	}
	
	public PasswordEncoder getPwdEncoder() {
		return pwdEncoder;
	}
	
	@Resource(name="pwdEncoder")
	public void setPwdEncoder(PasswordEncoder pwdEncoder) {
		this.pwdEncoder = pwdEncoder;
	}

	
	
	
	
}
