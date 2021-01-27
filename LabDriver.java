//File: LabDriver.java
//Liam Erickson
//May 7th, 2019
//Lab 5c
public class LabDriver {
	
	public static void main (String [] args) {
		
		final int MAX_ACCOUNTS = 10;
		
		Bank liamsBank = new Bank(MAX_ACCOUNTS);
		
		ATM a1 = new ATM(liamsBank, "ATM #1");
		
		a1.mainMenu();
	}
}
