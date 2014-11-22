package com.irahul.tbtf.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.User;
/**
 * Bank locations
 * @author rahul
 *
 */
@Entity
@Table(name="bank_location")
public class BankLocationImpl implements BankLocation {

	@Id
	@Column(name="idbank_location")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "accountLocations", targetEntity=UserImpl.class)	
	private List<User> users;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public long getId() {
		return id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		if(users==null){
			users = new ArrayList<User>();
		}
		users.add(user);
	}

	@Override
	public String toString() {
		return "BankLocationImpl [id=" + id + ", name=" + name + ", type="
				+ type + ", usersSize=" + users.size() + "]";
	}
}
