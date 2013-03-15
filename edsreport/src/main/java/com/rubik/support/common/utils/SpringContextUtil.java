/**
 * 
 */
package com.rubik.support.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class SpringContextUtil {
	private static ApplicationContext applicationContext;
	static{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@SuppressWarnings("unchecked")
	public static<T> T getBean(String name){
		return (T)applicationContext.getBean(name);
	}
}
