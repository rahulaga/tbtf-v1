package com.irahul.tbtf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.repository.CheckingAccountRepository;
import com.irahul.tbtf.service.CheckingAccountService;
import com.irahul.tbtf.service.UserService;

@Service
@Transactional//at class level
public class CheckingAccountServiceImpl implements CheckingAccountService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Override
	public CheckingAccount getAccount(long accountId) {
		return checkingAccountRepository.getAccount(accountId);
	}

	@Override
	public List<CheckingAccount> getAccountsForUser(long userId) {
		//make sure user is valid
		User user = userService.getUser(userId);
		if(user==null){
			throw new IllegalArgumentException("Invalid user Id");
		}
				
		//get accounts		
		return checkingAccountRepository.getAccountsForUser(user);
	}

	@Override
	public void deposit(long accountId, int totalDeposit, int availableNow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(long accountId, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CheckingAccount addAccount(CheckingAccount account) {
		long id =  checkingAccountRepository.addAccount(account);
		return getAccount(id);
	}
}
