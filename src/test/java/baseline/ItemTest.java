/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testSetInvalidSerialNumber() {
        // Create an item object with all valid components.
        // Attempt to set the serialNumber to be an invalid format.
        // Call assertFalse on a regex comparison between the item's serialNumber and the regex for A-XXX-XXX-XXX.
    }

    @Test
    void testSetValidSerialNumber() {
        // Create an item object with all valid components.
        // Attempt to set the serialNumber to be another valid format.
        // Call assertEquals on a string comparison between the item's current serialNumber and the number it should
            // have changed to.
    }

    @Test
    void testToString() {
        // Create an item object with all valid components.
        // Call assertEquals to ensure that the toString prints in the format serialNumber\titemName\tvalue.
    }

    @Test
    void testEquals() {
        // Create two of the exact same Item.
        // call assertEquals on the two objects.
    }
}