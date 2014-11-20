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

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestUserService extends AbstractJUnit4SpringContextTests  {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testPin(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");
		
		User added = userService.addUser(newUser);
		Assert.assertEquals(false, userService.isATMPinValid(added.getId(), "12345"));
		Assert.assertEquals(false, userService.isATMPinValid(added.getId(), null));
		Assert.assertEquals(true, userService.isATMPinValid(added.getId(), "1234"));
	}
	
	@Test
	public void addAndGetUser(){
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");
		
		User added = userService.addUser(newUser);
		logger.info("user added "+added);
		Assert.assertNotEquals(0, added.getId());//this should have been created so not zero anymore
		Assert.assertEquals(newUser.getFirstName(), added.getFirstName());
		Assert.assertEquals(newUser.getLastName(), added.getLastName());
		Assert.assertEquals(newUser.getPin(), added.getPin());
		
		User found = userService.getUser(added.getId());
		Assert.assertEquals(found.getId(), added.getId());
		Assert.assertEquals(found.getFirstName(), added.getFirstName());
		Assert.assertEquals(found.getLastName(), added.getLastName());
		Assert.assertEquals(found.getPin(), added.getPin());
	}
	
}
