package com.irahul.tbtf.repository;

import com.irahul.tbtf.entity.User;

public interface UserRepository {
	
	/**
	 * 
	 * @param user
	 * @return the id of the newly added user
	 */
	long addUser(User user);
	
	User getUser(long userId);

}
