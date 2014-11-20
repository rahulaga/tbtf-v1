package com.irahul.tbtf.repository;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.BankLocation.Type;
import com.irahul.tbtf.entity.impl.BankLocationImpl;

/**
 * NOTE - extending a different class - this lets tests join the transaction
 * @author rahul
 *
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class TestBankLocationRepository extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private BankLocationRepository bankLocationRepository;

	@Test
	//@Rollback(false)
	public void addAndGetBankLocation(){
		BankLocationImpl newBankLocation = new BankLocationImpl();
		newBankLocation.setName("testLocation"+new Random().nextInt(99999));
		newBankLocation.setType(Type.ATM);
		
		long  addedBankLocation = bankLocationRepository.addBankLocation(newBankLocation);
		System.out.println("added id "+addedBankLocation);
		Assert.assertNotEquals(0, addedBankLocation);		
		
		BankLocation found = bankLocationRepository.getBankLocation(addedBankLocation);
		Assert.assertEquals(found.getId(), addedBankLocation);
		Assert.assertEquals(found.getName(), newBankLocation.getName());
		Assert.assertEquals(found.getType(), newBankLocation.getType());
	}
}
