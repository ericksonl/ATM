//File: Bank.java
//Liam Erickson
//May 3rd, 2019
//Lab 5a
public class Bank
{

   private Account[] theAccounts;
   private int numAccounts;
   public static final int INVALID_ACCOUNT = -1;
   
   public Bank(int maxAccounts)
   {
      theAccounts = new Account[maxAccounts];
      numAccounts = 0;

   }

   private long accountNumIndex (int index)
   {
      return index + Account.START_ACCT_NUM;
   }

   private int accountNum (long accountNum1)
   {
      return (int) (accountNum1 - Account.START_ACCT_NUM);
   }

   public long createAccount(String name)
   {
      long accountNum = INVALID_ACCOUNT;
      if (numAccounts == theAccounts.length)
         System.out.println("ERROR: BANK IS FULL.");
      else {
         theAccounts[numAccounts] = new Account(name, 0.0);
         accountNum = accountNumIndex(numAccounts);
         numAccounts++;
      }

      return accountNum;

   } // method createAccount

   public Account getAccount (long accountNum2) {
      Account theAccount = null;
      int index = accountNum(accountNum2);
      if (index < 0 || index >= numAccounts)
         System.out.println("ERROR: This doesn't seem to be right. Account number " + accountNum2 + " is invalid. Please try again.");
      else
         theAccount = theAccounts[index];  

      return theAccount;
   }

    
    public String toString () {
    	String outStr = "";
    	for (int i = 0; i<numAccounts; i++) {
    		outStr = outStr + theAccounts[i] + "\n";
    	}
    	return outStr;
    }
}
