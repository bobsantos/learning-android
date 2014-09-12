package com.learningandroid.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtils {
	private static final String MD5 = "MD5";
	
	public static final String md5(final String text){
		String hash = "";
		
		try {
			MessageDigest digest = MessageDigest.getInstance(MD5);
			digest.update(text.getBytes(),0,text.length());
			hash = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hash;
	}
}
