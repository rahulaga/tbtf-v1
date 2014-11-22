package com.irahul.tbtf.service;

import com.irahul.tbtf.entity.BankLocation;

/**
 * Bank locations
 * @author rahul
 *
 */
public interface BankLocationService {
	
	BankLocation addBankLocation(BankLocation bankLocation);

	BankLocation getBankLocation(long id);	
}
