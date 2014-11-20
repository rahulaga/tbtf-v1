package com.irahul.tbtf.repository;

import java.util.List;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.User;

public interface CheckingAccountRepository {

	/**
	 * 
	 * @param account
	 * @return id of the newly created account
	 */
	long addAccount(CheckingAccount account);
	
	CheckingAccount getAccount(long accountId);
	
	//showing two ways to accomplish same result
	List<CheckingAccount> getAccountsForUser(long userId);
	
	List<CheckingAccount> getAccountsForUser(User user);
}
