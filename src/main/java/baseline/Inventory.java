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
        List<Item> searchList = new ArrayList<>();
        for (Item item : currentInventory) {
            if (item.getItemName().contains(searchString) || item.getSerialNumber().contains(searchString)) {
                searchList.add(item);
            }
        }
        return searchList;
    }

    public void exportHTML(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's exportHTML method using the file and currentInventory list.
        convert.exportHTML(file,currentInventory);
    }

    public void exportJSON(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's exportJSON method using the file and currentInventory list.
        convert.exportJSON(file,currentInventory);
    }

    public void exportTSV(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's exportTSV method using the file and currentInventory list.
        convert.exportTSV(file, currentInventory);
    }

    public void importHTML(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's importHTML method using the file and set the result to the currentInventory list.
        currentInventory = convert.importHTML(file);
    }

    public void importJSON(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's importJSONToApp method using the file and set the result to the currentInventory list.
        currentInventory = convert.importJSON(file);
    }

    public void importTSV(File file) {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Call the object's importTSV method using the file and set the result to the currentInventory list.
        currentInventory = convert.importTSV(file);
    }
}
