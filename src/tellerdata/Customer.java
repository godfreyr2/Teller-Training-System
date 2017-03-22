/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class customer will hold all attributes
 * that a customer will carry
 * @author godfreyr2
 * @version 1.0
 */
public class Customer {

    private String name; // name of the customer
    private String address; // address of the customer
    private String phoneNumber; // phoneNumber of the customer
    private static int MIN = 100; // MIN for customerID
    private static int MAX = 999; // MAX for customerID
    private Random randomGenerator = new Random(); // random generator
    private String Id; // ID of the customer
    private ArrayList<Account> accounts = new ArrayList<>(); // ArrayLIst of account objects

    /**
     * Empty constructor of class customer
     */
    public Customer() {
    }

    /**
     * 2nd constructor of Class Customer
     * @param name of the customeer
     * @param address of the customer
     * @param phoneNumber of the customer
     */
    public Customer(String name, String address, String phoneNumber) {
        this.name = name.toUpperCase().trim().replaceAll("\\s+", " "); // name trimmed
        this.address = address.toUpperCase().trim().replaceAll("\\s+", " "); // address trimmed
        this.phoneNumber = phoneNumber; // PhoneNumber  
        this.Id = generateCustomerId(); // gerate the customerID
    }

    /**
     * 3rd constructor of Class Customer
     * @param id // Customer ID 
     * @param name name of the customer
     * @param address address of the customer
     * @param phoneNumber phone number of the customer
     */
    public Customer(String id, String name, String address, String phoneNumber) {
        this.name = name.toUpperCase().trim().replaceAll("\\s+", " "); // name trimmed
        this.address = address.toUpperCase().trim().replaceAll("\\s+", " "); // address trimmed
        this.phoneNumber = phoneNumber; // phone Number
        this.Id = id; // Customer ID
    }

    /**
     * Get the name of the customer
     * @return the name
     */
    public String getName() {
        return name; // return the name
    }

    /**
     * Set the name of the customer
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;// set the name
    }

    /**
     * Get the address of the customer
     * @return the address
     */
    public String getAddress() {
        return address; // return the address
    }

    /**
     * Set the address of the customer
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address; // set the address
    }

    /**
     * get the phone Number of the customer
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;  // return the phone number
    }

    /**
     * set the phone number of the customer
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; // set the phone number
    }

    /**
     * change the name of the customer
     * @param newName
     */
    public void changeName(String newName) {
        setName(newName.toUpperCase().trim()); // set the new name
    }

    /**
     * Generate customer id
     * @return 
     */
    public String generateCustomerId() {
        String[] nameArray = getName().toUpperCase().trim().split("\\s+"); // create an array
        String firstLetter = ""; // first letter
        for (int index = 0; index < nameArray.length; index++) { // create a for loop
            firstLetter += nameArray[index].substring(0, 1);// get the first letter in each index
        }
        int number = getRandomGenerator().nextInt(899) + 100; // generate a random number between 100 and 999
        return firstLetter + number; // return the customer ID
    }

    /**
     * getId of customer
     * @return 
     */
    public String getId() {
        return Id; // the ID
    }

    /**
     * Get account numbers
     */
    public void getAccountNumbers() {
        System.out.println("The account numbers for " + getName()); // print heading
        for (Account num : accounts) { // for each Account object
            System.out.println(num.getAccountNumber()); // print each object
        }
    }

    /**
     * ToString method for Class Customer
     * @return name address phoneNumber ID
     */
    @Override
    public String toString() {
        return "Customer name: " + getName() + "\n" + "Address: " + getAddress() + "\n" + "Customer phone number: " + getPhoneNumber() + "\n" + "Customer id: " + Id + "\n";
    }

    /**
     * @return the randomGenerator
     */
    public Random getRandomGenerator() {
        return randomGenerator; // return the random generator
    }

    /**
     * get Accounts ArrayList
     * @return the accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts; // return accounts
    }

    /**
     * add account object
     * @param account
     */
    public void addAccount(Account account) {
        accounts.add(account); // add the account
        

    }
    
    /**
     * Show Accounts information
     * @param account
     * @return account number and balance
     */
    public String showAccount(Account account)
    {
        DecimalFormat df = new DecimalFormat("##.00");// decimal format
        String acc = Integer.toString(account.accountNumber); // get the account number
        String bal = Double.toString(account.getBalance()); // get the balance
        return "Account Number: " + acc + "\n" + "Balance: " + df.format(bal); // return the number and balance
    }


    /**
     * remove account object
     * @param account
     */
    public void removeAccount(Account account) {
        accounts.remove(account); // remove the account object
    }

    /**
     * Show ArrayList for GUI window
     * with a String return type
     * @return 
     */
    public String showAccounts() {
        ArrayList<String> array = new ArrayList<>(); // create a new array
        for (Account account : accounts) { // for each Account object
              String acc = account.toString(); // get the account
              array.add(acc + "\n"); // add the infor to the array
        }
        return array.toString().replace("[", "").replace(",", "").replace("]", ""); // return the array with no ", and []"
    }
    
    /**
     * get Account by account number
     * @param accountNumber
     * @return the account
     */
    public Account getAccount(int accountNumber)
    {
        for(Account ac : accounts) // for each Account object
        {
            if(ac.getAccountNumber() == accountNumber) // if the number matches the input number
            {
                return ac; // return it
            }
        }
        return null; // or return null
    }
    
   
}
