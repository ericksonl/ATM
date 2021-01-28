//File: Account.java
//Edit By: Liam Erickson
// May 3rd, 2019

//  Account.java
//  Created By: Kevin Sahr, April 1, 2014 (adapted from Lewis & Loftus)
//
//  Represents a bank account with basic services such as deposit
//  and withdraw.

import java.text.DecimalFormat;

public class Account {
	// // class variables

	// constants
	public static final double INTEREST_RATE = 0.035; // interest rate of 3.5%
	public static final long START_ACCT_NUM = 100000; // first account number

	// non-constant class variables
	private static long numAccounts = 0; // number of accounts created

	// // instance data
	private long acctNumber;
	private double balance = 0.0;
	private String name = "John Doe";

	private int pinNum = 0;
	private boolean loggedIn = false;
	
	// // class service methods
	public static long numberOfAccounts() {
		return numAccounts;
	}

	// // class helper methods
	private static long newAcctNum() {
		long newNum = START_ACCT_NUM + numAccounts;
		numAccounts++;
		return newNum;
	}

	// // constructors
	public Account() {
		acctNumber = newAcctNum();
	}

	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
		acctNumber = newAcctNum();
	}

	// // service instance methods
	boolean login (int pinNum) {
		boolean active = false;
		if (this.pinNum == pinNum) {
			loggedIn = true;
			active = true;
		}
		return active;
	}
	
	void logOut () {
		loggedIn = false;
	}
	
	// Deposits the specified amount into the account. Returns the
	// new balance.
	public double deposit(double amount) {
		balance += amount;
		return balance;
	}

	// Withdraws the specified amount from the account and applies
	// the fee. Returns the new balance.
	public double withdraw(double amount, double fee) {
		balance -= amount + fee;
		return balance;
	}

	// Adds interest to the account and returns the new balance.
	public double addInterest() {
		balance += (balance * INTEREST_RATE);
		return balance;
	}
	public void setPinNum (int pinNum) {this.pinNum = pinNum; }
	public int getPinNum () {return pinNum;}
	
	public boolean getLoggedIn () {return loggedIn;}
	
	// Returns the current name on the account.
	public String getName() {
		return name;
	}

	// Returns the current account number on the account.
	public long getAcctNumber() {
		return acctNumber;
	}

	// Returns the current balance of the account.
	public double getBalance() {
		return balance;
	}

	// Sets the current balance of the account to a new value.
	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Returns a one-line description of the account as a string.
	public String toString() {
		String loggedInStatus;
		if (loggedIn)
			loggedInStatus = "Currently Logged In ";
		else
			loggedInStatus = "Not Logged In";
		DecimalFormat fmt = new DecimalFormat("0.00");
		return ("Account Number: " + acctNumber + "\n" + "Account Holder Name: " + name +
		"\n" + "Current Balance: " + fmt.format(balance) + "\n" + "Pin Number: " + pinNum + 
		"\n" + loggedInStatus);
	}
}//end account
