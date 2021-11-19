/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void testAddItem() {
        // Create an Inventory object.
        // Call addItem with 3 valid parameters and create an item with those same parameters.
        // Use assertEquals to verify that the item in the list and the item with the same parameters are the same.
    }

    @Test
    void testEditItem() {
        // Create an Inventory object.
        // Call addToInventory with 3 valid parameters and create an item with different parameters.
        // Call editItem with the 3 new parameters to ensure the values update,
        // Use assertEquals to verify that the item in the list and the item with the different parameters are the same.
    }

    @Test
    void testDeleteItem() {
        // Create an Inventory object.
        // Call addItem with 3 valid parameters and create an item with those same parameters.
        // Call addItem again with 3 new valid parameters and create an item with those same parameters.
        // call deleteItem on the first item of the list.
        // Use assertEquals to verify that the one item in the list and the item added second are the same.
    }

    @Test
    void testClearList() {
        // Create an Inventory object.
        // Call addItem 3 times, each time having valid inputs.
        // Call clearList on the inventory.
        // Use assertEquals to verify the size of the inventory is 0.
    }

    @Test
    void testSaveAndUploadAsHTML() {
        // Create an Inventory object.
        // Create and add an example list of items to the object and have a File object to the docs directory.
        // Call the saveAsHTML method using the file.
        // Call uploadHTML on the file location.
        // Use assertEquals to compare the example list and the list you can retrieve from the inventory.
    }

    @Test
    void testSaveAndUploadAsJSON() {
        // Create an Inventory object.
        // Create and add an example list of items to the object and have a File object to the docs directory.
        // Call the saveAsJSON method using the file.
        // Call uploadJSON on the file location.
        // Use assertEquals to compare the example list and the list you can retrieve from the inventory.
    }

    @Test
    void testSaveAndUploadAsTSV() {
        // Create an Inventory object.
        // Create and add an example list of items to the object and have a File object to the docs directory.
        // Call the saveAsTSV method using the file.
        // Call uploadTSV on the file location.
        // Use assertEquals to compare the example list and the list you can retrieve from the inventory.
    }
}