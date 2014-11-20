package com.irahul.tbtf.repository;

import com.irahul.tbtf.entity.BankLocation;

/**
 * Manange BankLocation
 * @author rahul
 *
 */
public interface BankLocationRepository {	
	long addBankLocation(BankLocation bankLocation);
	
	BankLocation getBankLocation(long bankLocationId);
}
