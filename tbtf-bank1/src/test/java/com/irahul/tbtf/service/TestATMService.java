package com.irahul.tbtf.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.irahul.tbtf.entity.User;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestATMService extends AbstractJUnit4SpringContextTests   {	
	@Autowired
	private ATMService atmService;
	
	@Test
	public void getUser(){
		User user = atmService.validateAndGetUser(1, "1234");
		
		Assert.assertEquals("Incorrect userId", 1, user.getId());
	}

	@Test
	public void getUserIncorrectPin(){
		User user = atmService.validateAndGetUser(1, "1");
		
		Assert.assertNull("User should not be found",user);
	}
}
