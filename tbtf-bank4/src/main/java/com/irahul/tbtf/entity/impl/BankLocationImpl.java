package com.irahul.tbtf.entity.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.irahul.tbtf.entity.BankLocation;
/**
 * Bank locations
 * @author rahul
 *
 */
@Entity
@Table(name="bank_location")
public class BankLocationImpl implements BankLocation {

	@Id
	@Column(name="bank_location_idbank_location")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private Type type;

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
}
