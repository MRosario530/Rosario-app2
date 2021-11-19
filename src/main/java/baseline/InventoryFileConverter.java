/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import java.io.File;
import java.util.List;

public class InventoryFileConverter {
    public void saveAsHTML(File file, List<Item> currentInventory) {
        // Create a string and start it with the html and table tags.
        // Create 3 headers surrounded by the <th> tags.
        // Create a for loop and add every component of every item, one line at a time with the appropriate <td> tags.
        // Write the string to the file and save the result.
    }

    public void saveAsJSON(File file, List<Item> currentInventory) {
        // Create a GSON object.
        // Use the toJson method on the currentInventory list on the file destination.
    }

    public void saveAsTSV(File file, List<Item> currentInventory) {
        // Create a stringbuilder.
        // Have the first line include "Serial Number\tName\tValue\n"
        // Loop through the list, adding the toString of each item to the stringbuilder
        // Open the file and paste the stringbuilder in the file using a try/catch.
    }

    public List<Item> uploadHTML(File file) {
        // Create a temporary list of items.
        // Set up a try/catch to loop through the document.
        // Loop through the first few lines with no concern (doctype, html, etc)
        // Proceed to loop through the 3 lines between each tablerow <tr> and create an object with the three
        // components.
        // If an error occurs print the stacktrace.
        // Return the temporary list.
        return null;
    }

    public List<Item> uploadJSON(File file) {
        // Create a new inventory.
        // Within a try/catch create a json reader at the file location.
        // Create a Gson object and call fromJson with the wrapper class Inventory.
        // If an error occurs print the stacktrace.
        // Return the list of items from the inventory.
        return null;
    }

    public List<Item> uploadTSV(File file) {
        // Create a temporary list.
        // Create a filereader within a try/catch.
        // Pass the first line.
        // Then loop until the end of file with the line being passed into the tsvParser method.
            // Every item will get added to the temp list.
        // Return the temporary list.
        return null;
    }

    private Item tsvParser(String itemText) {
        // From the string given split the string into an array of strings based on the \t character.
        // Create and return an item from the 3 strings within the array.
        return null;
    }
}
