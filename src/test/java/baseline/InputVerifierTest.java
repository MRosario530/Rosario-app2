/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputVerifierTest {

    @Test
    void testVerifyAllWhenValid() {
        // Create an InputVerifier object.
        // Use assertTrue on the method verifyAll with 3 valid inputs.
    }

    @Test
    void testVerifyAllWhenInvalid() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifyAll with 2 valid inputs and 1 invalid input.
    }

    @Test
    void testCheckDuplicateSerialNumWithADuplicate() {
        // Create an InputVerifier object.
        // Create a list of at least 3 items, each with their own serialNumbers.
        // Use assertTrue on the method checkDuplicateSerialNum using a duplicate serial number.
    }

    @Test
    void testCheckDuplicateSerialNumWithoutADuplicate() {
        // Create an InputVerifier object.
        // Create a list of at least 3 items, each with their own serialNumbers.
        // Use assertFalse on the method checkDuplicateSerialNum using a unique serial number.
    }

    @Test
    void testVerifySerialNumberWhenValid() {
        // Create an InputVerifier object.
        // Use assertTrue on the method verifySerialNumber with a valid serial number.
    }

    @Test
    void testVerifySerialNumberWhenInvalid() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifySerialNumber with a serial number in an invalid format.
    }

    @Test
    void testVerifyValueWhenValid() {
        // Create an InputVerifier object.
        // Use assertTrue on the method verifyValue with a valid value above 0.
    }

    @Test
    void testVerifyValueWhenInvalidWord() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifyValue with a word.
    }

    @Test
    void testVerifyValueWhenInvalidNumber() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifyValue with a negative value.
    }

    @Test
    void testVerifyItemNameWhenValid() {
        // Create an InputVerifier object.
        // Use assertTrue on the method verifyItemName with a valid string between 2 and 256 characters.
    }

    @Test
    void testVerifyItemNameWhenInvalidShort() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifyItemName with an invalid string of length 1.
    }

    @Test
    void testVerifyItemNameWhenInvalidLong() {
        // Create an InputVerifier object.
        // Use assertFalse on the method verifyItemName with an invalid string of length 257.
    }
}