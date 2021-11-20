/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private SimpleStringProperty itemName;
    private BigDecimal itemValue;
    private SimpleStringProperty serialNumber;

    public Item() {
        // Constructor which creates a blank item object with no values.
    }

    public Item(String itemName, BigDecimal itemValue, String serialNumber) {
        // Constructor which creates an item and assigns each parameter to the corresponding variable.
        this.itemName = new SimpleStringProperty(itemName);
        this.itemValue = itemValue;
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    public String getItemName() {
        // Return the item's name.
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        // Return the itemName property directly.
        return itemName;
    }

    public void setItemName(String itemName) {
        // Assuming the string given is between 2 and 256 characters (inclusive), set the parameter itemName as the new
        // itemName instance variable.
        this.itemName = new SimpleStringProperty(itemName);
    }

    public BigDecimal getItemValue() {
        // Return the item's monetary value.
        return itemValue;
    }

    public void setItemValue(BigDecimal itemValue) {
        // Assuming the value given is a non-negative number, set the parameter value as the new
        // value instance variable.
        this.itemValue = itemValue;
    }

    public String getSerialNumber() {
        // Return the item's serialNumber.
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        // Return the serialNumber property directly.
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        // Assuming the serial number given is in the valid format "A-XXX-XXX-XXX", set the parameter serialNumber
        // as the new serialNumber instance variable.
        this.serialNumber = new SimpleStringProperty(serialNumber);
    }

    public String toString() {
        // Return a string formatted as: serialNumber\titemName\tvalue.
        return serialNumber + "\t" +itemName + "\t" + itemValue;
    }

    @Override
    public boolean equals(Object o) {
        // First check if the two objects are the same. If so, return true.
        if (this == o) return true;
        // Then check to see if the object o is an object of class item. If not, return false.
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        // Otherwise return whether all components (itemName, value, and serialNumber) are equal to each other.
        return itemName.equals(item.itemName) && itemValue.equals(item.itemValue) &&
                serialNumber.equals(item.serialNumber);
    }

    @Override
    public int hashCode() {
        // Returns the hash value of the item.
        return Objects.hash(itemName, itemValue, serialNumber);
    }

}
