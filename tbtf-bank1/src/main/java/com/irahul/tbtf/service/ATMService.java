package com.irahul.tbtf.service;

import java.util.List;

import com.irahul.tbtf.entity.Account;
import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.SavingsAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.util.CheckImage;

public interface ATMService {

	User validateAndGetUser(long userId, String pin);
	
	List<CheckingAccount> getCheckingAccounts(long userId);
	
	List<SavingsAccount> getSavingsAccounts(long userId);
	
	List<Account> getAllAccounts(long userId);
	
	void deposit(long accountId, int amount, CheckImage check);
	
	void withdraw(long accountId, int amount);
}
