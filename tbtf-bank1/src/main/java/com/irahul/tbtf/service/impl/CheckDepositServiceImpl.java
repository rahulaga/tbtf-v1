package com.irahul.tbtf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.service.CheckDepositService;
import com.irahul.tbtf.service.CheckingAccountService;
import com.irahul.tbtf.service.SavingsAccountService;
import com.irahul.tbtf.util.CheckImage;

@Service
public class CheckDepositServiceImpl implements CheckDepositService {		
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	@Autowired
	private SavingsAccountService savingsAccountService;

	@Override
	public void deposit(long accountId, int amount, CheckImage check) {
		//assume we are able to read the image to get the routing number
		//and the account number from where we will withdraw the funds		
		validateCheckImage(check);
		
		//TODO - somehow we queue this check to be processed so we can get the money
		
		int availableNow = (int) (amount*0.2);//only 20% available, the rest is available if check clears 
		
		//find account - need to improve this somehow?
		CheckingAccount checkingAccount = checkingAccountService.getAccount(accountId);
		if(checkingAccount!=null){
			checkingAccountService.deposit(accountId, amount, availableNow);
		}
		else{
			//must be saving since we only have two types right now
			savingsAccountService.deposit(accountId, amount, availableNow);
		}
	}

	private void validateCheckImage(CheckImage check) {
		// TODO Auto-generated method stub
		
	}

}
