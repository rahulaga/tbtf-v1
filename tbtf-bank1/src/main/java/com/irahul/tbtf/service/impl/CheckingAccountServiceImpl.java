package com.irahul.tbtf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.service.CheckingAccountService;
import com.irahul.tbtf.service.UserService;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {
	@Autowired
	private UserService userService;

	@Override
	public CheckingAccount getAccount(long accountId) {
				
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CheckingAccount> getAccountsForUser(long userId) {
		//make sure user is valid
		
				
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
