/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.util.List;

public class InputVerifier {

    public boolean verifyAll(String serialNumber, String value, String itemName) {
        // Call the 3 verification methods for the serialNumber, value, and itemName.
        // If all return true, return true.
        // Otherwise return false.
        return false;
    }

    public boolean checkDuplicateSerialNum (List<Item> currentInventory, String serialNum){
        // Loop through the currentInventory.
        // If there is a serial number that matches the parameter's serial number, return true.
        // Otherwise if no duplicate exists return false.
        return false;
    }

    public boolean verifySerialNumber(String serialNumber) {
        // Return the comparison between the serialNumber and an appropriate regex for A-XXX-XXX-XXX.
        return false;
    }

    public boolean verifyValue(String value) {
        // Attempt to parse the value to a bigdecimal.
            // If this fails return false.
        // Otherwise return whether the given value is a non-negative input. (False if negative).
        return false;
    }

    public boolean verifyItemName(String itemName) {
        // Return whether the given string is between 2 and 256 characters.
        return false;
    }

}
