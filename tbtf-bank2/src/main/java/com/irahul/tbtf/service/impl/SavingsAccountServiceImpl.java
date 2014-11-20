package com.irahul.tbtf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.SavingsAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.service.SavingsAccountService;
import com.irahul.tbtf.service.UserService;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {
	@Autowired
	private UserService userService;

	@Override
	public SavingsAccount getAccount(long accountId) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavingsAccount> getAccountsForUser(long userId) {
		//make sure user is valid
		User user = userService.getUser(userId);
		if(user==null){
			//throw Exception?
		}
		
		//get accounts
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(long accountId, int totalDeposit, int availableNow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(long accountId, int amount) {
		// TODO Auto-generated method stub
		
	}
}
