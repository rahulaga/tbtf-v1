package com.irahul.tbtf.entity;

/**
 * An address for a User
 * @author rahul
 *
 */
public interface Address {
	String getStreet1();
	String getStreet2();
	String getCity();
	String getState();
	String getZip();
	String getCountry();
	User getUser();
}
