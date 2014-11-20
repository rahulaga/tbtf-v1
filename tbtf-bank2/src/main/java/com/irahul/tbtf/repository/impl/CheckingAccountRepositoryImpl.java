package com.irahul.tbtf.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irahul.tbtf.entity.CheckingAccount;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.CheckingAccountImpl;
import com.irahul.tbtf.repository.CheckingAccountRepository;

@Repository
public class CheckingAccountRepositoryImpl implements CheckingAccountRepository {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public long addAccount(CheckingAccount account) {
		return (Long) this.sessionFactory.getCurrentSession().save(account);
	}

	@Override
	public CheckingAccount getAccount(long accountId) {
		return (CheckingAccount) this.sessionFactory.getCurrentSession().get(CheckingAccountImpl.class, accountId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckingAccount> getAccountsForUser(long userId) {		
		
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(CheckingAccountImpl.class)
				.add(Restrictions.eq("owner.id", userId));		
		List<CheckingAccount> checkingAccts = crit.list();
		
		return checkingAccts;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckingAccount> getAccountsForUser(User user) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(CheckingAccountImpl.class)
				.add(Restrictions.eq("owner", user));		
		List<CheckingAccount> checkingAccts = crit.list();
		
		return checkingAccts;
	}
}
