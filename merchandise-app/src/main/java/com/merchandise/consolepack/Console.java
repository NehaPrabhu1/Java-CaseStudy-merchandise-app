package com.merchandise.consolepack;

import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.merchandise.entities.Customer;
import com.merchandise.entities.Merchandise;
import com.merchandise.entities.Supplier;
import com.merchandise.services.Service;

public class Console {

	public static void main(String[] args) {

		char choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n========== Choose one of the following options ================");
			System.out.println("a. Add Customer");
			System.out.println("b. Add Supplier");
			System.out.println("c. Display Report");
			System.out.println("d. Search");
			System.out.println("e. Exit");
			System.out.println("================================================================");
			System.out.print("Enter your choice: ");
			choice = sc.next().charAt(0);
			switch (choice) {
			case 'a': // add customer
				System.out.println("\n=== For adding customer ===");
				createEntity("Customer");
				break;
			case 'b': // add supplier
				System.out.println("\n=== For adding Supplier ===");
				createEntity("Supplier");
				break;
			case 'c': // display customers and suppliers
				saveEntityDetails();
				break;
			case 'd': // search for customer
				try {
					System.out.print("\nEnter the partner id of customer: ");
					int id = sc.nextInt();
					Customer customerById = Service.getCustomerById(id);
					if (customerById == null) {
						System.out.println("=== Customer not found. ===");
					} else {
						System.out.println("Customer: " + customerById);
					}
				} catch (InputMismatchException e) {
					System.out.println("=== There was some error in the input. Try again. ===");
				}
				sc.nextLine();
				break;
			case 'e':
				System.out.println("\n ==== GoodBye ====");
				break;
			default:
				System.out.println("\n ==== Wrong choice. Try again. ====");
			}

		} while (choice != 'e');

	}

	private static void createEntity(String entity) {
		if (entity == null) {
			return;
		}
		Scanner sc2 = new Scanner(System.in);
		int partnerId = -1;
		try {
			// get data from user
			System.out.print("Enter partner Id: ");
			partnerId = sc2.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("=== There was some error in the input. Try again. ===");
			return;
		}
		sc2.nextLine();
		System.out.print("Enter partner Name: ");
		String partnerName = sc2.nextLine();
		System.out.print("Enter city: ");
		String city = sc2.nextLine();
		System.out.print("Enter state: ");
		String state = sc2.nextLine();

		if (entity.equalsIgnoreCase("Customer")) {
			double creditLimit = 222222222;
			try {
				System.out.print("Enter credit limit: ");
				creditLimit = sc2.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("=== There was some error in the input. Try again. ===");
				return;
			}
			System.out.print("Enter phone number: ");
			String phoneNo = sc2.next();
			System.out.print("Enter email:");
			String email = sc2.next();

			// create customer object
			Merchandise customer = null;
			try {
				customer = new Customer(partnerId, partnerName, city, state, creditLimit, phoneNo, email);
			} catch (Exception e) {
				Throwable t[] = e.getSuppressed();
				for (Throwable throwable : t) {
					System.out.println(throwable.getMessage());
				}
			}
			storeEntity(customer);
		} else if (entity.equalsIgnoreCase("Supplier")) {
			double creditBalance = 999999999;
			try {
				System.out.print("Enter credit balance: ");
				creditBalance = sc2.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("=== There was some error in the input. Try again. ===");
				return;
			}
			System.out.print("Enter driving License Number:");
			String drivingLicenseNumber = sc2.next();

			// create supplier object
			Merchandise supplier = null;
			try {
				supplier = new Supplier(partnerId, partnerName, city, state, creditBalance, drivingLicenseNumber);
			} catch (Exception e) {
				Throwable t[] = e.getSuppressed();
				for (Throwable throwable : t) {
					System.out.println(throwable.getMessage());
				}
			}
			storeEntity(supplier);
		} else {
			System.out.println("Wrong entity.");
		}

	}

	private static void storeEntity(Merchandise entity) {

		if (entity == null) {
			return;
		}

		if (entity instanceof Customer) {
			try {
				Service.saveCustomerDetails((Customer) entity);
			} catch (Exception e) {
				System.out.println("Cannot store the customer.");
			}
		}

		if (entity instanceof Supplier) {
			try {
				Service.saveSupplierDetails((Supplier) entity);
			} catch (Exception e) {
				System.out.println("Cannot store the supplier.");
			}
		}
	}

	private static void saveEntityDetails() {
		
		System.out.println("=== Choose one option to store data ===");
		System.out.println("a. Customer");
		System.out.println("b. Supplier");
		System.out.print("Enter your choice: ");

		char choice;
		Scanner sc1 = new Scanner(System.in);
		choice = sc1.next().charAt(0);
		switch (choice) {
		case 'a':
			Service.writeObjects("Customer");
			System.out.println("Customer file created");
			break;
		case 'b':
			Service.writeObjects("Supplier");
			System.out.println("Supplier file created");
			break;
		default:
			System.out.println("=== Wrong choice ===");
			break;
		}
	}
}
