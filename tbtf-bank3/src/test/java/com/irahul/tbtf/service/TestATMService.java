package com.irahul.tbtf.service;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.service.exception.InvalidFieldException;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestATMService extends AbstractJUnit4SpringContextTests   {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ATMService atmService;
	
	@Autowired
	private UserService userService;
	
	@Test(expected=InvalidFieldException.class)
	public void getUserNull(){
		atmService.validateAndGetUser(0, "1234");
	}

	@Test
	public void getUserIncorrectPin(){
		User created = createUser();		
		User user = atmService.validateAndGetUser(created.getId(), "0000");		
		Assert.assertNull("User should not be found",user);
	}
	
	@Test
	public void getUserValid(){
		User created = createUser();		
		User user = atmService.validateAndGetUser(created.getId(), "9999");
		logger.info("user validated"+user);
		Assert.assertNotNull("User should not be null",user);		
	}
	
	private User createUser(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("checking");
		newUser.setPin("9999");		
		return userService.addUser(newUser);
	}
}
