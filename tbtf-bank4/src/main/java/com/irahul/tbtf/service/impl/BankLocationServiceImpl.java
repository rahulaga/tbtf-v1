package com.irahul.tbtf.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.repository.BankLocationRepository;
import com.irahul.tbtf.service.BankLocationService;

@Service
@Transactional
public class BankLocationServiceImpl implements BankLocationService {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BankLocationRepository bankLocationRepository;

	@Override
	public BankLocation addBankLocation(BankLocation bankLocation) {
		long newId = bankLocationRepository.addBankLocation(bankLocation);
		return bankLocationRepository.getBankLocation(newId);
	}

	@Override
	public BankLocation getBankLocation(long id) {
		return bankLocationRepository.getBankLocation(id);
	}

}
