package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.irahul.tbtf.entity.Address;
import com.irahul.tbtf.entity.User;

@Entity
@Table(name="address")
public class AddressImpl implements Address {
	@Id
	@Column(name="idaddress")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="street1")
	private String street1;
	
	@Column(name="street2")
	private String street2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="country")
	private String country;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity=UserImpl.class)
	@JoinColumn(name="users_idusers")	
	private User user;
	
	@Override
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	@Override
	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	@Override
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getId() {
		return id;
	}

	@Override
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AddressImpl [id=" + id + ", street1=" + street1 + ", street2="
				+ street2 + ", city=" + city + ", state=" + state + ", zip="
				+ zip + ", country=" + country + ", userId=" + user.getId() + "]";
	}
}
