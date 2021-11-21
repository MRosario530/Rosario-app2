/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.math.BigDecimal;
import java.util.List;

public class InputVerifier {
    public String getAddingErrorString(String itemName, String value, String serialNum, List<Item> currentInventory){
        // Create a StringBuilder object to hold all potential errors.
        StringBuilder invalidInputString = new StringBuilder("");
        // If verifyItemName returns false, add "Invalid Item Name" to the string of errors.
        if (!verifyItemName(itemName)) {
            invalidInputString.append("Invalid Item Name\n");
        }
        // If verifyValue returns false, add "Invalid Value" to the string of errors.
        if (!verifyValue(value)) {
            invalidInputString.append("Invalid Value\n");
        }
        // If verifySerialNum returns false, add "Invalid Serial Number" to the string of errors.
        if (!verifySerialNum(serialNum)) {
            invalidInputString.append("Invalid Serial Number\n");
        } else if (!checkUniqueSerialNum(currentInventory,serialNum)) {
            // Otherwise, if checkUniqueSerialNum returns false, add "Duplicate Serial Number" to the string of errors.
            invalidInputString.append("Duplicate Serial Number\n");
        }
        // Return the resulting string.
        return invalidInputString.toString();
    }

    public String getEditingErrorString(String itemName, String value, String serialNum, List<Item> currentInventory){
        // Create a StringBuilder object to hold all potential errors.
        StringBuilder invalidInputString = new StringBuilder("");
        // If verifyItemName returns false and the input was not blank, add "Invalid Item Name" to the string of errors.
        if (!verifyItemName(itemName) && itemName.length() != 0) {
            invalidInputString.append("Invalid Item Name\n");
        }
        // If verifyValue returns false and the input was not blank, add "Invalid Value" to the string of errors.
        if (!verifyValue(value) && !value.isBlank()) {
            invalidInputString.append("Invalid Value\n");
        }
        // If verifySerialNum returns false and the input was not blank, add "Invalid Serial Number" to the string of
        // errors.
        if (!verifySerialNum(serialNum) && !serialNum.isBlank()) {
            invalidInputString.append("Invalid Serial Number\n");
        } else if (!checkUniqueSerialNum(currentInventory,serialNum)) {
            // Otherwise, if checkUniqueSerialNum returns false, add "Duplicate Serial Number" to the string of errors.
            invalidInputString.append("Duplicate Serial Number\n");
        }
        // Return the resulting string.
        return invalidInputString.toString();
    }

    private boolean verifyItemName(String itemName) {
        // Return whether the given string is between 2 and 256 characters.
        return itemName.length() >= 2 && itemName.length() <= 256;
    }

    private boolean verifyValue(String value) {
        // Attempt to parse the value to a BigDecimal.
        BigDecimal val;
        try {
            val = new BigDecimal(value);
        } catch (NumberFormatException e){
            // If this fails return false.
            return false;
        }
        // Otherwise, return whether the given value is a non-negative input. (False if negative).
        return val.compareTo(BigDecimal.valueOf(0)) >= 0;
    }

    private boolean verifySerialNum(String serialNum) {
        // Return the comparison between the serialNumber and an appropriate regex for A-XXX-XXX-XXX. False if it
        // doesn't match, true if it does.
        return serialNum.matches("^[a-zA-Z]+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}+-[A-Za-z0-9]{3}$");
    }

    private boolean checkUniqueSerialNum(List<Item> currentInventory, String serialNum){
        // Loop through the currentInventory.
        for (Item item: currentInventory) {
            // If there is a serial number that matches the parameter's serial number, return false.
            if (item.getSerialNumber().equals(serialNum)) {
                return false;
            }
        }
        // Otherwise, if no duplicate exists return true.
        return true;
    }
}
