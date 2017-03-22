/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import tellerdata.*;
import java.util.Scanner;
import tellerdata.TransactionRecords;
import tellerdata.BankDataBase;
import tellerdata.Checking;
import tellerdata.Customer;
import tellerdata.Savings;
import tellerLogic1.TellerMenu;
import tellerLogic1.Transaction;
/**
 * This is the Driver for the Teller Training System of Mega Bank
 * It will hold the main method that controls the entire program. 
 * 
 * @author godfreyr2
 * @version 1.0
 */
public class Driver {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    
       public static void main(String[] args) throws InterruptedException {
        ReadData dataRead = new ReadData(); // instance of class ReadData.  
        Object[] array = dataRead.read(); // reads the text file where all transaction and customer info is stored
        
        BankDataBase bank = (BankDataBase) array[1]; // instance of BankDataBase to help the reader
        TransactionRecords transaction = (TransactionRecords) array[0]; // instance of TransactionRecords to help the reader
        
        Transaction trans = new Transaction(); // instance of the Transaction class so the driver can call on its methods
        
        TellerMenu menu = new TellerMenu(); // intance of TellerMenu so it can print out the menu options for the user
        
        System.out.println(""); // line for cosmetic purposes
        // Create the menu options
        menu.addMenuItem("Add Customer"); //1 
        menu.addMenuItem("Remove Customer by Name");//2
        menu.addMenuItem("Remove Customer by Customer ID");//3
        menu.addMenuItem("Create Account"); //4
        menu.addMenuItem("Deposit");//5
        menu.addMenuItem("Withdraw"); //6
        menu.addMenuItem("Transfer"); //7
        menu.addMenuItem("Money Order"); //8
        menu.addMenuItem("Cashier Check"); //9
        menu.addMenuItem("Grow the GROWTH account"); //10
        menu.addMenuItem("Display Customer information"); //11
        menu.addMenuItem("Display All Customer Information"); //12
        menu.addMenuItem("Display All Transactions"); //13
        
       
        boolean stillSearching = true;// set boolean for switch statement loop
        Scanner scan = new Scanner(System.in); // instance of a scannner for user input
        
        while(stillSearching) // the boolean value that will end the loop when the user types '0' for quit
        {
            System.out.println(""); // line for cosmetic purpose
            System.out.println("0 : TO QUIT"); // prints on top of the menu
            menu.displayMenu(); // display menu
            System.out.println("");// line for cosmetic purpose
            System.out.println("---------------------------------------------------------------------------------------------");            
            System.out.println("This is the MEGA BANK Teller Training System. \n"
                    + "Please select an item from the following teller menu by choosing a menu number then pressing 'enter' \n"
                    + "After making your selection, you will be prompted with further instructions to complete each task.\n"                   
                    + "When you are finished please select '0' then 'enter' to end the Teller Training System.");
            // instructions for the user
            System.out.println("1st - ADD A CUSTOMER"); 
            System.out.println("2nd - CREATE 1 OR SEVERAL DIFFERENT ACCOUNT TYPES FOR THAT CUSTOMER");
            System.out.println("3rd - MAKE A DEPOSIT INTO THAT ACCOUNT");
            System.out.println("4th - MAKE SURE YOU HAVE FUN WITH THE REST!!!!");
            int selection = scan.nextInt(); // scan the user input for the menu selection
            scan.nextLine(); // necessary scan.nextLine after scan.nextInt
            switch(selection) // start the switch statement
            {
                case 0: stillSearching = false; // 0 means quit
                        break;
                        // Case 1 adds a new customer
                case 1: System.out.println("You chose [ADD CUSTOMER].\nType the name of the customer.  (Ex: FirstName MiddleName LastName)");
                        String name = scan.nextLine(); // scan next line
                        System.out.println("Type the address of the customer with NO COMMAS.  (Ex: 123 Main st. SpringField N.J.");
                        String address = scan.nextLine(); // scan next lint
                        System.out.println("Type the phoneNumber of the customer.  (Ex: 123-456-7890)");
                        String phoneNumber = scan.nextLine(); // scan next line
                        bank.addCustomer(new Customer(name, address, phoneNumber)); // create new customer object
                        bank.showCustomer(name); // show whether the customer was added or get an error message if it was not
                        System.out.println("##################################################");
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu.");
                        // loop created for bettevariabler functionality and readability.  It will not return to the main menu unless user types m
                        String back = ""; // create String 
                        boolean valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m"); // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        // case 2 will remove a customer by cutomer name
                case 2: bank.showAllCustomerNames(); // show a list of available customers
                        System.out.println("You chose [REMOVE CUSTOMER BY NAME].\nType the name of the customer.  (Ex: FirstName MiddleName LastName)");
                        String nameToBeRemoved = scan.nextLine(); // scan user input of name
                        bank.removeCustomerByName(nameToBeRemoved); // call the removeCustomerByName method
                        System.out.println("Select 'm' to return to main menu.");
                        // loop created for better functionality and readability.  It will not return to the main menu unless user types m
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true 
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m");   // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        // case 3 removes a customer by way of the customer ID
                case 3: bank.showAllCustomerIds(); // show a list of available customers
                        System.out.println("You chose [REMOVE CUSTOMER BY ID].\nType the id of the customer.  (Ex: ABC123)");
                        String idToBeRemoved = scan.nextLine(); // scan the user input of ID to be removed
                        bank.removeCustomerById(idToBeRemoved); // call the removeCustomerById method
                        System.out.println("Select 'm' to return to main menu.");
                        // loop created for better functionality and readability.  It will not return to the main menu unless user types m
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m"); // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        // case 4 creates a customer account
                case 4: System.out.println("You chose [CREATE ACCOUNT].\nWhat type of account do you wish to create?  (Ex: Checking - Savings - Growth");
                        String typeOfAccount = scan.nextLine(); // scan user input for account type
                        bank.showAllCustomerNames(); // show a list of available customers
                        System.out.println("Type the Customer name you wish to add this account to.");
                        String inputName = scan.nextLine(); // scan user input for customer name
                        System.out.println("________________________________________________________________________________________");
                        if(typeOfAccount.equalsIgnoreCase("Checking")) // if the account type is checking create a checking account object
                        {
                            Checking checking = new Checking(); // instance of checking account
                            bank.getCustomerToAddAccount(inputName, checking); // add checking account to customer name
                        }
                        else if(typeOfAccount.equalsIgnoreCase("Savings")) // if the account type is savings creat a savings account object
                        {
                            Savings savings = new Savings(); // instance of savings account
                            bank.getCustomerToAddAccount(inputName, savings); //add savings account to customer name
                        }
                        else if(typeOfAccount.equalsIgnoreCase("Growth")) // if 
                        {
                            Growth growth = new Growth(); // instance of growth account
                            bank.getCustomerToAddAccount(inputName, growth); // add growth account 
                        }
                        System.out.println(""); // line for cosmetic 
                        System.out.println("Select 'm' to return to main menu."); // if user types m to return to menu then valid is set to true 
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m");   // if user types m to return to menu then valid is set to true
                        }
                        break;
                        
                        // case 5 creates makes a deposit
                case 5: bank.showAllCustomerNames(); //show a list of available customers
                        System.out.println("You chose [DEPOSIT].\nType the name of the customer you would like to make a deposit for.");
                        String nameToDeposit = scan.nextLine(); // scan user input for customer name
                        bank.showCustomerAccountNumbers(nameToDeposit); // show a list of available customers
                        System.out.println("Please type in the account number you want to use.");
                        int accountNumber = scan.nextInt(); // scan user input for of cuntomer account number
                        System.out.println("Please type the amount to DEPOSIT.  (Ex: 100.00)");
                        double amount = scan.nextDouble(); // scan user input of amount to deposit
                        Account dac = bank.findCustomerAccountByNumber(nameToDeposit, accountNumber); // get the account object and store it in a variable                      
                        trans.deposit(dac, amount); // call on the deposit method
                        transaction.addTransaction(trans); // add transaction to the records
                        System.out.println(""); // line for cosmetic purpose
                        System.out.println("Select 'm' to return to main menu.");  // if user types m to return to menu then valid is set to true
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m");   // if user types m to return to menu then valid is set to true
                        }
                        break;
                        
                        // case 6 handles the withdraw
                case 6: bank.showAllCustomerNames(); // show a list of available customers
                        System.out.println("You chose [WITHDRAW].\nType the name of the customer you would like to make a withdraw for.");
                        System.out.println("ONE CAN ONLY WITHDRAW FROM A CHECKING ACCOUNT.");
                        String nameToWithdraw = scan.nextLine(); // scan user input for customer name to withdraw from 
                        bank.showCustomerAccountNumbers(nameToWithdraw); // show a list of the customers account numbers
                        System.out.println("Please type in the account number you want to use.");
                        int accountNumberToWithdraw = scan.nextInt(); // scan user input for the account number to be used
                        System.out.println("Please type the amount to WITHDRAW.  (Ex: 100.00)"); 
                        double withdrawAmount = scan.nextDouble(); // scan user input for amount to be withdrawed
                        Account wac = bank.findCustomerAccountByNumber(nameToWithdraw, accountNumberToWithdraw); //get the account object and store it in a variable
                        trans.withdraw(wac, withdrawAmount); // call on the transfer method
                        transaction.addTransaction(trans); // add the transaction to the records
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // if user types m to retrun to menu then valid is set to true
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m");   // if user types m to return to menu then valid is set to true
                        }
                        break;
                        
                        //case 7 handles the transfer
                case 7: bank.showAllCustomerNames(); // show a list of customers
                        System.out.println("You chose [TRANSFER].\nType the name of the customer you would like to transfer FROM."); 
                        String nameFrom = scan.nextLine(); // scan user input for the customer name
                        bank.showCustomerAccountNumbers(nameFrom); // show a list of account numbers owed by the customer
                        
                        System.out.println("Type in the account number you want to transfer FROM.");
                        int atf = scan.nextInt(); // scan the user input of the account number to transfer FROM
                        
                        System.out.println("Type the amount you want to TRANSFER.  (Ex: 100.00)");
                        double transferAmount = scan.nextDouble(); // scan the user input on the amount to be transferred
                        scan.nextLine(); // unused necessary scan.nextLine after an int
                        
                        System.out.println("Type the name of the customer you would like to transfer TO.");
                        String nameTo = scan.nextLine(); // scan user input of the customer name to transfer TO
                        bank.showCustomerAccountNumbers(nameTo); // show a list of customer numbers owned by that customer
                        
                        System.out.println("Type in the account number you want to transfer TO.");
                        int att = scan.nextInt();  // scan the user input of account number to transfer TO 
                        Account accountFrom = bank.findCustomerAccountByNumber(nameFrom, atf); // get account object of the account to transfer from 
                        Account accountTo = bank.findCustomerAccountByNumber(nameTo, att); // get account object of the account to transfer to
                        trans.transfer(accountFrom, accountTo, transferAmount); // call on the trasnfer method
                        transaction.addTransaction(trans);// add the transaction to the records
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // if user types m to retrun to menu then valid is set to true
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // scan user input
                            valid = back.equalsIgnoreCase("m");  // if user types m to return to menu then valid is set to true 
                        }
                        break;
                        
                        // case 8 handles the Money Order
                case 8: bank.showAllCustomerNames(); // show a list of available customer names
                        System.out.println("You chose [MONEY ORDER].\nType the name of the customer you would like to create a MONEY ORDER for.");
                        System.out.println("ONE CAN ONLY CREATE A MONEY ORDER FROM A CHECKING ACCOUNT.");
                        String nameForMoneyOrder = scan.nextLine(); // scan user input of customer name for money order
                        bank.showCustomerAccountNumbers(nameForMoneyOrder); // show a list of available customer account numbers
                        System.out.println("Please type in the account number you want to use.");
                        int accountForMoneyOrder = scan.nextInt(); // scan user input for account number to use in the money order
                        System.out.println("Please type the amount of the MONEY ORDER.  (Ex: 100.00)");
                        double moneyOrderAmount = scan.nextDouble(); // scan user input for the amount of the money Order
                        Account moac = bank.findCustomerAccountByNumber(nameForMoneyOrder, accountForMoneyOrder); // get the account object
                        trans.moneyOrder(moac, moneyOrderAmount); // call on the Money Order method
                        transaction.addTransaction(trans); // add the transaction to the records
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // if user types m to retrun to menu then valid is set to true
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // back = scan user input
                            valid = back.equalsIgnoreCase("m");  // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        // case 9 handles the cashier check
                case 9: bank.showAllCustomerNames();  // show a list of available customer names
                        System.out.println("You chose [CASHIER CHECK].\nType the name of the CUSTOMER you would like to create a CASHIER CHECK for.");
                        System.out.println("ONE CAN ONLY MAKE A CASHIER CHECK FROM A CHECKING ACCOUNT.");
                        String nameForCaCh = scan.nextLine(); // scan user input for the customer name that the check will be made from 
                        bank.showCustomerAccountNumbers(nameForCaCh); // show a list of available customers
                        System.out.println("Please type in the account number you want to use.");
                        int accNumForCaCh = scan.nextInt(); // scan user input for the account number to be used
                        System.out.println("Please type the amount of the CASHIER CHECK  (Ex: 100.00)");
                        double amountForCaCh = scan.nextDouble(); // scan the user input of the amount the money order
                        scan.nextLine(); // unused necessary scan.nextLine after scan.next int
                        System.out.println("Type the name of the person that the CASHIER CHECK is to be made out to.");
                        String nameOfPerson = scan.nextLine();// scan user input for the name that the check is to be made out to
                        Account CaCh = bank.findCustomerAccountByNumber(nameForCaCh, accNumForCaCh); // get the account object by account number
                        trans.cashierCheck(CaCh, nameOfPerson, amountForCaCh); // call on the cahshier cachier check method
                        transaction.addTransaction(trans);// add the transaction to the records
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // the user must type m to return to the menu
                        valid = false;// valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true 
                        {
                            back = scan.nextLine(); //back = scan user input
                            valid = back.equalsIgnoreCase("m"); // if user types m to return to menu then valid is set to true    
                        }
                        break;
                        
                        // case 10 will grow a specified Growth account 2% 
                case 10:bank.showAllCustomerNames();  // show a list of available customer names
                        System.out.println("You chose [GROW THE GROWTH ACCOUNT]. \nType the name of the CUSTOEMER you would like to service in growing 2%");
                        String gan = scan.nextLine();
                        bank.showCustomerAccountNumbers(gan);
                        System.out.println("Please type in the account number you want to use.");
                        int acNumForGrowth = scan.nextInt();
                        scan.nextLine();
                        Account accForGrowth = bank.findCustomerAccountByNumber(gan, acNumForGrowth);
                        System.out.println("Would you like to grow the account?   Type  ('y' OR 'n)");
                        String answer = scan.nextLine();
                        if(answer.equalsIgnoreCase("y"))
                        {
                            trans.growGrowthAccount(accForGrowth);
                            transaction.addTransaction(trans);
                        }
                        else if(answer.equalsIgnoreCase("n"))
                        {
                            System.out.println("Please use this option again when you are ready to grow the account.");
                        }
                        
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // the user must type m to return to the menu
                        valid = false;// valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true 
                        {
                            back = scan.nextLine(); //back = scan user input
                            valid = back.equalsIgnoreCase("m"); // if user types m to return to menu then valid is set to true    
                        }
                        break;
                        
                        // case 11 will display a specified customer's information
                case 11:bank.showAllCustomerNames();
                        System.out.println("You chose [DISPLAY CUSTOMER INFORMATION].\nType the name of the customer you want to display.");
                        String nameOfSingleCustomer = scan.nextLine();
                        bank.showCustomer(nameOfSingleCustomer);
                        System.out.println("");
                        System.out.println("Select 'm' to return to main menu."); // the user must type m to return to the menu
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // back = scan user input
                            valid = back.equalsIgnoreCase("m");  // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        
                        // case 11 will show all accounts and their information
                case 12:bank.showAllAccounts();
                        System.out.println(""); // line for cosmetic purpose
                        System.out.println("Select 'm' to return to main menu."); // the user must type m to return to the menu
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // back = scan user input
                            valid = back.equalsIgnoreCase("m");  // if user types m to return to menu then valid is set to true  
                        }
                        break;
                        
                        // case 12 handles showing all transactions that have taken place
                case 13:transaction.showTransactionRecords();
                        System.out.println(""); // line for cosmetic purpose
                        System.out.println("Select 'm' to return to main menu.");
                        valid = false; // valid is set to false for loop
                        while(!valid) // keep doing this loop until valid is == true
                        {
                            back = scan.nextLine(); // back = scan user input
                            valid = back.equalsIgnoreCase("m");  // if user types m to return to menu then valid is set to true 
                        }
                        break;
                       
                        
                                                       
            }    
        }
        
        scan.close();
        WriteData data = new WriteData();
        data.write(bank.getCustomers(), transaction.getRecords());
        System.out.println("DON'T FORGET TO SMILE AND SAY 'HAVE A NICE DAY'!!!!");
        System.exit(0);
    }
    
}
