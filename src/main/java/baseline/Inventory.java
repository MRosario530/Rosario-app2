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
    private List<Item> currentInventory;

    public Inventory() {
        // Create an Inventory object and initialize the list of items.
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
        // Call the setters for the 3 variables. If an input is blank, the setter will not change anything. Otherwise,
        // the variable is updated.
        currentInventory.get(itemIndex).setItemName(itemName);
        currentInventory.get(itemIndex).setItemValue(value);
        currentInventory.get(itemIndex).setSerialNumber(serialNumber);
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

    public List<Item> searchList(String searchString) {
        // Create a new list of items.
        List<Item> searchList = new ArrayList<>();
        // For every item in the currentInventory, if the item name or serial number contains the search string add
        // that item to the search list.
        for (Item item : currentInventory) {
            if (item.getItemName().contains(searchString) || item.getSerialNumber().contains(searchString)) {
                searchList.add(item);
            }
        }
        // Return the search list.
        return searchList;
    }

    public void exportFile(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's exportItem method using the file and currentInventory list.
        convert.exportFile(file, currentInventory);
    }

    public void importFile(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's importItem method using the file.
        currentInventory = convert.importFile(file);
    }
}
