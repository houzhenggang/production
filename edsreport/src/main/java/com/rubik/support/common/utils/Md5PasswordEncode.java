/**
 * 
 * Copyright (c) 2012 rubik All Rights Reserved.
 */
package com.rubik.support.common.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author wyfhrubik@sina.com
 * @version 1.0
 * @since 2011-4-25
 */
public class Md5PasswordEncode {

	private static final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
	
	public static String encodePassword(String normalPassword){
		return md5PasswordEncoder.encodePassword(normalPassword, null);
	}
}
