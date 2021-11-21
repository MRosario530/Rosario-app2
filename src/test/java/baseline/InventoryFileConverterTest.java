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

class InventoryFileConverterTest {
    // Note - Both the save and upload tests are done in the same unit test due to the fact that you cannot test one
    // without testing the other. For all the text I save to a file, I must reopen it to verify.

    @Test
    void testImportAndExportHTML() {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Create an example list of items and a File object with an html extension to the docs directory.
        List<Item> expectedResult = new ArrayList<>();
        expectedResult.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        expectedResult.add(new Item("Test2", new BigDecimal(2), "A-222-222-222"));
        File file = new File("docs/testfileconverter.html");
        // Call the exportFile method using the file and the list.
        convert.exportFile(file, expectedResult);
        // Call the importFile method using the file location and save the result to a new item list.
        List<Item> actualResult = convert.importFile(file);
        // Use assertEquals to compare the example list and the result list.
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testImportAndExportJSON() {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Create an example list of items and a File object with a json extension to the docs directory.
        List<Item> expectedResult = new ArrayList<>();
        expectedResult.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        expectedResult.add(new Item("Test2", new BigDecimal(2), "A-222-222-222"));
        File file = new File("docs/testfileconverter.json");
        // Call the exportFile method using the file and the list.
        convert.exportFile(file, expectedResult);
        // Call the importFile method using the file location and save the result to a new item list.
        List<Item> actualResult = convert.importFile(file);
        // Use assertEquals to compare the example list and the result list.
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testImportAndExportTSV() {
        // Create an InventoryFileConverter object.
        InventoryFileConverter convert = new InventoryFileConverter();
        // Create an example list of items and a File object with a txt extension to the docs directory.
        List<Item> expectedResult = new ArrayList<>();
        expectedResult.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        expectedResult.add(new Item("Test2", new BigDecimal(2), "A-222-222-222"));
        File file = new File("docs/testfileconverter.txt");
        // Call the exportFile method using the file and the list.
        convert.exportFile(file, expectedResult);
        // Call the importFile method using the file location and save the result to a new item list.
        List<Item> actualResult = convert.importFile(file);
        // Use assertEquals to compare the example list and the result list.
        assertEquals(expectedResult, actualResult);
    }
}