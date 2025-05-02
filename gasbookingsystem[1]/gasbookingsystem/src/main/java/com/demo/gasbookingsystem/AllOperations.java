package com.demo.gasbookingsystem;

import com.demo.Service.*;
import com.demo.ServiceImpl.*;
import com.demo.entity.*;
import Util.HibernateUtil;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.Console;

public class AllOperations {
	// Initialize service layer objects
	private CustomerService customerService = new CustomerServiceImpl();
	private BookingService bookingService = new BookingServiceImpl();
	private PaymentService paymentService = new PaymentServiceImpl();
	private DeliveryService deliveryService = new DeliveryServiceImpl();
	private GasService gasService = new GasServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	private Scanner scanner = new Scanner(System.in);
	private Admin loggedInAdmin = null;

	public static void main(String[] args) {
		AllOperations operations = new AllOperations();
		operations.run();
	}

	// Main method to start the application
	public void run() {
		System.out.println("=== Gas Booking System ==="+"\n");
		System.out.println("Are you an admin or a customer? (admin/customer)");
		
		String userType = scanner.nextLine();

		// Navigate to admin or customer menu based on user input
		if (userType.equalsIgnoreCase("admin")) {
			if (!adminLogin()) {
				System.out.println("Login failed. Exiting.");
				return;
			}
			adminMenu();
		} else {
			customerMenu();
		}
		// Close the Hibernate session factory when the application exits
		closeSessionFactory();
		// Close the scanner
		scanner.close();
	}

	// Handles admin login functionality
	private boolean adminLogin() {
	    System.out.println("\n*** Admin Login ****");
	    System.out.print("Enter Admin Name: ");
	    String adminName = scanner.nextLine();
	    System.out.print("Enter Admin Password: ");
	    String adminPassword = scanner.nextLine(); // Reading password as plain text for this simple example

	    if (adminName.equals("vaishnavi") && adminPassword.equals("vai@934")) {
	        Admin simpleAdmin = new Admin();
	        simpleAdmin.setAdminName("vaishnavi");
	        // In a real scenario, you'd fetch the admin ID from the database
	        // simpleAdmin.setAdminId(...);
	        loggedInAdmin = simpleAdmin;
	        System.out.println("Login successful ");
	        return true;
	    } else {
	        System.out.println("Invalid credentials.");
	        return false;
	    }
	}

	// Displays the admin menu and handles admin operations
	private void adminMenu() {
		boolean running = true;
		while (running) {
			displayAdminMenu();
			int choice = getChoice();
			switch (choice) {
			case 0:
				running = false;
				System.out.println("Exiting Gas Booking System.");
				break;
			case 1:
				getAllCustomers();
				break;
			case 2:
				handleGasOperations1();
				break;
			case 3:
				viewAllBookings();
				break;
			case 4:
				viewAllDeliveries();
				break;
			
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Displays the admin menu options
	private void displayAdminMenu() {
		System.out.println("Gas Booking System (Admin: " + (loggedInAdmin != null ? loggedInAdmin.getAdminName() : "Not Logged In") + ")");
		System.out.println("1. View All Customers");
		System.out.println("2. Manage Gas");
		System.out.println("3. View All Bookings");
		System.out.println("4. View All Deliveries");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: "+"\n");
	}
	// Displays the customer menu and handles customer operations
	private void customerMenu() {
		boolean running = true;
		while (running) {
			displayMainMenu();
			int choice = getChoice();
			switch (choice) {
			case 0:
				running = false;
				System.out.println("Exiting Gas Booking System.");
				break;
			case 1:
				handleCustomerOperations();
				break;
			case 2:
				handleGasOperations();
				break;
			case 3:
				handleBookingOperations();
				break;
			case 4:
				handlePaymentOperations();
				break;
			case 5:
				handleDeliveryOperations();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}
	// Displays the admin-specific gas management menu
	private void handleGasOperations1() {
		boolean gasMenuRunning = true;
		while (gasMenuRunning) {
			displayGasMenu1();
			int gasChoice = getChoice();
			switch (gasChoice) {
			case 0:
				gasMenuRunning = false;
				break;
			case 1:
				createGas();
				break;
			case 2:
				getAllAvailableGas();
				break;
			case 3:
				deleteGas();
				break;
			case 4:
				updateGas();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
	// Displays the admin-specific gas management menu options
	private void displayGasMenu1() {
		System.out.println("\n Gas Details Menu");
		System.out.println("===========================");
		System.out.println("1. Add Gas");
		System.out.println("2. Retrieve Available Gas");
		System.out.println("3. Delete Gas");
		System.out.println("4. Update Gas");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");
	}

	// Displays the main menu options for customers
	private void displayMainMenu() {
		System.out.println("\n=== Gas Booking System====");
		System.out.println("===========================");
		System.out.println("1. Customer Details");
		System.out.println("2. Gas Details");
		System.out.println("3. Booking Details");
		System.out.println("4. Payment Details");
		System.out.println("5. Delivery Details");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
	}

	// Displays the customer menu and handles customer related operations
	private void handleCustomerOperations() {
		boolean customerMenuRunning = true;
		while (customerMenuRunning) {
			displayCustomerMenu();
			int customerChoice = getChoice();
			switch (customerChoice) {
			case 0:
				customerMenuRunning = false;
				break;
			case 1:
				createCustomer();
				break;
			case 2:
				getCustomer();
				break;
			case 3:
				deleteCustomer();
				break;
			case 4:
				updateCustomer();
				break;
			case 5:
				getAllCustomers();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Displays the customer menu options
	private void displayCustomerMenu() {
		System.out.println("\n Customer Details Menu");
		System.out.println("===========================");
		System.out.println("1. Add Customer");
		System.out.println("2. Retrieve Customer");
		System.out.println("3. Delete Customer");
		System.out.println("4. Update Customer");
		System.out.println("5. Retrieve All Customers");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");

	}

	// Displays the gas menu options for customers
	private void handleGasOperations() {
		boolean gasMenuRunning = true;
		while (gasMenuRunning) {
			displayGasMenu();
			int gasChoice = getChoice();
			switch (gasChoice) {
			case 0:
				gasMenuRunning = false;
				break;
			case 1:
				getAllAvailableGas();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Displays the booking menu and handles booking related operations
	private void displayGasMenu() {
		System.out.println("\n Gas Details Menu");
		System.out.println("===========================");    
		System.out.println("1. Retrieve Available Gas");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");
	}

	// Displays the booking menu options
	private void handleBookingOperations() {
		boolean bookingMenuRunning = true;
		while (bookingMenuRunning) {
			displayBookingMenu();
			int bookingChoice = getChoice();
			switch (bookingChoice) {
			case 0:
				bookingMenuRunning = false;
				break;
			case 1:
				createBooking();
				break;
			case 2:
				deleteBooking();
				break;
			case 3:
				viewBookingDetails();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}


	private void displayBookingMenu() {
		System.out.println("\n Booking Details Menu");
		System.out.println("===========================");
		System.out.println("1. Add Booking");
		System.out.println("2. Delete Booking");
		System.out.println("3. View Booking Details");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");
	}

	// Displays the payment menu and handles payment related operations
	private void handlePaymentOperations() {
		boolean paymentMenuRunning = true;
		while (paymentMenuRunning) {
			displayPaymentMenu();
			int paymentChoice = getChoice();
			switch (paymentChoice) {
			case 0:
				paymentMenuRunning = false;
				break;
			case 1:
				makePaymentWithCustomerDetails();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void displayPaymentMenu() {
		System.out.println("\n Payment Details Menu");
		System.out.println("===========================");
		System.out.println("1. Make Payment (with Customer Details)");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");
	}
	// Displays the delivery menu and handles delivery related operations
	private void handleDeliveryOperations() {
		boolean deliveryMenuRunning = true;
		while (deliveryMenuRunning) {
			displayDeliveryMenu();
			int deliveryChoice = getChoice();
			switch (deliveryChoice) {
			case 0:
				deliveryMenuRunning = false;
				break;
			case 1:
				provideAvailableDeliveryDates();
				
				break;
			case 2:
				addDeliveryInformation();
				break;
			case 3:
				viewDeliveryStatus();
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void displayDeliveryMenu() {
		System.out.println("\n Delivery Details Menu");
		System.out.println("===========================");
		System.out.println("1. Provide Available Delivery Dates");
		System.out.println("2. Add Delivery Information ");
		System.out.println("3. View Delivery Status");
		System.out.println("0. Back to Main Menu");
		System.out.print("Enter your choice: ");
	}

	// Creates a new customer record
	private void createCustomer() {
		try {
			scanner.nextLine();
			System.out.print("Enter Customer Name: ");
			String custName = scanner.nextLine();
			System.out.print("Enter Customer Mobile: ");
			String custMobile = scanner.nextLine();
			System.out.print("Enter Customer Email: ");
			String custEmail = scanner.nextLine();
			System.out.print("Enter Customer Address: ");
			String custAddress = scanner.nextLine();

			Customer customer = new Customer(custName, custMobile, custEmail, custAddress);
			customerService.createCustomer(customer);
			System.out.println("Customer created successfully.");
		} catch (Exception e) {
			System.err.println("Error creating customer: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Retrieves and displays a customer by their ID
	private void getCustomer() {
		try {
			System.out.print("Enter Customer ID: ");
			int customerId = getIntInput();
			Customer customer = customerService.getCustomerById(customerId);
			if (customer != null) {
				System.out.println("Customer Details: " + customer);
			} else {
				System.out.println("Customer not found.");
			}
		} catch (Exception e) {
			System.err.println("Error retrieving customer: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Deletes a customer by their ID
	private void deleteCustomer() {
		try {
			System.out.print("Enter Customer ID to delete: ");
			int customerId = getIntInput();
			customerService.deleteCustomer(customerId);
			System.out.println("Customer deleted successfully.");
		} catch (Exception e) {
			System.err.println("Error deleting customer: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Updates an existing customer's information
	private void updateCustomer() {
		try {
			System.out.print("Enter Customer ID to update: ");
			int customerId = getIntInput();
			Customer customer = customerService.getCustomerById(customerId);
			if (customer != null) {
				scanner.nextLine();
				System.out.print("Enter new Customer Name (or press Enter to keep current): ");
				String newName = scanner.nextLine();
				if (!newName.isEmpty()) {
					customer.setCustName(newName);
				}
				System.out.print("Enter new Customer Mobile (or press Enter to keep current): ");
				String newMobile = scanner.nextLine();
				if (!newMobile.isEmpty()) {
					customer.setCustMobile(newMobile);
				}
				System.out.print("Enter new Customer Email (or press Enter to keep current): ");
				String newEmail = scanner.nextLine();
				if (!newEmail.isEmpty()) {
					customer.setCustEmail(newEmail);
				}
				System.out.print("Enter new Customer Address (or press Enter to keep current): ");
				String newAddress = scanner.nextLine();
				if (!newAddress.isEmpty()) {
					customer.setCustAddress(newAddress);
				}
				customerService.updateCustomer(customer);
				System.out.println("Customer updated successfully.");
			} else {
				System.out.println("Customer not found.");
			}
		} catch (Exception e) {
			System.err.println("Error updating customer: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Retrieves and displays all customers
	private void getAllCustomers() {
		try {
			List<Customer> allCustomers = customerService.getAllCustomers();
			System.out.println("All Customers:");
			allCustomers.forEach(System.out::println);
		} catch (Exception e) {
			System.err.println("Error retrieving all customers: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Creates a new gas type
	private void createGas() {
		try {
			scanner.nextLine();
			System.out.print("Enter Gas Name: ");
			String gasName = scanner.nextLine();
			System.out.print("Enter Gas Price: ");
			double gasPrice = getDoubleInput();
			Gas gas = new Gas(gasName, java.math.BigDecimal.valueOf(gasPrice));
			gasService.createGas(gas);
			System.out.println("Gas created successfully.");
		} catch (Exception e) {
			System.err.println("Error creating gas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Retrieves and displays all available gas types
	private void getAllAvailableGas() {
		try {
			List<Gas> availableGas = gasService.getAvailableGases();
			if (availableGas != null && !availableGas.isEmpty()) {
				System.out.println("Available Gases:");
				for (Gas gas : availableGas) {
					System.out.println(gas);
				}
			} else {
				System.out.println("No available gases found.");
			}
		} catch (Exception e) {
			System.err.println("Error retrieving available gas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Deletes a gas type by its ID
	private void deleteGas() {
		try {
			System.out.print("Enter Gas ID to delete: ");
			int gasId = getIntInput();
			gasService.deleteGas(gasId);
			System.out.println("Gas deleted successfully.");
		} catch (Exception e) {
			System.err.println("Error deleting gas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Updates an existing gas type's information
	private void updateGas() {
		try {
			System.out.print("Enter Gas ID to update: ");
			int gasId = getIntInput();
			Gas gas = gasService.getGasById(gasId);
			if (gas != null) {
				scanner.nextLine();
				System.out.print("Enter new Gas Name (or press Enter to keep current): ");
				String newName = scanner.nextLine();
				if (!newName.isEmpty()) {
					gas.setGasName(newName);
				}
				System.out.print("Enter new Gas Price (or press Enter to keep current): ");
				if (scanner.hasNextDouble()) {
					double newPrice = scanner.nextDouble();
					gas.setGasPrice(java.math.BigDecimal.valueOf(newPrice));
				}
				gasService.updateGas(gas);
				System.out.println("Gas updated successfully.");
			} else {
				System.out.println("Gas not found.");
			}
		} catch (Exception e) {
			System.err.println("Error updating gas: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Creates a new booking for a customer and gas type
	private void createBooking() {
		try {
			System.out.print("Enter Customer ID for Booking: ");
			int customerId = getIntInput();
			Customer customer = customerService.getCustomerById(customerId);
			if (customer == null) {
				System.out.println("Customer not found.");
				return;
			}

			System.out.print("Enter Gas ID for Booking: ");
			int gasId = getIntInput();
			Gas gas = gasService.getGasById(gasId);
			if (gas == null) {
				System.out.println("Gas type not found.");
				return;
			}

			System.out.print("Enter Booking Date (YYYY-MM-DD): ");
			String dateString = scanner.next();
			Date bookingDate = Date.valueOf(dateString);

			Booking booking = new Booking(customerId, gasId, bookingDate);
			bookingService.createBooking(booking);
			gasService.updateGasAvailability(gasId, false);
			System.out.println("Booking done successfully.");
		} catch (Exception e) {
			System.err.println("Error creating booking: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Deletes a booking by its ID
	private void deleteBooking() {
		try {
			System.out.print("Enter Booking ID to delete: ");
			int bookingId = getIntInput();
			bookingService.deleteBooking(bookingId);
			System.out.println("Booking deleted successfully.");
		} catch (Exception e) {
			System.err.println("Error deleting booking: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Retrieves and displays the details of a booking by its ID
	private void viewBookingDetails() {
	    try {
	        System.out.print("Enter Booking ID to view Details: ");
	        int bookingId = getIntInput();
	        Booking booking = bookingService.getBookingById(bookingId);
	        if (booking != null) {
	            int customerId = booking.getCustId();
	            Customer customer = customerService.getCustomerById(customerId);
	            int gasId = booking.getGasId();
	            Gas gas = gasService.getGasById(gasId);

	            System.out.println("Booking Details:");
	            System.out.println("Booking ID: " + booking.getBookingId());
	            if (customer != null) {
	                System.out.println("Customer Name: " + customer.getCustName());
	            } else {
	                System.out.println("Customer Name: N/A");
	            }
	            if (gas != null) {
	                System.out.println("Gas Name: " + gas.getGasName());
	                System.out.println("Gas Price: " + gas.getGasPrice());
	            } else {
	                System.out.println("Gas Name: N/A");
	                System.out.println("Gas Price: N/A");
	            }
	            System.out.println("Booking Date: " + booking.getBookingDate());
	            // You can display other relevant booking details here
	        } else {
	            System.out.println("Booking not found.");
	        }
	    } catch (Exception e) {
	        System.err.println("Error viewing booking details: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	// Processes a payment for a booking, requiring customer confirmation
	private void makePaymentWithCustomerDetails() {
		try {
			System.out.print("Enter Booking ID for Payment: ");
			int bookingId = getIntInput();

			Booking booking = bookingService.getBookingById(bookingId);

			if (booking == null) {
				System.out.println("Booking not found.");
				return;
			}

			Customer customer = customerService.getCustomerById(booking.getCustId());

			if (customer == null) {
				System.out.println("Customer details not found for this booking.");
				return;
			}

			System.out.println("Customer Details: " + customer);
			System.out.println("Confirm this is the person making payment? (yes/no)");
			String confirmation = scanner.next();

			if (!confirmation.equalsIgnoreCase("yes")) {
				System.out.println("Payment cancelled.");
				return;
			}

			System.out.print("Enter Payment Date (YYYY-MM-DD): ");
			String dateString = scanner.next();
			Date payDate = Date.valueOf(dateString);

			System.out.print("Enter Payment Amount: ");
			double payAmount = getDoubleInput();

			System.out.print("Payment by (credit/debit): ");
			String paymentMethod = scanner.next();

			String cardNumber = null;
			if (paymentMethod.equalsIgnoreCase("credit") || paymentMethod.equalsIgnoreCase("debit")) {
				System.out.print("Enter " + paymentMethod + " card number: ");
				scanner.nextLine(); // Consume the newline character
				cardNumber = scanner.nextLine();
				// In a real application, you would handle card number securely (e.g., encryption).
				System.out.println(paymentMethod + " card number entered: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
			} else {
				System.out.println("Invalid payment method. Payment cancelled.");
				return;
			}

			Payment payment = new Payment(bookingId, payDate, java.math.BigDecimal.valueOf(payAmount), paymentMethod, cardNumber);
			paymentService.createPayment(payment);
			System.out.println("Payment done successfully.");

		} catch (Exception e) {
			System.err.println("An error occurred during payment processing: " + e.getMessage());
			e.printStackTrace();
		}
	}
   
	// Provides available delivery dates within the next 7 days
	private void provideAvailableDeliveryDates() {
		try {
			System.out.print("Enter Booking ID: ");
			int bookingId = getIntInput();

			Booking booking = bookingService.getBookingById(bookingId);
			if (booking == null) {
				System.out.println("Booking not found.");
				return;
			}

			LocalDate today = LocalDate.now();
			LocalDate maxDate = today.plus(7, ChronoUnit.DAYS);

			System.out.println("Available delivery dates for Booking ID " + bookingId + ":");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			for (LocalDate date = today; date.isBefore(maxDate.plusDays(1)); date = date.plusDays(1)) {
				System.out.println(date.format(formatter));
			}
		} catch (Exception e) {
			System.err.println("Error providing delivery dates: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Adds delivery information for a booking
	private void addDeliveryInformation() {
		try {
			System.out.println("\nAdd Delivery Information:");

			System.out.print("Enter Booking ID for Delivery: ");
			int bookingId = getIntInput();

			Booking booking = bookingService.getBookingById(bookingId);
			if (booking == null) {
				System.out.println("Booking not found.");
				return;
			}

			scanner.nextLine();
			System.out.print("Enter Delivery Address: ");
			String deliveryAddress = scanner.nextLine();

			System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
			String dateString = scanner.next();
			Date deliveryDate = Date.valueOf(dateString);

			Delivery delivery = new Delivery();
			delivery.setBookingId(bookingId);
			delivery.setDeliveryAddress(deliveryAddress);
			delivery.setDeliveryDate(deliveryDate);

			deliveryService.createDelivery(delivery);
			System.out.println("Delivery information added successfully.");
		} catch (Exception e) {
			System.err.println("Error adding delivery info: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Views the delivery status for a given booking ID
	private void viewDeliveryStatus() {
	    try {
	        System.out.print("Enter Booking ID to view Delivery Status: ");
	        int bookingId = getIntInput();

	        Delivery delivery = deliveryService.getDeliveryByBookingId(bookingId);
	        if (delivery != null) {
	            System.out.println("Delivery Status for Booking ID " + bookingId + ":");
	            Date deliveryDate = delivery.getDeliveryDate();
	            LocalDate currentDate = LocalDate.now();
	            LocalDate deliveryLocalDate = deliveryDate.toLocalDate();
	            String status;

	            if (deliveryLocalDate.isBefore(currentDate)) {
	                status = "Delivered";
	            } else if (deliveryLocalDate.isEqual(currentDate)) {
	                status = "Delivering Today";
	            } else {
	                status = "Not Yet Delivered";
	            }

	            System.out.println("Status: " + status);
	            System.out.println("Delivery Details: " + delivery); // Optionally display other delivery details
	        } else {
	            System.out.println("Delivery information not found for Booking ID " + bookingId + ".");
	        }
	    } catch (Exception e) {
	        System.err.println("Error viewing delivery status: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	// Gets an integer input from the user with input validation
	private int getChoice() {
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.next();
		}
		return scanner.nextInt();
	}

	// Gets an integer input from the user with input validation
	private int getIntInput() {
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.next();
		}
		return scanner.nextInt();
	}

	// Gets a double input from the user with input validation
	private double getDoubleInput() {
		while (!scanner.hasNextDouble()) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.next();
		}
		return scanner.nextDouble();
	}

	// Closes the Hibernate session factory
	void closeSessionFactory() {
		HibernateUtil.getSessionFactory().close();
	}

	// Retrieves and displays all bookings
	private void viewAllBookings() {
        try {
            List<Booking> allBookings = bookingService.getAllBookings();
            System.out.println("All Bookings with Customer and Payment Information:");
            System.out.println("----------------------------");
            if (allBookings.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }
            for (Booking booking : allBookings) {
                Customer customer = customerService.getCustomerById(booking.getCustId());
                Gas gas = gasService.getGasById(booking.getGasId());
                Payment payment = paymentService.getPaymentByBookingId(booking.getBookingId()); // Fetch payment

                System.out.println("  Booking ID: " + booking.getBookingId());
                if (customer != null) {
                    System.out.println("  Customer Name: " + customer.getCustName());
                    System.out.println("  Customer Email: " + customer.getCustEmail());
                    System.out.println("  Customer Mobile: " + customer.getCustMobile());
                } else {
                    System.out.println("  Customer Details: N/A");
                }
                if (gas != null) {
                    System.out.println("  Gas Booked: " + gas.getGasName());
                    System.out.println("  Gas Price:"+gas.getGasPrice());
                } else {
                    System.out.println("  Gas Booked: N/A");
                }
                System.out.println("  Booking Date: " + booking.getBookingDate());
                if (payment != null) {
                    System.out.println("  Payment Method: " + payment.getPaymentMethod());
                } else {
                    System.out.println("  Payment Status: Not Paid");
                }
                System.out.println("--------------------");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving all bookings: " + e.getMessage());
            e.printStackTrace();
        }
    }
	// Retrieves and displays all deliveries
	private void viewAllDeliveries() {
	    try {
	        List<Delivery> allDeliveries = deliveryService.getAllDeliveries();
	        System.out.println("All Deliveries:");
	        for (Delivery delivery : allDeliveries) {
	            Date deliveryDate = delivery.getDeliveryDate();
	            LocalDate currentDate = LocalDate.now();
	            LocalDate deliveryLocalDate = deliveryDate.toLocalDate();
	            String status;

	            if (deliveryLocalDate.isBefore(currentDate)) {
	                status = "Delivered";
	            } else if (deliveryLocalDate.isEqual(currentDate)) {
		                status = "Delivering Today";
	            }  else {
	                status = "Not Yet Delivered";
	            }

	            System.out.println(delivery + ", Status: " + status);
	        }
	    } catch (Exception e) {
	        System.err.println("Error retrieving all deliveries: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}