package com.irahul.tbtf.service;

import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.UserAuditHistory.Type;
import com.irahul.tbtf.entity.impl.AddressImpl;
import com.irahul.tbtf.entity.impl.BankLocationImpl;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.entity.impl.UsersAuditHistoryImpl;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests  {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BankLocationService bankLocationService;
	
	@Test
	public void testPin(){		
		User added = createUser();
		Assert.assertEquals(false, userService.isATMPinValid(0, "12345"));
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
		Assert.assertNull(added.getAddress());//no address present
		
		User found = userService.getUser(added.getId());
		Assert.assertEquals(found.getId(), added.getId());
		Assert.assertEquals(found.getFirstName(), added.getFirstName());
		Assert.assertEquals(found.getLastName(), added.getLastName());
		Assert.assertEquals(found.getPin(), added.getPin());
	}
	
	@Test
	public void addAndGetUserAndAddress(){
		AddressImpl address = new AddressImpl();
		address.setStreet1("123 Main St");
		address.setStreet2("Apt 1");
		address.setCity("Sunnyvale");
		address.setState("CA");
		address.setZip("94087");
		address.setCountry("US");		
		
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");
		
		//adding to both sides
		newUser.setAddress(address);
		address.setUser(newUser);
		
		User added = userService.addUser(newUser);
		logger.info("user added "+added);
		Assert.assertNotEquals(0, added.getId());//this should have been created so not zero anymore
		Assert.assertEquals(newUser.getFirstName(), added.getFirstName());
		Assert.assertEquals(newUser.getLastName(), added.getLastName());
		Assert.assertEquals(newUser.getPin(), added.getPin());
		Assert.assertNotNull(added.getAddress());
		
		User found = userService.getUser(added.getId());
		Assert.assertEquals(found.getId(), added.getId());
		Assert.assertEquals(found.getFirstName(), added.getFirstName());
		Assert.assertEquals(found.getLastName(), added.getLastName());
		Assert.assertEquals(found.getPin(), added.getPin());
		Assert.assertEquals(found.getAddress().getCity(), address.getCity());
	}
	
	@Test
	public void testUserBankLocation(){
		User user1 = createUser();
		User user2 = createUser();
		
		BankLocation location1 = createLocation();
		BankLocation location2 = createLocation();
		
		user1.addAccountLocation(location1);
		user1.addAccountLocation(location2);
		userService.updateUser(user1);
		
		user2.addAccountLocation(location1);
		userService.updateUser(user2);
		
		User lookupUser1 = userService.getUser(user1.getId());
		User lookupUser2 = userService.getUser(user2.getId());
		
		Assert.assertEquals(2,lookupUser1.getAccountLocations().size());
		Assert.assertEquals(1,lookupUser2.getAccountLocations().size());		
	}
	
	@Test
	public void testUserAuditHistory(){
		User user1 = createUser();
		User user2 = createUser();
		
		BankLocation location1 = createLocation();
		BankLocation location2 = createLocation();
		
		UsersAuditHistoryImpl audit1 = new UsersAuditHistoryImpl();
		audit1.setBankLocation(location1);
		audit1.setOperation(Type.CASH_WITHDRAW);
		audit1.setOperationDateTime(new Date());
		audit1.setUser(user1);		
		user1.addUserAuditHistory(audit1);
		
		UsersAuditHistoryImpl audit2 = new UsersAuditHistoryImpl();
		audit2.setBankLocation(location1);
		audit2.setOperation(Type.CHECK_DEPOSIT);
		audit2.setOperationDateTime(new Date());
		audit2.setUser(user1);
		user1.addUserAuditHistory(audit2);
		
		UsersAuditHistoryImpl audit3 = new UsersAuditHistoryImpl();
		audit3.setBankLocation(location2);
		audit3.setOperation(Type.CHECK_DEPOSIT);
		audit3.setOperationDateTime(new Date());
		audit3.setUser(user2);
		user2.addUserAuditHistory(audit3);
		
		
		userService.updateUser(user1);		
		userService.updateUser(user2);
		
		User lookupUser1 = userService.getUser(user1.getId());
		User lookupUser2 = userService.getUser(user2.getId());
			
		Assert.assertEquals(2,lookupUser1.getUserAuditHistory().size());
		Assert.assertEquals(1,lookupUser2.getUserAuditHistory().size());		
	}

	private BankLocation createLocation() {
		BankLocationImpl newLocation = new BankLocationImpl();
		newLocation.setName("tbtf-branch-"+new Random().nextInt(99999));
		newLocation.setType(BankLocation.Type.values()[new Random().nextInt(2)]);
		return bankLocationService.addBankLocation(newLocation);
	}

	private User createUser() {
		UserImpl newUser = new UserImpl();
		newUser.setFirstName("test"+new Random().nextInt(99999));
		newUser.setLastName("lastName");
		newUser.setPin("1234");		
		return userService.addUser(newUser);
	}
}
