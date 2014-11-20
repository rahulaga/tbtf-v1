package com.irahul.tbtf.entity;

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
}
