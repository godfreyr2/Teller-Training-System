/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerLogic1;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import tellerdata.Account;

/**
 * Class Transaction is where to actual
 * methods that control each transaction are held
 * @author godfreyr2
 * @version 1.0
 */
public class Transaction {
    
    private String transNumber; // transNumber is the Date and Time stamp that becomes the key of the TreeMap<> records
    private Date date; // Date Object
    private StringBuilder transaction; // transaction is the StringBuilder used to store the actual transaction information that is stored
    private String transactionGui; // transactionGui is the String representation of the transaction to print out to the GUI window textArea
    
    /**
     * Empty constructor for Class Transaction
     */
    public Transaction() {
        this.transactionGui = transactionGui; // initialize the global transactionGUI
        
    }
    
    /**
     * General Transaction creates how the transaction
     * is stored.  The date and time stamp becomes the variable transNumber
     * and the stringbuilder builds the string into the variable transaction
     * @param time
     * @param trans
     */
    public void generalTransaction(Date time, StringBuilder trans) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        date = time;
        transNumber = dateFormatter.format(time);
        transaction = trans;
        
    }

    /**
     * DEPOSIT into an account
     *
     * @param account the actual account object
     * @param amount the actual amount to be deposited
     */
    public void deposit(Account account, double amount) {
        date = new Date(); // date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // date formatter
        
        DecimalFormat df = new DecimalFormat("##.00"); // decimal formatter
        
        double ab = account.getBalance(); // get the account balance
        double nb = 00.00;
        // Handle the deposit
        System.out.println("________________________________________");
        System.out.println("");
        // show the deposited amount into what account and the account number
        System.out.println("DEPOSIT of $" + df.format(amount) + " into " + account.getType() + " #" + account.getAccountNumber());
        System.out.println("");
        System.out.println("Your old balance was: $" + df.format(ab)); // old balance
        nb = ab + amount; // do the math
        account.setBalance(nb); // update the balance
        System.out.println("Your new updated balance is: $" + showBalance(account));// show new balance
        System.out.println("________________________________________");
        System.out.println("");
        // store the depostit to the record data base
        transNumber = dateFormatter.format(date); // key for transaction storage
        String deposit = "Deposit "; // variable for builder
        StringBuilder builder = new StringBuilder(); // create a string builder
        // put the transaction info into a string to be stored
        transaction = builder.append(account.getType()).append(" account,  Account#: ").append(account.getAccountNumber()).append(", ").append(deposit)
                .append(" of $").append(df.format(amount)).append(", ").append("Old balance: $").append(df.format(ab)).append(", ")
                .append("Your new balance: $").append(df.format(nb));
        // build a string of the transaction to be printed to the GUI
        transactionGui = account.getType() + " account, Account#: " + account.getAccountNumber() + "\nDeposit of $" + df.format(amount) +
                "\nOld balance: $" + df.format(ab) + "\nNew balance: $" + df.format(nb);
    }

    /**
     * Withdraw handles a withdraw from a checking account
     * A withdraw can only happen from a checking account.
     *
     * @param account the account object
     * @param amount the actual amount to be withdrawn
     */
    public boolean withdraw(Account account, double amount) {
        boolean success = false; // create a boolean to return success or failure 
        String at = account.getType(); // put the account type in a variable
        String checking = "Checking";
        date = new Date(); // create a date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // time and date formatter
        DecimalFormat df = new DecimalFormat("##.00"); // decimal formatter
        if (at.equalsIgnoreCase(checking)) { // if the account type is a checking account then do the withdraw
            
            double ab = account.getBalance(); // get account balance
            double nb = 00.00;
            if (amount > ab) { // if the requested withdraw is less than the account balance print an error message
                System.out.println("###################################################################################");
                System.out.println("");
                JOptionPane.showMessageDialog(null,"THERE ARE INSUFFICIENT FUNDS TO COMPLETE THE WITHDRAW FROM THIS ACCOUNT!!!!!!!!!!!!!!");
                System.out.println("");
                System.out.println("###################################################################################");
                success = false; // recived an error
            } else { // if there are enough funds then do the withdraw
                System.out.println("________________________________________");
                System.out.println("");
                // get the amount, account type, and account number
                System.out.println("WITHDRAW of $" + df.format(amount) + " from " + account.getType() + " #" + account.getAccountNumber());
                System.out.println("");
                System.out.println("Your old balance was: $" + df.format(ab)); // show old balance
                nb = ab - amount; // do the math
                account.setBalance(nb); // update balance
                System.out.println("Your new updated balance is: $" + showBalance(account)); // show the new balance
                System.out.println("________________________________________");
                System.out.println("");
                // store the withdraw in the database
                transNumber = dateFormatter.format(date); // date and time stamp into a variable
                String withdraw = " Withdraw "; // variable for storage
                StringBuilder builder = new StringBuilder(); // create a string builder
                // create the stringbuilder to store the actual transaction for storage
                transaction = builder.append(account.getType()).append(" account,  Account#: ").append(account.getAccountNumber())
                        .append(", ").append(withdraw).append("of $").append(df.format(amount)).append(", ").append("Old balance: $").append(df.format(ab)).append(", ")
                        .append(" Your new balance: $").append(df.format(nb));
                transactionGui = account.getType() + " account, Account#: " + account.getAccountNumber() + "\nWithdraw of $" + df.format(amount) + 
                        "\nOld balance: $" + df.format(ab) + "\nNew balance: $" + df.format(nb);
                success = true; // the transaction happened
            }
        } else { // if the account type is not checking then print an error message
            System.out.println("###################################################################################");
            System.out.println("");
            JOptionPane.showMessageDialog(null, "YOU CAN NOT WITHDRAW FROM A SAVINGS OR A GROWTH ACCOUNT!!!!!!!!!!!!!!");
            System.out.println("");
            System.out.println("###################################################################################");
            success = false;  // received an error
        }
        return success; // the success of fail of the transaction
    }

    /**
     * Transfer handles the transfer of funds between two accounts
     *
     * @param accountFrom the account money is coming from 
     * @param accountTo the account money is going to 
     * @param amount the actual amount to be transferred
     */
    public boolean transfer(Account accountFrom, Account accountTo, double amount) {
        boolean success = false; // boolean value for the success or fail of the transaction
        double amnt = amount; // an amount variable
        double afb = accountFrom.getBalance(); // get balance of the account from 
        double atb = accountTo.getBalance(); // get balance of the acccount 
        double naFromBal = 00.00;  // new account from balance
        double naToBal = 00.00; // new account To balance
        date = new Date(); // new date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // date and time formatter
        DecimalFormat df = new DecimalFormat("##.00"); // decimal formatter
        if (afb < amnt) { // if the amount is more than the account from balance throw an error message
            System.out.println("###################################################################################");
            System.out.println("");
            JOptionPane.showMessageDialog(null, "THERE ARE INSUFFICIENT FUNDS TO COMPLETE THIS TRANSFER!!!!!!!!!!!!!!");
            System.out.println("");
            System.out.println("###################################################################################");
            success = false; // an error 
        } else { // if there are enough funds then do the transfer
            System.out.println("________________________________________");
            System.out.println("");
            System.out.println("TRANSFER in the amount of:  $" + df.format(amnt)); // get the amount to be transferred
            System.out.println("");
            System.out.println("From " + accountFrom.getType() + ": #" + accountFrom.getAccountNumber());// account type and account number
            System.out.println("Balance BEFORE Transfer:   $" + df.format(afb)); // show the balance before transfer
            naFromBal = afb - amnt; // subtract the amount from sending account
            accountFrom.setBalance(naFromBal); // update the balance due to the withdraw
            System.out.println("Balance AFTER Transfer:    $" + df.format(naFromBal)); // show the balance after transfer
            System.out.println("");
            System.out.println("To " + accountTo.getType() + ": #" + accountTo.getAccountNumber()); // get the account type and account number
            System.out.println("Balance BEFORE Transfer:   $" + df.format(atb)); // show the balance before transfer
            naToBal = atb + amnt; // add the amount to the account receiving the transferred deposit
            accountTo.setBalance(naToBal); // update the balance due to the deposit
            System.out.println("Balance AFTER Transfer:    $" + df.format(naToBal)); // show balance after transfer
            System.out.println("________________________________________");
            System.out.println("");
            // store the transfer in the database
            transNumber = dateFormatter.format(date); // date and time stamp into a variable
            String transfer = " Transfer ";
            StringBuilder builder = new StringBuilder(); // new stringbuilder
            // build the transfer information into a string
            transaction = builder.append("FROM: ").append(accountFrom.getType()).append(" account, Account#: ").append(accountFrom.getAccountNumber())
                    .append(", ").append(transfer).append("of $").append(df.format(amount)).append(",   Old Balance: $").append(df.format(afb))
                    .append("   New Balance: $").append(df.format(naFromBal)).append(", ").append(" To: ").append(accountTo.getType())
                    .append(" account, Account#: ").append(accountTo.getAccountNumber()).append(",  Old Balance: $").append(df.format(atb))
                    .append(",  New Balance: $").append(df.format(naToBal));
            // build the tranfer information into a string for GUI window
            transactionGui = "FROM: " + accountFrom.getType() + " account, Account#: " + accountFrom.generateAccountNumber() + "\nTransfer in the amount of $" +
                    df.format(amount) + "\nOld balance: $" + df.format(afb) + "\nNew balance: $" + df.format(naFromBal) + "\n" + "\nTO: " + accountTo.getType()  +
                    " account, Account#: " + accountTo.generateAccountNumber() + "\nOld balance: $" + df.format(atb) + "\nNew balance: $" + df.format(naToBal);
            success = true; // the transaction was a success.  
        }
        return success; // return whether the transaction happened or not
    }

    /**
     * Money Order handles creating a money order.
     * This can only happen from a checking account
     * @param account the actual account type
     * @param amount the amount of the money order
     */
    public boolean moneyOrder(Account account, double amount) {
        boolean success = false; // boolean of success or fail on the transaction happening or not
        String at = account.getType(); // get the account type
        String checking = "Checking"; // variable for the account check
        date = new Date(); // new date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // time and date formatter
        DecimalFormat df = new DecimalFormat("##.00"); // decimal formatter
        if (at.equalsIgnoreCase(checking)) { // if the account is a checking account then do the transaction
            double ab = account.getBalance(); // get balance
            double nb = 00.00;
            if (amount > ab) { // if amount is greater than the account balance
                System.out.println("###################################################################################");
                System.out.println("");
                JOptionPane.showMessageDialog(null, "THERE ARE INSUFFICIENT FUNDS TO CREATE THIS MONEY ORDER!!!!!!!!!!!!!!");
                System.out.println("");
                System.out.println("###################################################################################");
                success = false; // transaction did not happen
            } else {
                System.out.println("________________________________________");
                System.out.println("");
                System.out.println("This was a money order creation of $" + df.format(amount) + " from " + account.getType() + " #" + account.getAccountNumber());
                System.out.println("Your old balance was: $" + df.format(ab));
                nb = ab - amount; // do the math of the money order
                account.setBalance(nb); // set the new balance
                System.out.println("Your new updated balance is: $" + showBalance(account)); // ahow the new balance
                System.out.println("________________________________________");
                System.out.println("");
                
                transNumber = dateFormatter.format(date); // format the date
                String moneyOrder = "Money Order ";
                StringBuilder builder = new StringBuilder(); // create a new stringbuilder
                // create the builder representation of the transaction for the records
                transaction = builder.append(account.getType()).append(" account,  Account#: ").append(account.getAccountNumber())
                        .append(", ").append(moneyOrder).append("of $").append(df.format(amount)).append(",  Old Balance: $").append(df.format(ab))
                        .append(",  New Balance: $").append(df.format(nb));
                // create a string of the transaction to print out to the GUI window
                transactionGui = account.getType()+ " account, Account#; " + account.generateAccountNumber() + "\nA Money Order of $" + df.format(amount) +
                        "\nOld balance: $" + df.format(ab) + "\nNew balance: $" + df.format(nb);
                success = true; // transaction was a success
            }
        } else {
            System.out.println("###################################################################################");
            System.out.println("");
            JOptionPane.showMessageDialog(null,"YOU CAN NOT CREATE A MONEY ORDER FROM A SAVINGS OR A GROWTH ACCOUNT!!!!!!!!!!!!!!");
            System.out.println("");
            System.out.println("###################################################################################");
            success = false; // transaction did not happen
        }
        return success; // return a boolean value on if the transaction happened or not
    }

    /**
     * Cashier Check handles creating the Cashier Check
     * This can only be done from a checking account
     *
     * @param account the account object
     * @param nameOfPayee the name that goes on the memo
     * @param amount  the amount of the check
     */
    public boolean cashierCheck(Account account, String nameOfPayee, double amount) {
        boolean success = false; // boolean value on if the transaction happened or not
        nameOfPayee = nameOfPayee.toUpperCase().trim().replaceAll("\\s+", " "); // get the name of the payee for the memo
        String at = account.getType(); // get the account type
        String checking = "Checking";
        date = new Date(); // a new date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // format the date
        DecimalFormat df = new DecimalFormat("##.00"); // use the decimal format
        if (at.equalsIgnoreCase(checking)) { // if the accout type is checking do the transaction
            double ab = account.getBalance(); // get the account balance
            double nb = 00.00; // create a variable for the new balance
            
            if (amount > ab) { // Check to see if there are enough funds in the account
                System.out.println("###################################################################################");
                System.out.println("");
                JOptionPane.showMessageDialog(null, "THERE ARE INSUFFICIENT FUNDS TO CREATE THIS CASHIER CHECK!!!!!!!!!!!!!!");
                System.out.println("");
                System.out.println("###################################################################################");
                success = false; // the transaction did not happen
            } else {
                System.out.println("________________________________________");
                System.out.println("");
                System.out.println("CASHIER CHECK of $" + df.format(amount) + " to the Payee: " + nameOfPayee.toUpperCase().trim().replaceAll("\\s+", " ") + " from " + account.getType() + " #" + account.getAccountNumber());
                System.out.println("Your old balance was: $" + df.format(ab)); // show the old balance
                nb = ab - amount; // do the math for the check
                account.setBalance(nb); // set the new balance
                System.out.println("Your new updated balance is: $" + showBalance(account)); // show the new balance
                System.out.println("________________________________________");
                
                System.out.println("");
                
                transNumber = dateFormatter.format(date);// format the date
                StringBuilder builder = new StringBuilder(); // create a string builder for storing the transaction
                // build the transaction for storeing into records
                transaction = builder.append(account.getType()).append(" account,  Account#: ").append(account.getAccountNumber())
                        .append(", ").append(" Cashier Check ").append("of $").append(df.format(amount)).append(", To the payee: ").append(nameOfPayee)
                        .append(",  Old Balance: $").append(df.format(ab)).append(",  New Balance: $").append(df.format(nb));
                // build the transaction for printing to the GUI window
                transactionGui = account.getType() + " accout, Account# " + account.getAccountNumber() + "\nCashier Check of $" + df.format(amount) +
                        "\nName of Payee for Memo; " + nameOfPayee.toUpperCase() + "\nOld balance: $" + df.format(ab) + "\nNew balance: $" + df.format(nb);
                success = true; // the transaction was a success
            }
        } else {
            System.out.println("###################################################################################");
            System.out.println("");
            JOptionPane.showMessageDialog(null, "YOU CAN NOT CREATE A CASHIER CHECK FROM A SAVINGS OR A GROWTH ACCOUNT!!!!!!!!!!!!!!");
            System.out.println("");
            System.out.println("###################################################################################");
            success = false;// the transaction did not happen
        }
        return success; // return whether the transaction happened or not
    }
    
    /**
     * Grow the Growth account 2%
     */
    public boolean growGrowthAccount(Account account)
    {
        boolean success = false; // create a boolean value on whether the transaction happened or not
        String at = account.getType(); // get the account type
        String growth = "Growth";
        date = new Date();// create a new date object
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // create the date formatter
        DecimalFormat df = new DecimalFormat("##.00"); // use the decimal format
        if (at.equalsIgnoreCase(growth)) // if the account type is a growth account then do the transaction
        {   
            double ab = account.getBalance(); // get the account balance
            double nb = 00.00; // set a vaiable to store the new balance in
            System.out.println("________________________________________");
            System.out.println("");
            System.out.println("This was a monthly 2% growth " + "for the Growth account #" + account.getAccountNumber());
            System.out.println("");
            System.out.println("Your old balance was: $" + df.format(ab));
            nb = ab * 1.02; // do the math for the 2% growth
            account.setBalance(nb);
            System.out.println("Your 30 day monthly growth of 2% brings your new balance to: $" + df.format(nb));
            System.out.println("________________________________________");
            double dib = nb - ab; // find out how much the account grew
            System.out.println("");
            transNumber = dateFormatter.format(date); // format the date
            StringBuilder builder = new StringBuilder(); // create a new stringbuilder
            // create a Stringbuilder of the transaction to be stored into records
            transaction = builder.append(account.getType()).append(" account,  Account#: ").append(account.getAccountNumber())
                        .append(", ").append(" Growth ").append("of $").append(df.format(dib)).append(", old balance: $")
                        .append(df.format(ab)).append(", new balance: $").append(df.format(nb)); 
            // create a String of the transaction to print out to the GUI window
            transactionGui = account.getType() + " account, Account#: " + account.generateAccountNumber() + "\nGrowth of $" + df.format(dib) +
                    "\nOld balance: $" + df.format(ab) + "\nNew balance: $" + df.format(nb);
            success = true; // the transaction was a success
        }
        else
        {
            System.out.println("###################################################################################");
            System.out.println("");
            JOptionPane.showMessageDialog(null, "YOU CAN NOT GROW A CHECKING OR SAVINGS ACCOUNT.");
            System.out.println("");
            System.out.println("###################################################################################");
            success = false; // the transaction did not happen
        }
        return success; // return whether the transaction happened or not
    }
    
    /**
     * ShowBalance will show the balance and 
     * put it in decimal format
     * @param account
     * @return the balance in decimal format
     */
    public String showBalance(Account account) {
        DecimalFormat df = new DecimalFormat("##.00"); // decimal format
        double nb = account.getBalance(); // get the account balance
        return df.format(nb); // return the balance
    }

    /**
     * get transaction number
     *
     * @return the global value of transnumber 
     */
    public String getTransNumber() {
        return transNumber; // this is the key value for the records
    }

    /**
     * getTransaction returns the global
     * value of transaction
     * @return the transaction to a string
     */
    public String getTransaction() {
        return transaction.toString(); // i used this to change the string builder 
                                        // back to a string early on.  
    }  
    
    /**
     * return the field transactionGui
     * This field is so I could print the 
     * transaction information to the specified GUI
     * @return 
     */
    public String getTransactionGui()
    {
        return transactionGui; // retrun the custom built transaction record for the GUI window
    }
   
    
    
}
