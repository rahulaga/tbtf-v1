package com.irahul.tbtf.entity.impl;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.util.Currency;

public abstract class AbstractAccount {
	private long id;
	private User owner;
	private int balance;
	private int availableBalance;
	private Currency currency;
	
	public long getId() {
		return id;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getAvailableBalance() {
		return availableBalance;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public void deposit(int totalDeposit, int availableNow){
		balance+=totalDeposit;
		availableBalance+=availableNow;
	}
	
	public void withdraw(int amount){
		balance-=amount;
	}
}
