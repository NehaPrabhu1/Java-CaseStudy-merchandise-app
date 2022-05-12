package com.merchandise.entities;

import java.util.HashSet;
import java.util.Set;

import com.merchandise.services.Service;

public class TestMerchandise {

	public static void main(String[] args) throws Exception {
		
		Customer c1 = null;
		try {
			c1 = new Customer(1,"John","New York City","New York", 12000, "1234567890","johngmailcom");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Supplier s1= null;
		try {
			s1 = new Supplier(2,"Peter","Chicago","Illionis",120000,"CH0120111234567");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Customer c2= null;
		try {
			c2 = new Customer(3,"Jackie","New York City","New York", 12000, "1234567890","jack@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Supplier s2= null;
		try {
			s2 = new Supplier(4,"Jilly","Chicago","Illionis",12000,"CH0120111234565");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Repetitive
		Customer c3= null;
		try {
			c3 = new Customer(5,"Johnny","New York City","New York", 10000, "1234567890","john@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Supplier s3= null;
		try {
			s3 = new Supplier(6,"Peter","Chicago","Illionis",120000,"CH0120111234567");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Customer c4= null;
		try {
			c4 = new Customer(7,"Johnny","New York City","New York", 10000,"1234567890","john@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Supplier s4 = null;
		try {
			s4 = new Supplier(8,"Peter","Chicago","Illionis",120000,"C-1234");
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		try {
			Service.saveSupplierDetails(s1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Service.saveSupplierDetails(s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Service.saveSupplierDetails(s3);
		System.out.println(Service.getAllSuppliers());
		
		Service.saveCustomerDetails(c2);
		Service.saveCustomerDetails(c3);
		Service.saveCustomerDetails(c4);
		System.out.println(Service.getAllCustomers());
		
		
		
	}

}
