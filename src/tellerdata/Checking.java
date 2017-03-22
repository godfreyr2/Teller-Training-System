/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.text.DecimalFormat;

/**
 * This is Class Checking and is a subclass 
 * of Account. It hold all attributes
 * that are unique to a checking account.
 * @author godfreyr2
 * @version 1.0
 */
public class Checking extends Account {

    private String type = "Checking"; // the type of account

    /**
     * empty constructor of Class Checking
     */
    public Checking() {
        super(); // call on the superClass Account
    }

    /**
     * 2nd constructor of Class Checking
     * @param balance the balance of the account
     * @param account the account number
     */
    public Checking(double balance, int account) {
        super(balance, account); // call on the superClass
    }

    /**
     * get the account type
     * @return the type
     */
    @Override
    public String getType() {
        return type; // account type
    }

    /**
     * to string method for Class Checking
     *
     * @return type account number and balance
     */
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.00");
        return "Your " + type + " Account number: " + accountNumber + "\n" + "Your balance is: $" + df.format(balance);
    }
}
