package com.irahul.tbtf.entity;

import java.util.List;

/**
 * A bank location
 * @author rahul
 *
 */
public interface BankLocation {
	public enum Type{
		BRANCH,
		ATM
	}

	String getName();

	Type getType();

	long getId();
	
	/**
	 * All users with accounts at this location
	 * @return
	 */
	List<User> getUsers();

	void addUser(User user); 
}