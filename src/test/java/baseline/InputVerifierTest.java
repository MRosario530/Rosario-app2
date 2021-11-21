/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InputVerifierTest {
    // Note - InputVerifier utilizes many of the same private methods in its 2 public methods, which verify input for
    // adding items and editing items. Since all the private methods are tested already though the several
    // getAddingErrorString tests, I chose not to repeat the same tests for the getEditingErrorString tests.
    @Test
    void testGetAddingErrorStringAllValid() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using all valid inputs and verify that the resulting string is blank.
        String actualResult = verifier.getAddingErrorString("Test2","1", "A-123-456-789",
                currentInventory);
        assertEquals("", actualResult);
    }

    @Test
    void testGetAddingErrorStringAllInvalid() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using all invalid inputs and verify that the resulting string has all 3
        // errors present.
        String actualResult = verifier.getAddingErrorString("a","a", "a",
                currentInventory);
        assertEquals("Invalid Item Name\nInvalid Value\nInvalid Serial Number\n", actualResult);
    }

    @Test
    void testGetAddingErrorStringInvalidNameOnly() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using an invalid name input and verify that the resulting string is has
        // one error for "Invalid Item Name" present.
        String actualResult = verifier.getAddingErrorString("a","1", "A-123-456-789",
                currentInventory);
        assertEquals("Invalid Item Name\n", actualResult);
    }

    @Test
    void testGetAddingErrorStringAllInvalidValueWordOnly() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using a word for the value and verify that the resulting string has one error
        // for "Invalid Value" present.
        String actualResult = verifier.getAddingErrorString("Test2","a", "A-123-456-789",
                currentInventory);
        assertEquals("Invalid Value\n", actualResult);
    }

    @Test
    void testGetAddingErrorStringAllInvalidValueNumOnly() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using a negative for the value and verify that the resulting string has one
        // error for "Invalid Value" present.
        String actualResult = verifier.getAddingErrorString("Test2","-1", "A-123-456-789",
                currentInventory);
        assertEquals("Invalid Value\n", actualResult);
    }

    @Test
    void testGetAddingErrorStringAllInvalidSerialNumber() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using an invalid serial number and verify that the resulting string has one
        // error for "Invalid Serial Number" present.
        String actualResult = verifier.getAddingErrorString("Test2","1", "SerialNum",
                currentInventory);
        assertEquals("Invalid Serial Number\n", actualResult);
    }

    @Test
    void testGetAddingErrorStringAllDuplicateSerialNumber() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getAddingErrorString using a duplicate serial number and verify that the resulting string has one
        // error for "Duplicate Serial Number" present.
        String actualResult = verifier.getAddingErrorString("Test2","1", "A-111-111-111",
                currentInventory);
        assertEquals("Duplicate Serial Number\n", actualResult);
    }

    @Test
    void getEditingErrorStringAllValid() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getEditingErrorString using all valid inputs and verify that the resulting string is blank.
        String actualResult = verifier.getEditingErrorString("Test2","1", "A-123-456-789",
                currentInventory);
        assertEquals("", actualResult);
    }

    @Test
    void getEditingErrorStringBlankName() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getEditingErrorString using a blank name and verify that the resulting string is still blank.
        String actualResult = verifier.getEditingErrorString("","1", "A-123-456-789",
                currentInventory);
        assertEquals("", actualResult);
    }

    @Test
    void getEditingErrorStringBlankValue() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getEditingErrorString using a blank value and verify that the resulting string is still blank.
        String actualResult = verifier.getEditingErrorString("Test2","", "A-123-456-789",
                currentInventory);
        assertEquals("", actualResult);
    }

    @Test
    void getEditingErrorStringBlankSerialNumber() {
        // Create a new verifier object.
        InputVerifier verifier = new InputVerifier();
        // Create a list of items with 1 item within it already.
        List<Item> currentInventory = new ArrayList<>();
        currentInventory.add(new Item("Test", new BigDecimal(1), "A-111-111-111"));
        // Call the getEditingErrorString using a blank serial Number and verify that the resulting string is still
        // blank.
        String actualResult = verifier.getEditingErrorString("Test2","1", "",
                currentInventory);
        assertEquals("", actualResult);
    }
}