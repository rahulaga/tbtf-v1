package com.irahul.tbtf.entity.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.irahul.tbtf.entity.Address;
import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.UserAuditHistory;

@Entity
@Table(name="users")
public class UserImpl implements User {
	@Id
	@Column(name="idusers")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="pin")
	private String pin;
	
	@OneToOne(targetEntity=AddressImpl.class, optional=true, cascade=CascadeType.ALL, mappedBy="user")
	private Address address;
	
	@ManyToMany(targetEntity=BankLocationImpl.class, fetch=FetchType.EAGER)
	@JoinTable(name="users_account_location", joinColumns = { @JoinColumn(name = "users_idusers", nullable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "bank_location_idbank_location", nullable = false) })
	private List<BankLocation> accountLocations;
	
	@OneToMany(mappedBy = "user", targetEntity=UsersAuditHistoryImpl.class, cascade=CascadeType.ALL)	
    private List<UserAuditHistory> userAuditHistory;
	
	public UserImpl(){}		

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

	@Override
	public String getPin() {		
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<BankLocation> getAccountLocations() {
		return accountLocations;
	}

	public void addAccountLocation(BankLocation accountLocation) {
		if(accountLocations==null){
			accountLocations=new ArrayList<BankLocation>();
		}
		accountLocations.add(accountLocation);
	}

	public List<UserAuditHistory> getUserAuditHistory() {
		return userAuditHistory;
	}

	public void addUserAuditHistory(UserAuditHistory userAuditHistory) {
		if(this.userAuditHistory==null){
			this.userAuditHistory = new ArrayList<UserAuditHistory>();
		}
		this.userAuditHistory.add(userAuditHistory);
	}

	@Override
	public String toString() {
		return "UserImpl [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", pin=" + pin + ", addressId="
				+ address +"]";
	}
}
