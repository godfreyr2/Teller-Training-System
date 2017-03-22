/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.util.Random;
import java.text.DecimalFormat;

/**
 * This is Class Account and it is 
 * an Abstract Class.  It will have three
 * subclasses. (Checking, Savings, Growth)
 * It will hold all of the common attributes that 
 * all accounts will share
 * @author godfreyr2
 * @Version 1.0
 */
public abstract class Account {
    protected int accountNumber; // the account number
    public double balance; // balance of the account
    private static int MIN = 10000; // MIN boundaries for random account number
    private static int MAX = 99999;//Max boundaries for random account number
    private Random randomGenerator = new Random(); // Create a new random
    DecimalFormat df = new DecimalFormat("##.00"); // bring in the decimal format

    /**
     * Constructor for Class Account
     * 
     */
    public Account() {

        this.accountNumber = generateAccountNumber(); // random account number
        this.balance = 00.00; // initialize the balance

    }

    /**
     * 2nd constructor for Account
     * @param balance the balance of the account
     * @param account the account number
     */
    public Account(double balance, int account) {
        this.accountNumber = account; // account number
        this.balance = balance; // account balance
    }

    /**
     * get the account number
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber; // return the account number
    }

    /**
     * Get the balance of the account
     * @return the balance
     */
    public double getBalance() {
       
        return balance; // the balance of the account
    }

    /**
     * set the balance of the account
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance; // set the balance
    }


    /**
     * Generate account number by using a random
     * number between 10000 and 99999
     * 
     * @return return the account number
     */
    public int generateAccountNumber() {

        int number = getAccountRandomGenerator().nextInt(89999) + 10000; // generate a new random
        return number; // return the new number
    }

    /**
     * Create the random generator
     * @return the randomGenerator
     */
    public Random getAccountRandomGenerator() {
        return randomGenerator; // return the random generator
    }

    /**
     * to string method for specified fields
     *
     * @return account number and the balance of the account
     */
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.00"); // decimal fomat
        // return the account number and balance
        return "Your Account number: " + accountNumber + "\n" + "Your balance is: $" + df.format(balance); // 
    }

    /**
     * get account type
     *
     * @return the actual account type (checking, growth, savings)
     */
    abstract public String getType();
}
