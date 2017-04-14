package com.pay;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware{

	private static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext con)throws BeansException {
		context = con;
	}
	
	public static ApplicationContext getContext()
	{
		return context;
	}
}
