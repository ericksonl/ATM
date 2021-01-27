// file: ATM.java
// CS257 Lab 3
// Author: Liam Erickson
// Created: April 22, 2019

//2nd Use
//File: ATM.java
//Liam Erickson
//May 7th, 2019
//Lab 5b

import java.util.Scanner;
import java.text.DecimalFormat;

public class ATM {
	
	public static final double fee = 1.5;
	private static DecimalFormat format = new DecimalFormat("0.00");
	private static Scanner scan = new Scanner(System.in);
	
	private Bank bank;
	private String name;
	
	ATM(Bank bank, String name) {
		this.name= name;
		this.bank = bank;
	}
	
	public void mainMenu () {

		boolean options = true;
		long accountNum;
		while (options) {
			System.out.print(
					"~~~~ " + name + " Menu ~~~~ \n"
					+ "1. Create a new account. \n"
					+ "2. Login. \n"
					+ "3. Exit.");
			int menuOptions = Integer.parseInt(scan.nextLine());
			System.out.println("You have chosen: " + menuOptions);
			
			switch (menuOptions) {
			
			case 1:
				System.out.print("Please Enter your name: ");
				String name = scan.nextLine();
				System.out.print("Please retype your name: ");
				String nameV = scan.nextLine();
				if (!name.equals(nameV)) {
					System.out.println("Error: Name did not match.");
					continue;
				} else {
					int pinCheck = 0;
					while (true) {
						try {
							System.out.println("Please enter a 4 digit Pin");
							int pinIn = Integer.parseInt(scan.nextLine());
							while (pinIn < 1000 || pinIn > 9999) {
								System.out.println("Invalid input!");
								System.out.println("Please enter a 4 digit Pin");
								pinIn = Integer.parseInt(scan.nextLine());
							}
							System.out.print("Please retype your PIN: ");
							pinCheck = Integer.parseInt(scan.nextLine());
							if (pinIn != pinCheck) {
								System.out.println("Error: PIN did not match.");
								continue;
							}
						} catch (NumberFormatException ne) {
							   System.out.println("Invalid input!");
							   continue;
						} 
						break;
					}
					accountNum = bank.createAccount(name);
					Account liamsAccount = bank.getAccount(accountNum);
					liamsAccount.setPinNum(pinCheck);
					System.out.println("Your new bank account  " + name + " has been created. The account number is: " + accountNum);
					break;
				}
			case 2:
				System.out.print("Enter your account number: ");
				accountNum = Integer.parseInt(scan.nextLine());
				System.out.println("You entered " + accountNum);
				Account liamsAccount = bank.getAccount(accountNum);
				if (liamsAccount == null) {
					continue;
				}
				
				System.out.print("Enter your PIN: ");
				int pinNumInt = Integer.parseInt(scan.nextLine());
				if (liamsAccount.login(pinNumInt))
					accountMenu(liamsAccount);
				else 
					System.out.println("Error: Invalid PIN!");
				break;
				
			case 3:
				System.out.print("Goodbye!");
				options = false;
				break;
				
			default:
				System.out.println("Sorry: " + menuOptions + " is not a valid option");
			}//end choice
		}//end options
	}
	
	private void accountMenu (Account account) {
		
		boolean options = true;
		while (options) {
			System.out.print(
					" ~~~~ " + name + "  Menu ~~~~ \n"
					+ "a. Make a deposit \n"
					+ "b. Withdrawl Money \n"
					+ "c. Account Info \n"
					+ "d. Change Pin \n"
					+ "e. Logout");
			char atmOptions = scan.nextLine().charAt(0);
			System.out.println("You have chosen: " + atmOptions);
			switch (atmOptions) {
			case 'a':
				deposit(account);
				break;
			
			case 'b':
				withdrawl(account);
				break;
			
			case 'c':
				System.out.println("Displaying your account info:");
				System.out.println(account);
				break;
				
			case 'd':
				int pinCheck = 0;
				boolean tru = true;
				while (tru) {
					try {
						System.out.println("Please enter a 4 digit Pin");
						int pinIn = Integer.parseInt(scan.nextLine());
						while (pinIn < 1000 || pinIn > 9999) {
							System.out.println("Invalid input!");
							System.out.println("Please enter a 4 digit Pin");
							pinIn = Integer.parseInt(scan.nextLine());
						}
						if (pinIn == account.getPinNum()) {
							System.out.print("Error: " + pinIn + " is your current PIN number!\n");
							continue;
						}
						System.out.print("Please retype your PIN: ");
						pinCheck = Integer.parseInt(scan.nextLine());
						if (pinIn != pinCheck) {
							System.out.println("Error: PIN did not match.");
							continue;
						}
					} catch (NumberFormatException ne) {
						   System.out.println("Invalid input!");
						   continue;
					}
					account.setPinNum(pinCheck);
					tru = false;
				}
				break;
			case 'e':
				account.logOut();
				System.out.println("Logging Out...");
				System.out.println("Goodbye!");
				options = false;
				break;
				
			default:
				System.out.println("Sorry: " + atmOptions + " is not a valid option");
				
			}//end switch
		}//end while
	}//end account menu
	
	private void deposit(Account account) {
		System.out.print("Enter how much you would like to deposit: ");
		double amount = Double.parseDouble(scan.nextLine());
		while (amount <= 0) {
			System.out.println("ERROR: " + format.format(amount) + " is not a positive amount!");
			System.out.println(" Please enter a positive amount of money you would like to deposit: ");
			amount = Double.parseDouble(scan.nextLine());
		}
		
		System.out.println("Depositing: " + format.format(amount));
		account.deposit(amount);

	}
	
	private void withdrawl(Account account) {
		System.out.println("There will be a " + format.format(fee) + " fee." );
		System.out.print("Enter the amount you would like to withdrawl: ");
		double amount = Double.parseDouble(scan.nextLine());
		while (amount <= 0) {
			System.out.println("ERROR: " + format.format(amount) + " is not a positive amount!");
			System.out.println("Please enter a positive amount of money you would like to withdrawl: ");
			amount = Double.parseDouble(scan.nextLine());
		}
		
		System.out.println("Withdrawling $" + format.format(amount));
		
		double funds = account.getBalance() - fee;
		if (funds >= amount)
			account.withdraw(amount, fee);
		else
			System.out.println("ERROR: Insufficient Funds");
		
		
	}
}

