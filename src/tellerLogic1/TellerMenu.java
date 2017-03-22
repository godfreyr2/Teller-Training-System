/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tellerLogic1;

import java.util.HashMap;

/**
 * This is class TellerMenu and creates the
 * the teller menu for the user to interact with.
 * @author Godfreyr2
 * @version 1.0
 */


public class TellerMenu {
    private HashMap<Integer, String> tellerMenu; // HashMap that holds the Teller menu information
    private Integer menuNumber = 1; // start the hashMap at key value of 1
    
    /**
     * Constructor for TellerMenu
     */
    public TellerMenu()
    {
        tellerMenu = new HashMap<Integer, String>(); // initialize the HashMap in the constructor
    }
    
    /**
     * AddMenuItem adds each menu item
     * @param newItem // the actual item in each menu
     */
    public void addMenuItem(String newItem)
    {
        tellerMenu.put(menuNumber, newItem); // put a number key starting at 1 and the value is the item
        menuNumber++; // increment the menu number which is the key value
    }
    
    /**
     * ValidMenuChoice checks whether a specific item is valid
     * and/or if it exists or not.
     * @param itemNum the actual menu item itself
     * @return the tellerMenu's boolean value
     */
    public boolean validMenuChoice(Integer itemNum)
    {
        return tellerMenu.containsKey(itemNum); // return a boolean value if the item exists or not
    }
    
    /**
     * DisplayMenu displays all items in the menu along
     * with its key value.  
     */
    public void displayMenu()
    {
        if (tellerMenu.size() > 0) // if tellerMenu is not empty
        {
            for (Integer num : tellerMenu.keySet()) // iterate through the Map's keyset
            System.out.println(num + " : " + tellerMenu.get(num)); // print the key and value for every item
            
        }
        else
        {
            System.out.println("There are no menu objects to display"); // if empty then print error message
        }
    }
}
