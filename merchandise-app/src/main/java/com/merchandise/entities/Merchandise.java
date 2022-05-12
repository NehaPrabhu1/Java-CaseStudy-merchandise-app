package com.merchandise.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is Merchandise class which is a base class.
 * @author neha15376
 *
 */

public abstract class Merchandise implements Serializable{
	//instance variables
	private int partnerId;
	private String partnerName;
	private String city;
	private String state;
	
	//constructor
	public Merchandise(int partnerId, String partnerName, String city, String state){
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.city = city;
		this.state = state;
		try {
			validate();
		} catch (Exception e) {
		}
	}
	
	//getter and setter methods
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPartnerId() {
		return partnerId;
	}
	
	//validate method that returns error array
	public String[] validate() throws Exception{
		ArrayList<String> errorList = new ArrayList<String>();
		
		//validate partnerId
		if(partnerId <=0) {
			errorList.add("Partner Id cannot be negative");
		}
		
		//validate partnerName
		if(partnerName == null) {
			errorList.add("Partner Name cannot be null");
		}
		else if(partnerName.length() < 5) {
			errorList.add("Partner Name should have atleast 5 characters");
		}
		
		//validate city
		if(city == null) {
			errorList.add("City cannot be null");
		}
		else if(city.length() < 3) {
			errorList.add("City should have atleast 3 characters");
		}
		
		//validate state
		if(state == null) {
			errorList.add("State cannot be null");
		}
		else if(state.length() < 3) {
			errorList.add("State should have atleast 3 characters");
		}
		
		//convert list to array
		String[] errors = new String[10];
		errorList.toArray(errors);
		return errors;
	}
	
	//toString method is overridden
	@Override
	public String toString() {
		return this.partnerId + " : "+this.partnerName+" : "+this.city+" : "+this.state;
	}
	
	//equals method -> attributes used partnerName, city and state, partnerId is compared separately
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null) 
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		Merchandise that = (Merchandise) obj;
		if(that.partnerName != null && that.city != null && that.state != null &&
			this.partnerName != null && this.city != null && this.state != null) {
			return this.partnerName.equalsIgnoreCase(that.partnerName)
					&& this.city.equalsIgnoreCase(that.city) && this.state.equalsIgnoreCase(that.state);
		}
		else {
			return false;
		}
	}
	
	//hashCode method to generate hashcode -> attributes used - pa
	@Override
	public int hashCode() {
		int prime = 7907;
		int partnerNameHashCode = partnerName== null ? 0 : partnerName.hashCode();
		int cityHashCode = city == null ? 0: city.hashCode();
		int stateHashCode = state == null ? 0: state.hashCode();
		return prime + partnerNameHashCode+ cityHashCode+ stateHashCode;
	}
	

}
