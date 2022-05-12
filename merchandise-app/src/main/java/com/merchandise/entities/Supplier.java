package com.merchandise.entities;

import java.io.Serializable;
import java.util.*;

/**
 * This is Supplier class used to create Supplier objects.
 * 
 * @author neha15376
 *
 */

public class Supplier extends Merchandise implements Serializable{
	// instance variables
	private double creditBalance;
	private String drivingLicenseNumber;

	// constructor
	public Supplier(int partnerId, String partnerName, String city, String state, double creditBalance,
			String drivingLicenseNumber) throws Exception {
		super(partnerId, partnerName, city, state);
		this.creditBalance = creditBalance;
		this.drivingLicenseNumber = drivingLicenseNumber;
		validate();
	}

	// getter and setters
	public double getCreditBalance() {
		return creditBalance;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		String drivingLicenseNumberPattern = "^[A-Z]{2}[0-9]{2}[0-9]{4}[0-9]{7}";
		if (drivingLicenseNumber != null && drivingLicenseNumber.matches(drivingLicenseNumberPattern))
			this.drivingLicenseNumber = drivingLicenseNumber;
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

		// validate creditBalance
		if (creditBalance > 175000) {
			errorList.add("Credit Balance should not be more than 175000");
		}

		// validate drivingLicenseNumber
		String drivingLicenseNumberPattern = "^[A-Z]{2}[0-9]{2}[0-9]{4}[0-9]{7}";

		if (drivingLicenseNumber == null) {
			errorList.add("Driving license number should not be null.");
		} else if (!drivingLicenseNumber.matches(drivingLicenseNumberPattern)) {
			errorList.add("Driving license number should be in proper format.");
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

	// toString method to generate Supplier object format
	@Override
	public String toString() {
		return "[" + super.toString() + " : " + this.creditBalance + " : " + this.drivingLicenseNumber + "]";
	}

	/**
	 * If a Supplier has same license number, the person should not be repeated in
	 * the array, for that hashCode and equals is overridden
	 */

	// hashCode method to generate hashcode -> attributes used -
	// drivingLicenseNumber as unique to a person
	@Override
	public int hashCode() {
		int result = super.hashCode();
		int drivingLicenseNumberHashCode = drivingLicenseNumber == null ? 0 : drivingLicenseNumber.hashCode();
		return result + drivingLicenseNumberHashCode;
	}

	// equals method to compare two objects -> attributes used -drivingLicenseNumber
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier that = (Supplier) obj;
		if (that.drivingLicenseNumber != null) {
			return this.drivingLicenseNumber.equalsIgnoreCase(that.drivingLicenseNumber);
		} else {
			return false;
		}
	}

}
