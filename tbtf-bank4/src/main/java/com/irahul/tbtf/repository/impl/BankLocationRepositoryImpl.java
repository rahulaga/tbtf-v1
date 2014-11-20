package com.irahul.tbtf.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.impl.BankLocationImpl;
import com.irahul.tbtf.repository.BankLocationRepository;

@Repository
public class BankLocationRepositoryImpl implements BankLocationRepository {	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public long addBankLocation(BankLocation bankLocation) {
		return (Long) this.sessionFactory.getCurrentSession().save(bankLocation);		
	}

	@Override
	public BankLocation getBankLocation(long id) {
		return (BankLocation) this.sessionFactory.getCurrentSession().get(BankLocationImpl.class, id);
	}
}
