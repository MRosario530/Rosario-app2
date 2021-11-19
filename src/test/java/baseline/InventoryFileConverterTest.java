/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFileConverterTest {
    // Note - Both the save and upload tests are done in the same unit test due to the fact that you cannot test one
    // without testing the other. For all the text I save to a file, I must reopen it to verify.

    @Test
    void testSaveAndUploadAsHTML() {
        // Create an InventoryFileConverter object.
        // Create an example list of items and a File object to the docs directory.
        // Call the saveAsHTML method using the file and the list.
        // Call uploadHTML on the file location and save the result to a new item list.
        // Use assertEquals to compare the example list and the result list.
    }

    @Test
    void testSaveAndUploadAsJSON() {
        // Create an InventoryFileConverter object.
        // Create an example list of items and a File object to the docs directory.
        // Call the saveAsJSON method using the file and the list.
        // Call uploadJSON on the file location and save the result to a new item list.
        // Use assertEquals to compare the example list and the result list.
    }

    @Test
    void testSaveAndUploadAsTSV() {
        // Create an InventoryFileConverter object.
        // Create an example list of items and a File object to the docs directory.
        // Call the saveAsTSV method using the file and the list.
        // Call uploadTSV on the file location and save the result to a new item list.
        // Use assertEquals to compare the example list and the result list.
    }
}