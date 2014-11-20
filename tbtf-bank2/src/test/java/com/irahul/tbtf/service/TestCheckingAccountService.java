package com.irahul.tbtf.service;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.util.Currency;

@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//ordering tests (not ideal but using this to show get of account lists)
public class TestCheckingAccountService extends AbstractJUnit4SpringContextTests{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	@Autowired
	private UserService userService;
	
	private static User testUser;
		
	@Before
	public void beforeTestClass(){
		//we need a user for all tests here to creating one
		if(testUser==null){
			UserImpl newUser = new UserImpl();
			newUser.setFirstName("test"+new Random().nextInt(99999));
			newUser.setLastName("checking");
			newUser.setPin("9999");		
			testUser = userService.addUser(newUser);
		}
	}	

	@Test
	public void addAndGetCheckingAccount(){		
		CheckingAccountImpl newAccount = new CheckingAccountImpl();
		newAccount.setRoutingNumber(121000238);
		newAccount.setBalance(50);
		newAccount.setAvailableBalance(10);
		newAccount.setCurrency(Currency.USD);
		newAccount.setOwner(testUser);
		
		CheckingAccount addedAccount = checkingAccountService.addAccount(newAccount);
		Assert.assertNotEquals(0, addedAccount.getId());
		Assert.assertEquals(newAccount.getRoutingNumber(), addedAccount.getRoutingNumber());
		
		CheckingAccount found = checkingAccountService.getAccount(addedAccount.getId());		
		Assert.assertEquals(addedAccount.getId(), found.getId());
		
		logger.info("added account "+addedAccount);
	}
	
	@Test
	public void getAllAccount(){
		List<CheckingAccount> accts = checkingAccountService.getAccountsForUser(testUser.getId());
		
		Assert.assertTrue(accts.size()>0);
		
		logger.info("found accounts size="+accts.size());
	}
}
