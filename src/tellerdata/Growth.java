/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.text.DecimalFormat;

/**
 * Class Growth is a subclass of 
 * Account. It will hold all attributes
 * that are unique to a Growth account
 * @author godfreyr2
 * @version 1.0
 */
public class Growth extends Account {

    private String type = "Growth"; // account type
    private double growth;

    /**
     * constructor of Classs Growth
     */
    public Growth() {
        super(); // calls the superClass of Account
        this.growth = growth;
    }

    /**
     *2nd constructor of Class Growth
     * @param balance
     * @param account
     */
    public Growth(double balance, int account) {
        super(balance, account); //calls of the super
    }

    /**
     * to string method for Class Growth
     *
     * @return type account number balance
     */
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.00"); // decimal format
        return "Your " + type + " Account number: " + accountNumber + "\n" + "Your balance is: $" + df.format(balance);
    }

    /**
     * Get type of account
     * @return the type
     */
    public String getType() {
        return type; // return the account type
    }
}
