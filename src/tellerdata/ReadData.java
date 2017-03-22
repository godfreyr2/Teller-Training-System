/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerdata;

import tellerLogic1.Transaction;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class ReadData reads the customerData.txt file and 
 * builds the information back up when the 
 * program is started
 * @author godfreyr2
 * @version 1.0
 */
public class ReadData {

    private BufferedReader reader; // a global BufferedReader field

    /**
     * @return the result
     */
    public Object[] read() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // time and date formatter
        String word; // Type String called word
        String line; // Type String called line
        TransactionRecords trans = new TransactionRecords(); // create a new instance of TransactionRecords
        BankDataBase bank = new BankDataBase(); // create a new instance of the BankDataBase
        Object[] result = new Object[2]; // an instance of object at the 3 index

        try { // try
            reader = new BufferedReader(new FileReader("customerData.txt")); // put the .txt into the variable reader
            line = reader.readLine(); // put the reader variable and read the next line
            while ((line != null) && (!line.equals(""))) { // while the line is not null and line does not equal null
                String[] array = line.split(","); // an array that is seperated by a comma
                String id = array[0]; // id is at index 0
                String name = array[1]; // name is at index 1
                String address = array[2]; // address is at index 2
                String phoneNumber = array[3]; // phoneNumber is at index 3
                Customer cust = new Customer(id, name, address, phoneNumber); // create the customer object with the gathered infor
                // then start to gather the account info
                for (int i = 4; i < array.length; i += 3) { // create a for loop
                    String accountType = array[i]; // the account type 
                    int accountNumber = Integer.parseInt(array[i + 1]); // account number
                    double balance = Double.parseDouble(array[i + 2]); // balance 

                    if (accountType.equalsIgnoreCase("Checking")) { // if it is a checking account
                        Checking account = new Checking(balance, accountNumber); // create a checking
                        cust.addAccount(account); // add acount
                    } else if (accountType.equalsIgnoreCase("Savings")) {// if it is a savings account
                        Savings account = new Savings(balance, accountNumber); // create a savings
                        cust.addAccount(account); // add account
                    } else if (accountType.equalsIgnoreCase("Growth")) { // if it is a growth account
                        Growth account = new Growth(balance, accountNumber); //create a growth
                        cust.addAccount(account); // add the customer
                    }
                }
                bank.addCustomer(cust); // add the customer object 
                System.out.println(line);
                line = reader.readLine(); // read the next line
            }
            line = reader.readLine(); // reate the next line
            while (line != null) { // while the line is not null keep going
                String[] array = line.split(","); // split new array with commas
                Date date = dateFormatter.parse(array[0]); // date and time stamp at index 0
                StringBuilder builder = new StringBuilder(); // new String Builder
                builder.append(array[1]); // append array at index 1
                for (int i = 2; i < array.length; i++) { // a for loop building the information  until the end starting at index 2
                    builder.append(",").append(array[i]);// build the .appends seperated through commas
                }
                Transaction t = new Transaction(); // create a new transaction
                t.generalTransaction(date, builder); // call the generalTransaction method
                trans.addTransaction(t); // add the transaction

                line = reader.readLine(); // read the next line
            }
            reader.close(); // close the readder
        } catch (FileNotFoundException e) //exceptions are thrown
        {
            System.err.println("Unable to open ");
        } catch (IOException e) {
            System.err.println("A problem was encountered reading ");
        } catch (NumberFormatException | ParseException e) {
            System.err.println("something happened " + e);
        }
        result[0] = trans; // result for transaction
        result[1] = bank; // result2 for the bank

        return result; // return the result
    }

}
