package com.irahul.tbtf.service;

import java.util.List;

import com.irahul.tbtf.entity.User;

public interface UserService {
	
	User addUser(User user);

	User getUser(long userId);
	
	/**
	 * Search user by first or last name, partial searches also performed
	 * @param firstName
	 * @param lastName
	 * @return Empty list is returned if none found
	 */
	List<User> getUsers(String firstName, String lastName);
	
	boolean isATMPinValid(long userId, String pin);	
}
