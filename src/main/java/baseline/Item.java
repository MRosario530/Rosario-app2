/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import javafx.beans.property.StringProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private StringProperty itemName;
    private BigDecimal value;
    private StringProperty serialNumber;

    public Item() {
        // Constructor which creates a blank item object with no values.
    }

    public Item(String itemName, BigDecimal value, String serialNumber) {
        // Constructor which creates an item and assigns each parameter to the corresponding variable.
    }

    public String getItemName() {
        // Return the item's name.
        return itemName.get();
    }

    public void setItemName(String itemName) {
        // Assuming the string given is between 2 and 256 characters (inclusive), set the parameter itemName as the new
        // itemName instance variable.
    }

    public BigDecimal getValue() {
        // Return the item's monetary value.
        return value;
    }

    public void setValue(BigDecimal value) {
        // Assuming the value given is a non-negative number, set the parameter value as the new
        // value instance variable.
    }

    public String getSerialNumber() {
        // Return the item's serialNumber.
        return serialNumber.get();
    }

    public void setSerialNumber(String serialNumber) {
        // Assuming the serial number given is in the valid format "A-XXX-XXX-XXX", set the parameter serialNumber
        // as the new serialNumber instance variable.
    }

    public String toString() {
        // Return a string formatted as: serialNumber\titemName\tvalue.
        return null;
    }

    @Override
    public boolean equals(Object o) {
        // First check if the two objects are the same. If so, return true.
        // Then check to see if the object o is an object of class item. If not, return false.
        // Otherwise return whether or not all components (itemName, value, and serialNumber) are equal to each other.
        return false;
    }

    @Override
    public int hashCode() {
        // Returns the hash value of the item (?).
        return Objects.hash(itemName, value, serialNumber);
    }

}
