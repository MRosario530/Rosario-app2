/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.math.BigDecimal;
import java.util.List;

public class InputVerifier {

    public boolean checkUniqueSerialNum(List<Item> currentInventory, String serialNum){
        // Loop through the currentInventory.
        for (Item item: currentInventory) {
            // If there is a serial number that matches the parameter's serial number, return false.
            if (item.getSerialNumber().equals(serialNum)) {
                return false;
            }
        }
        // Otherwise if no duplicate exists return true.
        return true;
    }

    public boolean verifySerialNum(String serialNum) {
        // Return the comparison between the serialNumber and an appropriate regex for A-XXX-XXX-XXX. False if it
        // doesn't match, true if it does.
        return serialNum.matches("^[a-zA-Z]+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}$");
    }

    public boolean verifyValue(String value) {
        // Attempt to parse the value to a BigDecimal.
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
        // If the item name was invalid, add item name to the string of invalid components
        if (verifyItemName(itemName)) {
            invalidInputString.append("Invalid Item Name\n");
        }
        // If the value was invalid, add value to the string of invalid components.
        if (!verifyValue(value)) {
            invalidInputString.append("Invalid Value\n");
        }
        // If the serial number was invalid, either in input or by duplicate, add serial number to the string of
        // invalid opponents.
        if (!verifySerialNum(serialNum)) {
            invalidInputString.append("Invalid Serial Number\n");
        } else if (!checkUniqueSerialNum(currentInventory,serialNum)) {
            invalidInputString.append("Duplicate Serial Number\n");
        }
        // Return the resulting string.
        return invalidInputString.toString();
    }

    public String getInvalidEditString(String itemName, String value, String serialNum,
                                      List<Item> currentInventory){
        StringBuilder invalidInputString = new StringBuilder("");
        // If the item name was invalid and not blank, add item name to the string of invalid components
        if (verifyItemName(itemName) && itemName.length() != 0) {
            invalidInputString.append("Invalid Item Name\n");
        }
        // If the value was invalid and not blank, add value to the string of invalid components.
        if (!verifyValue(value) && !value.isBlank()) {
            invalidInputString.append("Invalid Value\n");
        }
        // If the serial number was invalid, either in input or by duplicate, and not blank, add serial number
        // to the string of invalid opponents.
        if (!verifySerialNum(serialNum) && !serialNum.isBlank()) {
            invalidInputString.append("Invalid Serial Number\n");
        } else if (!checkUniqueSerialNum(currentInventory,serialNum)) {
            invalidInputString.append("Duplicate Serial Number\n");
        }
        // Return the resulting string.
        return invalidInputString.toString();
    }

}
