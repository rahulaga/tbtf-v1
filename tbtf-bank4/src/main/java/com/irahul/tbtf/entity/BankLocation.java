package com.irahul.tbtf.entity;

/**
 * A bank location
 * @author rahul
 *
 */
public interface BankLocation {
	public enum Type{
		BRANCH,
		ATM
	}

	String getName();

	Type getType();

	long getId();
}