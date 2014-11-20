package com.irahul.tbtf.entity.impl;

import com.irahul.tbtf.entity.User;

public class UserImpl implements User {
	private long id;
	private String firstName;
	private String lastName;
		
	public UserImpl(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
