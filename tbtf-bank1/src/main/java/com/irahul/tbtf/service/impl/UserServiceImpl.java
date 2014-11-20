package com.irahul.tbtf.service.impl;

import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return new UserImpl(userId);
	}

	@Override
	public boolean isATMPinValid(long userId, String pin) {
		// TODO Auto-generated method stub
		if(userId==1 && "1234".equals(pin)){
			return true;
		}
		return false;
	}

}
