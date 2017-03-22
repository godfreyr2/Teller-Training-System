/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import tellerLogic1.Transaction;

/**
 * This is Class TransactionRecords. Its main purpose
 * is to be the data base and storage of all 
 * recorded transactions.
 * @author godfreyr2
 * @version 1.0
 */
public class TransactionRecords {

    private TreeMap<String, String> records = new TreeMap<>(); // create a new TreeMap called records

    /**
     * empty constructor for Class TransactioRecords
     */
    public TransactionRecords() {

    }

    /**
     * add Transaction to TreeMap 
     * called records.  
     *
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {

        if (transaction == null) { // if it is null then print error
            System.out.println("Transaction number is Invalid!");
        } else { // else getRecords and store the date and time as the key and the transaction as the value
            getRecords().put(transaction.getTransNumber(), transaction.getTransaction().toString());
        }
    }

    /**
     * Show all transactions in the records database
     */
    public void showTransactionRecords() {
        if(records.size() > 0) // if there are records in the map
        {
            System.out.println("");
            System.out.println("");
            System.out.println("ALL BANK TANSACTIONS:");
            System.out.println("---------------------------------------------");
            Set<String> recKey = getRecords().keySet(); // get the records keyset
            for (String key : recKey) { // for each key
                System.out.println(key); // print key
                System.out.println(getRecords().get(key)); // then print the value
                System.out.println("________________________________________");
            }
        }
        else
        {   // else print an error
            System.out.println("THERE ARE NO TRANSACTIONS TO DISPLAY!!!!");
        }
    }
    
    /**
     * show the transactions in the GUI
     * window with a String return type
     * @return 
     */
    public String showTransactionRecordsGui() {
        
        ArrayList<String> array = new ArrayList<>(); // create the array
        Set<String> recKey = getRecords().keySet(); // get the records .keyset
        for (String key : recKey) { // for each key
            String key1 = key; //get the key
            String value = getRecords().get(key); // get the value
            array.add(key1 + "\n" + value + "\n"); // add the infor gathered to the array
        }
        return array.toString().replace("[", "").replace(",", "").replace("]", ""); // return the array with no ", and[]"
    }
    
    /**
     * get the records size
     * @return 
     */
    public int getRecordsSize() {
        return getRecords().size(); // return the records
    }

    /**
     * get records
     * @return the records
     */
    public TreeMap<String, String> getRecords() {
        return records; // return the TreeMap object called records
    }
}
