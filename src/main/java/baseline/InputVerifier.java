/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.math.BigDecimal;
import java.util.List;

public class InputVerifier {

    public boolean checkDuplicateSerialNum (List<Item> currentInventory, String serialNum){
        // Loop through the currentInventory.
        for (Item item: currentInventory) {
            // If there is a serial number that matches the parameter's serial number, return true.
            if (item.getSerialNumber().equals(serialNum)) {
                return true;
            }
        }
        // Otherwise if no duplicate exists return false.
        return false;
    }

    public boolean verifySerialNum(String serialNum) {
        // Return the comparison between the serialNumber and an appropriate regex for A-XXX-XXX-XXX.
        return serialNum.matches("^[a-zA-Z]+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}$");
    }

    public boolean verifyValue(String value) {
        // Attempt to parse the value to a bigdecimal.
        BigDecimal val;
        try{
            val = new BigDecimal(value);
        } catch (NumberFormatException e){
            // If this fails return false.
            return false;
        }
        // Otherwise return whether the given value is a non-negative input. (False if negative).
        return val.compareTo(BigDecimal.valueOf(0)) >= 0;
    }

    public boolean verifyItemName(String itemName) {
        // Return whether the given string is between 2 and 256 characters.
        return itemName.length() < 2 || itemName.length() > 256;
    }

    public String getInvalidAddString(String itemName, String value, String serialNum,
                                      List<Item> currentInventory){
        StringBuilder invalidInputString = new StringBuilder("");
        if(verifyItemName(itemName)) {
            invalidInputString.append("Item Name\n");
        }
        if(!verifyValue(value)) {
            invalidInputString.append("Value\n");
        }
        if(!verifySerialNum(serialNum) || checkDuplicateSerialNum(currentInventory,serialNum)) {
            invalidInputString.append("Serial Number\n");
        }
        return invalidInputString.toString();
    }

    public String getInvalidEditString(String itemName, String value, String serialNum,
                                      List<Item> currentInventory){
        StringBuilder invalidInputString = new StringBuilder("");
        if (verifyItemName(itemName)) {
            invalidInputString.append("Item Name\n");
        }
        if (!verifyValue(value)) {
            invalidInputString.append("Value\n");
        }
        if (!verifySerialNum(serialNum) || checkDuplicateSerialNum(currentInventory,serialNum)) {
            invalidInputString.append("Serial Number\n");
        }
        return invalidInputString.toString();
    }

}
