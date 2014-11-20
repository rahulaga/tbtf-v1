package com.irahul.tbtf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.Account;
import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.SavingsAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.service.ATMService;
import com.irahul.tbtf.service.CheckDepositService;
import com.irahul.tbtf.service.CheckingAccountService;
import com.irahul.tbtf.service.SavingsAccountService;
import com.irahul.tbtf.service.UserService;
import com.irahul.tbtf.util.CheckImage;

@Service
public class ATMServiceImpl implements ATMService {
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@Autowired
	private CheckDepositService checkDepositService;
	
	@Autowired
	private UserService userService;
	

	@Override
	public User validateAndGetUser(long userId, String pin) {
		if(userService.isATMPinValid(userId, pin)){
			return userService.getUser(userId);
		}
		return null;
	}

	@Override
	public List<CheckingAccount> getCheckingAccounts(long userId) {
		return checkingAccountService.getAccountsForUser(userId);
	}

	@Override
	public List<SavingsAccount> getSavingsAccounts(long userId) {
		return savingsAccountService.getAccountsForUser(userId);
	}

	@Override
	public List<Account> getAllAccounts(long userId) {
		List<Account> returnList = new ArrayList<>();
		
		List<CheckingAccount> checkingAccounts = getCheckingAccounts(userId);
		if(checkingAccounts!=null){
			returnList.addAll(checkingAccounts);
		}
		
		List<SavingsAccount> savingAccounts = getSavingsAccounts(userId);
		if(savingAccounts!=null){
			returnList.addAll(savingAccounts);
		}
		
		return returnList;
	}

	@Override
	public void deposit(long accountId, int amount, CheckImage check) {
		
		checkDepositService.deposit(accountId, amount, check);	

	}

	@Override
	public void withdraw(long accountId, int amount) {
		
		
		// TODO Auto-generated method stub

	}

}
