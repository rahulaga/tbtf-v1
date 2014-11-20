package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.irahul.tbtf.entity.CheckingAccount;

@Entity
@Table(name="checking_account")
public class CheckingAccountImpl extends AbstractAccount implements	CheckingAccount {
	@Column(name="routing_number")
	private long routingNumber;

	@Override
	public long getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(long routingNumber) {
		this.routingNumber = routingNumber;
	}

	@Override
	public String toString() {
		return "CheckingAccountImpl [routingNumber=" + routingNumber
				+ ", toString()=" + super.toString() + "]";
	}
	
}
