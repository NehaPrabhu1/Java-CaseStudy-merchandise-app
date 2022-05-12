package com.merchandise.services;

import com.merchandise.entities.Customer;
import com.merchandise.entities.Merchandise;
import com.merchandise.entities.Supplier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * This is a Service class used to store objects of class Customer and Supplier
 * created by user in arrays. Also used for providing list of Customers and
 * Suppliers stored and used for searching Customer by Id.
 * 
 * @author neha15376
 *
 */

public class Service {

	// Arrays to store customer and suppliers
	static Customer[] customerArray = new Customer[5];
	static Supplier[] supplierArray = new Supplier[5];

	public static void saveCustomerDetails(Customer cust) throws Exception {
		// HashSet to avoid repetitive objects
		HashSet<Customer> customers = new HashSet<>();

		// add existing customers in array to hashSet
		for (Customer customer : customerArray) {
			if (customer != null) {
				// check if customer with same id exists ignore
				if (customer.getPartnerId() == cust.getPartnerId() || customer.getEmail().equals(cust.getEmail())
						|| customer.getPhoneNo().equals(cust.getPhoneNo())) {
					System.out.println("=== Customer provided with duplicate data. The customer cannot be added. ===");
					return;
				}
				customers.add(customer);
			}
		}

		// add new customer object to hashset and convert hashset to array
		if (isValid(cust)) {
			customers.add(cust);
			customers.toArray(customerArray);
			System.out.println("=== Customer added successfully ===");
		}
	}

	public static List<Customer> getAllCustomers() {
		List<Customer> allCustomer = new ArrayList<>();
		// add all the existing customer objects from array to the list
		for (Customer customer : customerArray) {
			if (customer != null) {
				allCustomer.add(customer);
			}
		}
		Collections.sort(allCustomer, new SortEntityById());
		return allCustomer;
	}

	public static Customer getCustomerById(int customerid) {
		for (Customer customer : customerArray) {
			if (customer != null) {
				if (customer.getPartnerId() == customerid) {
					return customer;
				}
			}
		}
		return null;
	}

	public static void saveSupplierDetails(Supplier sup) throws Exception {
		// HashSet to avoid repetitive objects
		Set<Supplier> suppliers = new HashSet<>();

		// add existing suppliers in array to hashSet
		for (Supplier supplier : supplierArray) {
			if (supplier != null) {
				// checking if supplier with same partnerId or driving license exists
				if (supplier.getPartnerId() == sup.getPartnerId()
						|| supplier.getDrivingLicenseNumber().equalsIgnoreCase(sup.getDrivingLicenseNumber())) {
					System.out.println("=== Supplier provided with duplicate data. The supplier cannot be added. ===");
					return;
				}
				suppliers.add(supplier);
			}
		}

		// add new supplier object to hashSet and convert hashSet to array
		if (isValid(sup)) {
			suppliers.add(sup);
			suppliers.toArray(supplierArray);
			System.out.println("=== Supplier added successfully ===");
		}
	}

	public static List<Supplier> getAllSuppliers() {
		List<Supplier> allSupplier = new ArrayList<>();
		// add all the existing supplier objects from array to the list
		for (Supplier supplier : supplierArray) {
			if (supplier != null) {
				allSupplier.add(supplier);
			}
		}
		Collections.sort(allSupplier, new SortEntityById());
		return allSupplier;
	}

	private static boolean isValid(Merchandise entity) throws Exception {
		if (entity == null) {
			return false;
		}
		String[] validationErrors = entity.validate();

		// Display errors if any
		for (String error : validationErrors) {
			if (error != null) {
				return false;
			}
		}

		return true;

	}

	public static void writeObjects(String entity) {
		if (entity.equalsIgnoreCase("Customer"))
			SaveToPdfFile.writeCustomer(getAllCustomers());
		else if(entity.equalsIgnoreCase("Supplier"))
			SaveToPdfFile.writeSupplier(getAllSuppliers());

	}

}
