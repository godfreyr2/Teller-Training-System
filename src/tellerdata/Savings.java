/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.text.DecimalFormat;

/**
 * Class Savings is a subclass
 * of Class Account. It will hold all
 * attributes that are unique to Class Savings
 * @author godfreyr2
 * @version 1.0
 */
public class Savings extends Account {

    private String type = "Savings"; // the account type is Savings

    /**
     * Empty constructor of Class Savings
     */
    public Savings() {
        super(); // calls on the superClass Account

    }

    /**
     * 2nd constructor of Class Savings
     * @param balance of the account
     * @param account number
     */
    public Savings(double balance, int account) {
        super(balance, account); // calls on the superClass
    }

    /**
     * to string method of Class Savings
     *
     * @return type account number and balance
     */
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.00"); // decimal format
        return "Your " + type + " Account number: " + accountNumber + "\n" + "Your balance is: $" + df.format(balance);
    }

    /** 
     * Get the account type
     * @return the type
     */
    @Override
    public String getType() {
        return type; // return type of account
    }

}
