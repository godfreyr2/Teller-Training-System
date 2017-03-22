/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * This is Class WriteData
 * It writes all of the Data to the dataBase
 * when the program is terminated in several different
 * ways.  A window listener JFrame when closed, and an exit menu item will both
 * trigger this class to initiate and start writing all of the 
 * data to text file "customerData.txt"
 * @author godfreyr2
 * @version 1.0
 */
public class WriteData {

    FileWriter fw; // a global FileWriter 

    /**
     * Write will start reading the data and preparing it 
     * to write to the text file
     * @param customers
     * @param records
     */
    public void write(HashMap<String, Customer> customers, TreeMap<String, String> records) {

        try {
            fw = new FileWriter("customerData.txt", false); // write to the file inputs.txt
            fw.write("");

            if (!customers.equals(null)) {// if customers does not equal null

                for (String key : customers.keySet()) { // for each key 

                    String id = key; // get the key 
                    String customersName = customers.get(key).getName();// get the customer name
                    String address = customers.get(key).getAddress();// get the address
                    String phoneNumber = customers.get(key).getPhoneNumber(); // get the phonenumber

                    fw.append(id + "," + customersName + "," + address + "," + phoneNumber); // start to build the string
                    ArrayList<Account> accounts = customers.get(key).getAccounts(); // get the customer accounts
                    DecimalFormat df = new DecimalFormat("##.00"); // use decimal format
                    for (Account ac : accounts) { //for each Account object

                        String accountType = ac.getType(); // get the account type
                        int accountNumber = ac.getAccountNumber(); // get the account number
                        double balance = ac.getBalance(); // geet the balance
                        fw.append("," + accountType + "," + accountNumber + "," + df.format(balance));// start to build the string
                    }
                    fw.append("\r\n"); // combine them
                }
                fw.append("\r\n"); // combine until empty line
            }
            if (!records.equals(null)) { // if records does not equal null
                for (String key : records.keySet()) { // for each key in records
                    String time = key; // get the time
                    String transaction = records.get(key); // get the transaction
                    fw.append(time + "," + transaction); // combine them
                    fw.append("\r\n"); // write until an emty line
                }
            }
            fw.close(); // close the fileWriter
        } catch (IOException e) {
            System.err.println("Unable to create or open file."); // print error if you can not write to inputs.txt 
        }
    }
}
