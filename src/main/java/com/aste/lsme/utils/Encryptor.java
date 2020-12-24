package com.aste.lsme.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.FixedStringSaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Component;

import com.aste.lsme.domain.Constants;

@Component
public class Encryptor {

	 
	 private static StandardPBEStringEncryptor basicTextEncryptor;

	private static BasicTextEncryptor basic;
	 public Encryptor(){
		 System.err.println("i m initialized");
		 Encryptor.basicTextEncryptor=new StandardPBEStringEncryptor();
		 basic = new BasicTextEncryptor();
		 basic.setPassword(Constants.ENCRYPTION_KEY);
		 basicTextEncryptor.setPassword(Constants.ENCRYPTION_KEY);
		 basicTextEncryptor.setSaltGenerator(new ZeroSaltGenerator());
	 }
	 
	public static String encrypt(String value){
		 
		if(value !=null)
			if(!value.isEmpty())
				return basicTextEncryptor.encrypt(value);
	
		return value;
	}
	
	public static String decrypt(String value){
		 
		if(value !=null)
			if(!value.isEmpty())
				try {
					return basic.decrypt(value);
				} catch (Exception e) {
					return basicTextEncryptor.decrypt(value);
				}
				
		
		return value;
	}
}
