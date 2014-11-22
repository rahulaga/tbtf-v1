package com.irahul.tbtf.entity;

import java.util.List;

/**
 * A bank customer
 * @author rahul
 *
 */
public interface User {

	long getId();	
	String getFirstName();
	String getLastName();
	String getPin();
	
	Address getAddress();
	
	/**
	 * All locations where this user has a bank account
	 * @return
	 */
	List<BankLocation> getAccountLocations();
	void addAccountLocation(BankLocation accountLocation);
}
