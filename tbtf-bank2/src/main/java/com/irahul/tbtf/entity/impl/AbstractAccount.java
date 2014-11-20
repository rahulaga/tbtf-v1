package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.util.Currency;

@MappedSuperclass
public abstract class AbstractAccount {
	@Id
	@Column(name="idchecking_account")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(targetEntity=UserImpl.class)
	@JoinColumn(name="users_idusers")	
	private User owner;
	
	@Column//eg: no need to specify name since matches column but I prefer to be explicit
	private int balance;
	
	@Column(name="available_balance")
	private int availableBalance;
	
	@Column(name="currency")
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
	
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void deposit(int totalDeposit, int availableNow){
		balance+=totalDeposit;
		availableBalance+=availableNow;
	}
	
	public void withdraw(int amount){
		balance-=amount;
	}

	@Override
	public String toString() {
		return "AbstractAccount [id=" + id + ", owner=" + owner + ", balance="
				+ balance + ", availableBalance=" + availableBalance
				+ ", currency=" + currency + "]";
	}
	
}
