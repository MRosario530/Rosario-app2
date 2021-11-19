/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController {

    private Inventory inventory;

    private int currentMode;

    @FXML
    private MenuItem addToggleButton;

    @FXML
    private Label characterCountLabel;

    @FXML
    private MenuItem clearAllButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteLabel;

    @FXML
    private MenuItem deleteToggleButton;

    @FXML
    private MenuItem editToggleButton;

    @FXML
    private Label inventoryInputLabel;

    @FXML
    private TableView<Item> inventoryTableview;

    @FXML
    private Button itemInputButton;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TextArea nameInputBox;

    @FXML
    private MenuItem saveHTMLButton;

    @FXML
    private MenuItem saveJSONButton;

    @FXML
    private MenuItem saveTSVButton;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField serialInputBox;

    @FXML
    private TableColumn<Item, String> serialNumColumn;

    @FXML
    private MenuItem uploadHTMLButton;

    @FXML
    private MenuItem uploadJSONButton;

    @FXML
    private MenuItem uploadTSVButton;

    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    @FXML
    private TextField valueInputBox;

    @FXML
    void onAddTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 1, do nothing.
        // Otherwise if the value is 2, change the inventoryInputLabel to say "Add to Inventory" and the
            // itemInputButton text to "Add Item".
        // Otherwise if the value is 3, change the inventoryInputLabel to say "Add to Inventory" and the
            // itemInputButton text to "Add Item". Toggle the visibility of deleteButton to invisible and set
            // the table's selectionmodel to singular. Toggle all input field's visibility to be visible.
        // Update currentMode to 1.
    }

    @FXML
    void onClearAllPressed(ActionEvent event) {
        // Call the inventory's clearList method.
        // Update the tableview.
    }

    @FXML
    void onDeleteButtonPressed(ActionEvent event) {
        // Create a for each loop.
        // Call the inventory's deleteItem method on each item that was selected from the tableview.
        // Update the tableview.
    }

    @FXML
    void onDeleteTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 3, do nothing.
        // Otherwise if the value is anything else, toggle the visibility of all input fields and labels on the
            // left side of the tableview to invisible. Toggle the visibility of deleteButton to visible and set
            // the table's selectionmodel to multiple.
        // Update currentMode to 3.
    }

    @FXML
    void onEditTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 2, do nothing.
        // Otherwise if the value is 1, change the inventoryInputLabel to say "Edit to Inventory" and the
            // itemInputButton text to "Edit Selected Item".
        // Otherwise if the value is 3, change the inventoryInputLabel to say "Edit to Inventory" and the
            // itemInputButton text to "Edit Selected Item". Toggle the visibility of deleteButton to invisible and set
            // the table's selectionmodel to singular. Toggle all input field's visibility to be visible.
        // Update currentMode to 2.
    }

    @FXML
    void onItemInputPressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // Create an InputVerifier.
        // If the value is 1, call the verifyAll method on the inputs of the three text fields.
            // Also call the checkDuplicateSerialNum method to see if the serial number is unique.
            // If the result is true, call addItem on the three inputs.
            // Otherwise, call the individual verifier methods and display an alert for each thing that returns false.
        // Otherwise, if the value is 2, call each verifier method.
            // Call the individual verifier methods and display an alert for each thing that returns false UNLESS
            // the input was blank. Also call the checkDuplicateSerialNum method to see if the serial number is unique.
            // Call the editItem method afterwards.
        // If the input was valid/accepted, clear the text fields afterwards.
    }

    @FXML
    void onSaveHTMLPressed(ActionEvent event) {
        // Open up a filechooser to get the user's location and filename and ensure that the extension must be .html.
        // Call the inventory's saveAsHTML method.
    }

    @FXML
    void onSaveJSONPressed(ActionEvent event) {
        // Open up a filechooser to get the user's location and filename and ensure that the extension must be .txt.
        // Call the inventory's saveAsJSON method.
    }

    @FXML
    void onSaveTSVPressed(ActionEvent event) {
        // Open up a filechooser to get the user's location and filename and ensure that the extension must be .txt.
        // Call the inventory's saveAsTSV method.
    }

    @FXML
    void onUploadHTMLPressed(ActionEvent event) {
        // Open up a filechooser to get the file's location and ensure that the extension must be .html.
        // Call the inventory's uploadHTML method and update the tableview.
    }

    @FXML
    void onUploadJSONPressed(ActionEvent event) {
        // Open up a filechooser to get the file's location and ensure that the extension must be .txt.
        // Call the inventory's uploadJSON method and update the tableview.

    }

    @FXML
    void onUploadTSVPressed(ActionEvent event) {
        // Open up a filechooser to get the file's location and ensure that the extension must be .txt.
        // Call the inventory's uploadTSV method and update the tableview.
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the inventory.
        // Initialize the currentMode to the value of 1.
        // Initialize the 3 columns of the tableview for itemName, value, and serialNumber.
            // Specifically enable resizing of the itemName column due to its length.
        // Create a character counter for the description box by binding the label to the text within the
            // nameInput box.
        // Create a filteredlist and sortedlist with the tableview and bind both the searchbox to the list and the
            // comparator of the list to the tableview.
        // Set the items for the tableview.
    }

}
