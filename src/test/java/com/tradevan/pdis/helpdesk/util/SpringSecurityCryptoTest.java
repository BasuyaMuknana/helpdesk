package com.tradevan.pdis.helpdesk.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/business-config.xml" })
/**
 * 用此功能之前先下載 Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files
 * (看jdk版本不同下載6或7，6以下版本不支援此功能)
 * 解壓縮後將檔案放在 ${java.home}/jre/lib/security/下面
 * @author 2904
 *
 */
public class SpringSecurityCryptoTest {
	
	private static CharSequence password;
	private static CharSequence salt;
	
	@BeforeClass
	public static void runClassWildSetup(){
		password = "setyourownpass";
		salt = KeyGenerators.string().generateKey();
		System.out.println("crypto password: " + password);
		System.out.println("crypto salt: " + salt);
	}
	
	@AfterClass
	public static void runClassWildTearDown(){
		password = null;
		salt = null;
	}
	
	@Before
	public void runBeforeEveryTest() {

	}
	
	@After
	public void runAfterEveryTest() {

	}
	
	
	@Test
	public void testTextEncrypt(){
		String testString = "Tell your mama, I am your father!";
		TextEncryptor textEncryptor = Encryptors.queryableText(password, salt);
		String encryptedString = textEncryptor.encrypt(testString);
		String decryptedString = textEncryptor.decrypt(encryptedString);
		assertEquals(testString,decryptedString);

	}
	

	

	
	
	
	
}
