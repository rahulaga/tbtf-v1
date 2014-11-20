package com.irahul.tbtf.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public long addUser(User user) {
		return (Long) this.sessionFactory.getCurrentSession().save(user);		
	}

	@Override
	public User getUser(long userId) {
		return (User) this.sessionFactory.getCurrentSession().get(UserImpl.class, userId);
	}

}
