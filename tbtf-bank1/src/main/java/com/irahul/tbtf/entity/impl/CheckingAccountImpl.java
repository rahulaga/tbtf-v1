package com.irahul.tbtf.entity.impl;

import com.irahul.tbtf.entity.CheckingAccount;

public class CheckingAccountImpl extends AbstractAccount implements	CheckingAccount {
	private long routingNumber;

	@Override
	public long getRoutingNumber() {
		return this.routingNumber;
	}

	public void setRoutingNumber(long routingNumber) {
		this.routingNumber = routingNumber;
	}
}
