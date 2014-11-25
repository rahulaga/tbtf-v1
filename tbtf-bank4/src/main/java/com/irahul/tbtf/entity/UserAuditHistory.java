package com.irahul.tbtf.entity;

import java.util.Date;

public interface UserAuditHistory {
	public enum Type{
		CASH_WITHDRAW,
		CHECK_DEPOSIT,
		UPDATE_PIN
	}

	Date getOperationDateTime();

	Type getOperation();

	User getUser();

	BankLocation getBankLocation();

	long getId();

}