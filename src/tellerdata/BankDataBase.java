/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import tellergui.*;

/**
 * Class BankDataBase will hold a HashMap of 
 * customer objects.  I will iterate through
 * the customer information several different ways
 * in this class.  It is the main storage
 * DataBase for all customer information
 * @author godfreyr2
 * @version 1.0
 */
public class BankDataBase {
    // create a new HashMap with a customer ID as the key and the customer object as the Value
    private HashMap<String, Customer> customers = new HashMap<String, Customer>();
    
    // empty constructor for the DataBase
    public BankDataBase() {

    }

    /**
     * add customer to the DataBase
     *
     * @param customer object
     */
    public void addCustomer(Customer customer) {

        if (customer == null) { // if the customer object is null
            System.out.println("Customer is Invalid"); // throw an error
        }
        if (getCustomers().containsKey(customer.getId())) {// if the key already exists print error
            System.out.println("CUSTOMER ALREADY EXISTS: " + customer.getId()); // print error
        } else {

            getCustomers().put(customer.getId(), customer); // if no error than store the ID and customer
            System.out.println("________________________________________________________________________________________");
            System.out.println("");
            System.out.println("Added customer successful. Here is their Customer ID: " + customer.getId());// print successful

        }
    }

    /**
     * remove customer by a customer 
     * ID number
     *
     * @param inputId the customer ID
     */
    public void removeCustomerById(String inputId) {
        String id = inputId.toUpperCase().trim().replaceAll("\\s+", " "); // get the input ID and trim
        if (id == null) { // if it doesn't exist
           JOptionPane.showMessageDialog(null, "ID is INVALID"); // print error
        }
        if (!customers.containsKey(id)) { // if it exists already
            JOptionPane.showMessageDialog(null, "NON-existing: " + id); // print error
        } else {
            customers.remove(id);// else remove the customer from the map
        }
    }
    
    /**
     * is the customer name valid?
     * If it does not exist in the collection then 
     * return false
     * @param inputName
     * @return 
     */
    public boolean checkName(String inputName)
    {
        boolean result = false; // set a boolean to return the result
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " "); // trim the name
        for (String key : customers.keySet()) // for every key in customers
        {
            String nameOf = customers.get(key).getName(); // get the customer name
            if(nameOf.equalsIgnoreCase(name))// if we have a match
            {
                result = true; // set the result to true
            }
        }
        return result; // return true or false on whether the name is valid
    }
    
    /**
     * is the ID valid?
     * If it does not exist in the colloection then
     * return false otherwise return true
     * @param inputId
     * @return 
     */
    public boolean checKId(String inputId)
    {
        boolean result = false; // create a boolean for the result
        String id = inputId.toUpperCase().trim().replaceAll("\\s+", ""); // trim the id
        for (String key : customers.keySet()) // for each key in customers
        {
            String ID = customers.get(key).getId(); // get the customer id
            if(ID.equalsIgnoreCase(id)) // if we have a match
            {
                result = true; // set the result to true
            }
        }
        return result; // return true or false on if the ID is valid
    }
    
    /**
     * remove customer by a name
     * of a customer
     * @param inputName the name of the customer to be removed
     */
    public void removeCustomerByName(String inputName) {
        boolean found = false; // create a boolean for if found or not
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " "); // trim the name
        for (Iterator<String> it = customers.keySet().iterator(); it.hasNext();) { // create an iterator
            String key = it.next(); // the next item
            if (customers.get(key).getName().equalsIgnoreCase(name)) { // if the name matches the input name
                it.remove(); // remove it
                found = true; // found the name
                System.out.println("CUSTOMER: " + name + " WAS REMOVED SUCCESSFULLY"); // print confirmation
            }
        }
        if (!found) { // if not found
            System.out.println("NO CUSTOMERS FOUND WITH THE NAME: " + name); // print error
        }
        System.out.println("##################################################");
    }

    /**
     * get Customer objects
     * @return the customers
     */
    public HashMap<String, Customer> getCustomers() {
        return customers; // return customer object
    }

    /**
     * show customer information of a specified 
     * input by name
     * @param inputName the name in search
     */
    public void showCustomer(String inputName) {
        boolean found = false; // have we found the name?
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " "); // trim the input
        for (Iterator<String> it = customers.keySet().iterator(); it.hasNext();) { // create an iterator
            String key = it.next(); // the next item
            if (customers.get(key).getName().equalsIgnoreCase(name)) { // do we have a match
                String id = customers.get(key).getId(); // get the id
                System.out.println("--------------------------------------------------");
                System.out.println("cust id: " + id);//print the id
                System.out.println("name: " + customers.get(key).getName()); // print the name
                System.out.println("address: " + customers.get(key).getAddress()); // print the address
                System.out.println("phone number: " + customers.get(key).getPhoneNumber()); // print the phonenumber
                ArrayList<Account> accounts = customers.get(key).getAccounts();// search the arraylist of accounts
                for (Account ac : accounts) { // for each account object
                    double bal = ac.getBalance();// get balance
                    System.out.println("account: " + ac.getType() + " - #" + ac.getAccountNumber() + " - $" + bal); // prit to string
                }
                System.out.println("--------------------------------------------------");
                found = true; // found the customer
            }
        }
        if (!found) { // if not found print error
            System.out.println("NO CUSTOMERS FOUND WITH THE NAME: " + name);
        }
    }

    /**
     * Show customer information by a specified
     * input name
     * show customer of Type String
     * @param inputName the name in search
     * @return name account number and balance or null if not found
     */
    public String showCustomerAndBalance(String inputName) {
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " ");// trim name
        for (Iterator<String> it = customers.keySet().iterator(); it.hasNext();) { // create an iterator
            ArrayList<String> array = new ArrayList<>(); // new arraylist 
            String key = it.next(); // the next item
            if (customers.get(key).getName().equalsIgnoreCase(name)) { // if we have a match
                String id = customers.get(key).getId(); // get the id
                String nameOfCustomer = customers.get(key).getName(); // get the name
                ArrayList<Account> accounts = customers.get(key).getAccounts(); // get the accounts 
                for (Account ac : accounts) { // for each loop over account objects
                    String account = Integer.toString(ac.getAccountNumber()); // get the account number
                    String bal = Double.toString(ac.getBalance()); // get the balance
                    return nameOfCustomer + "\n" + "Account number: " + account + "Balance: " + bal; // return the name number and balance

                }

            }
        }
        return null; // return null
    }

    /**
     * show All accounts in the HashMap
     */
    public void showAllAccounts() {
        if (customers.size() > 0) { // if there are cusomers
            System.out.println("");
            System.out.println("");
            System.out.println("ALL CUSTOMERS AT THE BANK:");
            System.out.println("----------------------------------------");
            Set<String> ids = getCustomers().keySet(); // get the customers keys
            for (String st : ids) { // iterate over the ids
                System.out.println(getCustomers().get(st)); // get the id numbers
                getCustomers().get(st).showAccounts(); // get the customers and the accounts
                System.out.println("________________________________________");
            }
        } else { // else print error if there are no customers
            System.out.println("THERE ARE NO CUSTOMERS TO DISPLAY!!!!");
        }
    }

    /**
     * Show All accounts on the GUI window
     * show Accounts
     * @return return the arrayList of the gathered customer information
     */
    public String showAllAccountsGui() {

        ArrayList<String> array = new ArrayList<>(); // create a new arrayLise
        Set<String> ids = getCustomers().keySet();// get the customer keys
        for (String st : ids) { // for each idnumber
            String cust = getCustomers().get(st).toString(); // gete the customer
            getCustomers().get(st).showAccounts();// show customers and show accounts
            array.add(cust + getCustomers().get(st).showAccounts() + "\n"); // add the info to an arraylist
        }
        return array.toString().replace("[", "").replace(",", "").replace("]", "");// format the arraylist to be printed remove all ", and {}"
    }

    /**
     * Get Customer to add to an account
     *
     * @param inputName name of customer
     * @param account account object
     */
    public void getCustomerToAddAccount(String inputName, Account account) {
        boolean found = false; // did we find the customer
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " ");// trim the name
        for (String key : customers.keySet()) { // for each customer
            if (customers.get(key).getName().equalsIgnoreCase(name)) {// if we have a match
                Customer cust = customers.get(key);  // get the customer
                cust.addAccount(account); // add an account 
                found = true; // found the customer
                System.out.println("");
                System.out.println("Added account to " + name);
                cust.showAccounts(); // show the accounts to that name
            }
        }
        if (!found) {// if no customer then print error
            System.out.println("NO CUSTOMERS FOUND WITH THE NAME: " + name);
        }
    }

    /**
     * Get the customer and account number
     * show customer name and account numbers
     * @return arrayList of the built information 
     */
    public String getCustomerAndAccountNumber() {
        ArrayList<String> array = new ArrayList<>(); // create a new arraylist
        for (String key : customers.keySet()) { // get the customers
            Customer cust = customers.get(key);// get the customer
            String name = cust.getName(); // get thet customer name
            String id = cust.getId(); // get the customer id
            ArrayList<Account> accounts = customers.get(key).getAccounts(); // get the accounts of all customers
            for (Account ac : accounts) { // for each account object
                DecimalFormat df = new DecimalFormat("##.00"); // decimal format
                String bal = Double.toString(ac.balance); // get the balance
                String num = Integer.toString(ac.getAccountNumber()); // get the account number
                String type = ac.getType(); // get the account number
                array.add(name + " - " + id + "\n" + type + " - " + num + "\n" + "$" + bal + "\n"); // add the infor to the array
            }

        }
        return array.toString().replace("[", "").replace(",", "").replace("]", ""); // return the array and replace all ", and []"
    }

    /**
     * Show customer account numbers 
     * by a name search
     * show customer
     * @param inputName the name in search
     */
    public void showCustomerAccountNumbers(String inputName) {
        boolean found = false;// did we find the name?
        String name = inputName.toUpperCase().trim().replaceAll("\\s+", " "); // trim the name
        for (Iterator<String> it = customers.keySet().iterator(); it.hasNext();) { // create an iterator
            String key = it.next(); // the next item
            if (customers.get(key).getName().equalsIgnoreCase(name)) { // is there a match
                ArrayList<Account> accounts = customers.get(key).getAccounts(); // the accounts arrayList
                for (Account ac : accounts) { // for each account object
                    int num = ac.getAccountNumber(); // get the account number
                    String type = ac.getType(); // get the account type
                    System.out.println(type + " - " + num);
                    found = true;// found the name
                }
            }
        }
        if (!found) { // if not found then print error
            System.out.println("NO CUSTOMERS FOUND WITH THE NAME: " + name);
        }
    }

    /**
     * Find customer account by account number
     * @param inputName name in search
     * @param accountNumber in search
     * @return account object
     */
    public Account findCustomerAccountByNumber(String inputName, int accountNumber) {
        Account a = null; // create Account variable
        int ac = accountNumber; // get the account number
        for (String key : customers.keySet()) { // for each custoemr Keyset
            Customer cust = customers.get(key); // get the customer object
            if (cust.getName().equalsIgnoreCase(inputName)) { // if there is a match
                a = cust.getAccount(accountNumber); // get the account object
            }
        }
        return a; // return the account
    }

    /**
     * Show Customer all customers
     * with a String return type
     *
     * @return
     */
    public String showAllCustomerNames() {

        ArrayList<String> returnArray = new ArrayList<>(); // create a new Array
        for (String key : customers.keySet()) { // for each customers key
            returnArray.add(customers.get(key).getName() + "\n"); // add the information to the array

        }
        return returnArray.toString().replace("[", "").replace(",", "").replace("]", ""); // return the array with no ", and []"
    }

    /**
     * Show Customer Id's with a 
     * String return type
     *
     * @return the customers IDs
     */
    public String showAllCustomerIds() {
        ArrayList<String> idArray = new ArrayList<>(); // create a new Array
        for (String key : customers.keySet()) { // for each customers key
            String id = key; // get id  
            String customersName = customers.get(key).getName(); // get name
            idArray.add(id + "    <----    " + customersName + "\n"); // add the gathered info

        }
        return idArray.toString().replace("[", "").replace(",", "").replace("]", ""); // return the array with not ", and []"
    }

    /**
     * show customer name and account numbers
     * prepared for the GUI window with a String return type
     * @return the array created
     */
    public String showAllAccountsToGui() {
        ArrayList<String> array = new ArrayList<>(); // create a new ArrayList
        for (String key : customers.keySet()) { // for each customer key    
            Customer cust = customers.get(key); // get the customer
            String name = cust.getName(); // get the name
            String address = cust.getAddress(); // get address
            String phoneNumber = cust.getPhoneNumber(); // get phoneNumber  
            String id = cust.getId(); // get the id
            ArrayList<Account> accounts = customers.get(key).getAccounts(); // get the accounts

            for (Account ac : accounts) { // for each account object
                DecimalFormat df = new DecimalFormat("##.00"); // decimal format
                String bal = Double.toString(ac.balance); // get the balance
                String num = Integer.toString(ac.getAccountNumber()); // get the accont number
                String type = ac.getType(); // get the account type
                array.add(name + " - " + id + "\n" + type + " - " + num + "\n" + "$" + bal + "\n"); //add the gathered infor
            }

        }
        return array.toString().replace("[", "").replace(",", "").replace("]", ""); // return infor with no ", 
    }
    
}
