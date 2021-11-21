/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testSetInvalidSerialNumber() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the serialNumber to be an invalid input not in the A-XXX-XXX-XXX format.
        actualResult.setSerialNumber("Wow");
        // Use assertEquals to verify the invalid input did not change the serial number of the item.
        assertEquals("A-111-111-111", actualResult.getSerialNumber());
    }

    @Test
    void testSetValidSerialNumber() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the serialNumber to be an invalid format.
        actualResult.setSerialNumber("A-2A2-22A-A22");
        // Use assertEquals to verify the valid input did change the serial number of the item.
        assertEquals("A-2A2-22A-A22", actualResult.getSerialNumber());
    }

    @Test
    void testSetInvalid1ItemName() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the item name to be an invalid length of 1.
        actualResult.setItemName("A");
        // Use assertEquals to verify the invalid input did not change the name of the item.
        assertEquals("Test", actualResult.getItemName());
    }

    @Test
    void testSetInvalid257ItemName() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the item name to be an invalid length of over 256.
        actualResult.setItemName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        // Use assertEquals to verify the invalid input did not change the name of the item.
        assertEquals("Test", actualResult.getItemName());
    }

    @Test
    void testSetValidItemName() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the item name to be a valid length of 6.
        actualResult.setItemName("Tested");
        // Use assertEquals to verify the valid input did change the name of the item.
        assertEquals("Tested", actualResult.getItemName());
    }

    @Test
    void testSetInvalidItemValue() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the item value to be an invalid value under 0.
        actualResult.setItemValue(new BigDecimal(-1));
        // Use assertEquals to verify the invalid input did not change the value of the item.
        assertEquals(BigDecimal.valueOf(1), actualResult.getItemValue());
    }

    @Test
    void testSetValidValue() {
        // Create an item object with all valid components.
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        // Attempt to set the item value to be a valid value above 0.
        actualResult.setItemValue(new BigDecimal(100));
        // Use assertEquals to verify the valid input did change the value of the item.
        assertEquals(BigDecimal.valueOf(100), actualResult.getItemValue());
    }

    @Test
    void testEquals() {
        // Create two of the exact same Item.
        Item expectedResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        Item actualResult = new Item("Test", new BigDecimal(1), "A-111-111-111");
        boolean result = expectedResult.equals(actualResult);
        // call assertEquals on the two objects.
        assertTrue(result);
    }
}