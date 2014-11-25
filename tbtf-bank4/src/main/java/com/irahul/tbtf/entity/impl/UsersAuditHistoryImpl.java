package com.irahul.tbtf.entity.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.irahul.tbtf.entity.BankLocation;
import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.UserAuditHistory;
/**
 * Audit log
 * @author rahul
 *
 */
@Entity
@Table(name="users_audit_history")
public class UsersAuditHistoryImpl implements UserAuditHistory {

	@Id
	@Column(name="idusers_audit_history")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="operation_datetime")
	private Date operationDateTime;
	
	@Column(name="operation")
	@Enumerated(EnumType.STRING)
	private Type operation;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=UserImpl.class)
	@JoinColumn(name="users_idusers")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity=BankLocationImpl.class)
	@JoinColumn(name="bank_location_idbank_location")
	private BankLocation bankLocation;

	@Override
	public Date getOperationDateTime() {
		return operationDateTime;
	}

	public void setOperationDateTime(Date operationDateTime) {
		this.operationDateTime = operationDateTime;
	}

	@Override
	public Type getOperation() {
		return operation;
	}

	public void setOperation(Type operation) {
		this.operation = operation;
	}

	@Override
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public BankLocation getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(BankLocation bankLocation) {
		this.bankLocation = bankLocation;
	}

	@Override
	public long getId() {
		return id;
	}
}
