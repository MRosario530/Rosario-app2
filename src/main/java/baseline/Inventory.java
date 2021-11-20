/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> currentInventory;

    public Inventory() {
        // Create an Inventory object.
        currentInventory = new ArrayList<>();
    }

    public List<Item> getCurrentInventory() {
        // Return the currentInventory list.
        return currentInventory;
    }

    public void addItem(String itemName, BigDecimal value, String serialNumber) {
        // Create an Item object using the three parameters above.
        Item newItem = new Item(itemName,value,serialNumber);
        // Add the item to the currentInventory.
        currentInventory.add(newItem);
    }

    public void editItem(Item itemToEdit, String itemName, BigDecimal value, String serialNumber) {
        // Get the item given from the list.
        int itemIndex = currentInventory.indexOf(itemToEdit);
        // If an input is blank, do not change the corresponding instance variable.

        // If an input is invalid (itemName length not between 2 and 256, value < 0 or serialNumber in incorrect format)
        // do not update anything.
        // Otherwise update the non blank valid parameters in the given itemToEdit.
    }

    public void deleteItems(List<Item> selectedItems) {
        // Delete the items in the parameter from the currentInventory list.
        for (Item item: selectedItems) {
            currentInventory.remove(item);
        }
    }

    public void clearList() {
        // Call the list's clear method.
        if (!currentInventory.isEmpty()) {
            currentInventory.clear();
        }
    }

    public void saveAsHTML(File file) {
        // Create an InventoryFileConverter object.
        // Call the object's saveAsHTML method using the file and currentInventory list.
    }

    public void saveAsJSON(File file) {
        // Create an InventoryFileConverter object.
        // Call the object's saveAsJSON method using the file and currentInventory list.
    }

    public void saveAsTSV(File file, List<Item> currentInventory) {
        // Create an InventoryFileConverter object.
        // Call the object's saveAsTSV method using the file and currentInventory list.
    }

    public void uploadHTML(File file) {
        // Create an InventoryFileConverter object.
        // Call the object's uploadHTML method using the file and set the result to a new list of items.
        // If the length of the list isn't 0, overwrite the currentInventory with the new list.
    }

    public void uploadJSON(File file) {
        // Create an InventoryFileConverter object.
        // Call the object's uploadJSON method using the file and set the result to a new list of items.
        // If the length of the list isn't 0, overwrite the currentInventory with the new list.
    }

    public void uploadTSV(File file) {
        // Create an InventoryFileConverter object.
        // Call the object's uploadTSV method using the file and set the result to a new list of items.
        // If the length of the list isn't 0, overwrite the currentInventory with the new list.
    }
}
