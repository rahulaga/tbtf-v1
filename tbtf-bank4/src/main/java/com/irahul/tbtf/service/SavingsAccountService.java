package com.irahul.tbtf.service;

import java.util.List;

import com.irahul.tbtf.entity.SavingsAccount;

public interface SavingsAccountService {
	
	SavingsAccount getAccount(long accountId);
	
	List<SavingsAccount> getAccountsForUser(long userId);
	
	void deposit(long accountId, int totalDeposit, int availableNow);
	
	void withdraw(long accountId, int amount);
}
