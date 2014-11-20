package com.irahul.tbtf.entity.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.irahul.tbtf.entity.SavingsAccount;

@Entity
@Table(name="savings_account")
public class SavingsAccountImpl extends AbstractAccount implements SavingsAccount {

}
