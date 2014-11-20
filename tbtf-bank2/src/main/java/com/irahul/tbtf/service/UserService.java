package com.irahul.tbtf.service;

import com.irahul.tbtf.entity.User;

public interface UserService {
	
	User addUser(User user);

	User getUser(long userId);
	
	boolean isATMPinValid(long userId, String pin);	
}
