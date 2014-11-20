package com.irahul.tbtf.service;

import com.irahul.tbtf.util.CheckImage;

public interface CheckDepositService {
	
	void deposit(long accountId, int amount, CheckImage check);

}
