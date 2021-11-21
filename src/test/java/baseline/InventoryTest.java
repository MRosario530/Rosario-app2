/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    // Note - Both the save and upload tests are done in the same unit test due to the fact that you cannot test one
    // without testing the other. For all the text I save to a file, I must reopen it to verify.

    @Test
    void testAddItem() {
        // Create an Inventory object.
        Inventory inventory = new Inventory();
        // Call addItem with 3 valid parameters and create an item with those same parameters.
        inventory.addItem("Test", new BigDecimal(1), "A-111-111-111");
        Item expectedResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Use assertEquals to verify that the item in the list and the item with the same parameters are the same.
        assertEquals(expectedResult, inventory.getCurrentInventory().get(0));
    }

    @Test
    void testEditItem() {
        // Create an Inventory object.
        Inventory inventory = new Inventory();
        // Call addToInventory with 3 valid parameters and create an item with different parameters.
        inventory.addItem("Test", new BigDecimal(1), "A-111-111-111");
        Item expectedResult = new Item("Test2", new BigDecimal(2), "A-222-222-222");
        // Call editItem with the item to edit and the 3 new parameters to ensure the values update.
        inventory.editItem(new Item("Test", new BigDecimal(1), "A-111-111-111"),
                "Test2", new BigDecimal(2), "A-222-222-222");
        // Use assertEquals to verify that the item in the list and the item with the different parameters are the same.
        assertEquals(expectedResult, inventory.getCurrentInventory().get(0));
    }

    @Test
    void testDeleteItems() {
        // Create an Inventory object.
        Inventory inventory = new Inventory();
        // Add 4 valid items to the inventory.
        inventory.addItem("Test", new BigDecimal(1), "A-111-111-111");
        inventory.addItem("Test2", new BigDecimal(0), "A-222-222-222");
        inventory.addItem("Test3", new BigDecimal(1), "A-333-333-333");
        inventory.addItem("Test4", new BigDecimal(0), "A-444-444-444");
        // Create a list consisting of 2 of the 4 items to delete.
        List<Item> itemsToDel = new ArrayList<>();
        itemsToDel.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        itemsToDel.add(new Item("Test4", new BigDecimal(0), "A-444-444-444"));
        // Delete the items from the inventory.
        inventory.deleteItems(itemsToDel);
        // Create another list of the items expected to still be present within the inventory.
        List<Item> expectedResult = new ArrayList<>();
        expectedResult.add(new Item("Test2", new BigDecimal(0), "A-222-222-222"));
        expectedResult.add(new Item("Test3", new BigDecimal(1), "A-333-333-333"));
        // Use assertEquals to verify that the item in the list and the item with the same parameters are the same.
        assertEquals(expectedResult, inventory.getCurrentInventory());
    }

    @Test
    void testClearList() {
        // Create an Inventory object.
        Inventory inventory = new Inventory();
        // Add 4 valid items to the inventory.
        inventory.addItem("Test", new BigDecimal(1), "A-111-111-111");
        inventory.addItem("Test2", new BigDecimal(0), "A-222-222-222");
        inventory.addItem("Test3", new BigDecimal(1), "A-333-333-333");
        inventory.addItem("Test4", new BigDecimal(0), "A-444-444-444");
        // Clear the inventory.
        inventory.clearList();
        // Use assertEquals to verify that there are no items left in the list.
        assertEquals(0, inventory.getCurrentInventory().size());
    }

    @Test
    void testSearchList() {
        // Create an Inventory object.
        Inventory inventory = new Inventory();
        // Add 4 valid items to the inventory.
        inventory.addItem("wow", new BigDecimal(1), "A-111-111-111");
        inventory.addItem("Test2", new BigDecimal(0), "A-222-222-222");
        inventory.addItem("Test3", new BigDecimal(1), "A-333-333-wow");
        inventory.addItem("Test4", new BigDecimal(0), "A-444-444-444");
        // Call searchList on the inventory using the substring "wow" and set the result to a new List of items.
        List<Item> actualResult = inventory.searchList("wow");
        // Create another list of the items representing the expected result
        List<Item> expectedResult = new ArrayList<>();
        expectedResult.add(new Item("wow", new BigDecimal(1), "A-111-111-111"));
        expectedResult.add(new Item("Test3", new BigDecimal(1), "A-333-333-wow"));
        // Use assertEquals to verify that the item in the list and the item with the same parameters are the same.
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testImportAndExportHTML() {
        // Create an Inventory object and add a few items to it.
        Inventory expectedResult = new Inventory();
        expectedResult.addItem("Test", new BigDecimal(1), "A-111-111-111");
        expectedResult.addItem("Test2", new BigDecimal(2), "A-222-222-222");
        // Create a File object with an html extension to the docs directory.
        File file = new File("docs/testinventoryfile.html");
        // Call the exportFile method using the file and the list.
        expectedResult.exportFile(file);
        // Create a new Inventory and call the importFile method using the file location.
        Inventory actualResult = new Inventory();
        actualResult.importFile(file);
        // Use assertEquals to compare the list of items within each inventory.
        assertEquals(expectedResult.getCurrentInventory(), actualResult.getCurrentInventory());
    }

    @Test
    void testImportAndExportJSON() {
        // Create an Inventory object and add a few items to it.
        Inventory expectedResult = new Inventory();
        expectedResult.addItem("Test", new BigDecimal(1), "A-111-111-111");
        expectedResult.addItem("Test2", new BigDecimal(2), "A-222-222-222");
        // Create a File object with a json extension to the docs directory.
        File file = new File("docs/testinventoryfile.json");
        // Call the exportFile method using the file and the list.
        expectedResult.exportFile(file);
        // Create a new Inventory and call the importFile method using the file location.
        Inventory actualResult = new Inventory();
        actualResult.importFile(file);
        // Use assertEquals to compare the list of items within each inventory.
        assertEquals(expectedResult.getCurrentInventory(), actualResult.getCurrentInventory());
    }

    @Test
    void testImportAndExportTSV() {
        // Create an Inventory object and add a few items to it.
        Inventory expectedResult = new Inventory();
        expectedResult.addItem("Test", new BigDecimal(1), "A-111-111-111");
        expectedResult.addItem("Test2", new BigDecimal(2), "A-222-222-222");
        // Create a File object with a txt extension to the docs directory.
        File file = new File("docs/testinventoryfile.txt");
        // Call the exportFile method using the file and the list.
        expectedResult.exportFile(file);
        // Create a new Inventory and call the importFile method using the file location.
        Inventory actualResult = new Inventory();
        actualResult.importFile(file);
        // Use assertEquals to compare the list of items within each inventory.
        assertEquals(expectedResult.getCurrentInventory(), actualResult.getCurrentInventory());
    }
}