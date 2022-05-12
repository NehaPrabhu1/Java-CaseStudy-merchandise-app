package com.merchandise.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Customer class used to create Customer objects.
 * 
 * @author neha15376
 *
 */

public class Customer extends Merchandise implements Serializable{
	// instance variables
	private double creditLimit;
	private String phoneNo;
	private String email;

	// Constructor
	public Customer(int partnerId, String partnerName, String city, String state, double creditLimit, String phoneNo,
			String email) throws Exception {
		super(partnerId, partnerName, city, state);
		this.creditLimit = creditLimit;
		this.phoneNo = phoneNo;
		this.email = email;
		validate();
	}

	// getter and setter methods
	public double getCreditLimit() {
		return creditLimit;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setPhoneNo(String phoneNo) {
		String phoneNoPattern = "^[1-9]{1}[0-9]{9}";
		if (phoneNo != null && phoneNo.matches(phoneNoPattern))
			this.phoneNo = phoneNo;
	}

	public void setEmail(String email) {
		String emailPattern = "^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}";
		if (email != null && email.matches(emailPattern))
			this.email = email;
	}

	// validate method
	@Override
	public String[] validate() throws Exception {

		// get validation array from Merchandise class
		String[] errors = super.validate();

		// convert array to list
		List<String> errorList = new ArrayList<>();
		for (String error : errors) {
			if (error != null)
				errorList.add(error);
		}

		// validate credtlimit
		if (creditLimit > 50000) {
			errorList.add("Credit Limit should not be more than 50000");
		}

		// validate phoneNo
		String phoneNoPattern = "^[1-9]{1}[0-9]{9}";
		if (phoneNo == null) {
			errorList.add("Phone No. should not be null");
		} else if (!phoneNo.matches(phoneNoPattern)) {
			errorList.add("Phone number should be in proper format.");
		}

		// validate email
		String emailPattern = "^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}";
		if (email == null) {
			errorList.add("Email should not be null.");
		} else if (!email.matches(emailPattern)) {
			errorList.add("Email should be in proper format.");
		}

		if (!errorList.isEmpty()) {
			Exception ex = new Exception();
			for (String error : errorList) {
				ex.addSuppressed(new Exception(error));
			}
			throw ex;
		}

		// convert list to array
		errorList.toArray(errors);
		return errors;
	}

	// toString method to generate Customer object format
	@Override
	public String toString() {
		return "[" + super.toString() + " : " + this.creditLimit + " : " + this.email + " : " + this.phoneNo + "]";
	}

	/**
	 * If a customer has same phone no and email, the person should not be repeated
	 * in the array, for that hashCode and equals is overridden
	 */

	// hashCode method to generate hashcode -> attributes used - phoneNo and email
	// as unique to a person
	@Override
	public int hashCode() {
		int result = super.hashCode();
		int phoneNoHash = phoneNo == null ? 0 : phoneNo.hashCode();
		int emailHashCode = email == null ? 0 : email.hashCode();
		return result + phoneNoHash + emailHashCode;
	}

	// equals method to compare two objects -> attributes used - phoneNo and email
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer that = (Customer) obj;
		if (that.email != null && this.email != null && this.phoneNo != null && that.phoneNo != null) {
			return this.email.equalsIgnoreCase(that.email) && this.phoneNo.equalsIgnoreCase(that.phoneNo);
		} else {
			return false;
		}
	}

}
