/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    private Inventory inventory;

    private List<Item> visibleList;

    private int currentMode;

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label serialNumberLabel;

    @FXML
    private Label monetaryValueLabel;

    @FXML
    private MenuItem addToggleButton;

    @FXML
    private Label characterCountLabel;

    @FXML
    private MenuItem clearAllButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteInstructLabel;

    @FXML
    private MenuItem deleteToggleButton;

    @FXML
    private MenuItem editToggleButton;

    @FXML
    private Label inputTitleLabel;

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
        if (currentMode == 2) {
            // Otherwise if the value is 2, change the inventoryInputLabel to say "Add to Inventory" and the
            // itemInputButton text to "Add Item".
            inputTitleLabel.setText("Add to Inventory");
            itemInputButton.setText("Add Item");
        } else if (currentMode == 3) {
            // Otherwise if the value is 3, change the inventoryInputLabel to say "Add to Inventory" and the
            // itemInputButton text to "Add Item". Toggle the visibility of deleteButton to invisible and set
            // the table's selectionmodel to singular. Toggle all input field's visibility to be visible.
            inputTitleLabel.setText("Add to Inventory");
            itemInputButton.setText("Add Item");
            toggleInputView(true);
            inventoryTableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        // Update currentMode to 1.
        currentMode = 1;
    }

    @FXML
    void onClearAllPressed(ActionEvent event) {
        // Call the inventory's clearList method.
        inventory.clearList();
        // Update the tableview.
        visibleList = inventory.getCurrentInventory();
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    void onDeleteButtonPressed(ActionEvent event) {
        List<Item> itemsToDelete = inventoryTableview.getSelectionModel().getSelectedItems();
        // Call the inventory's deleteItems method on the list of selected items.
        inventory.deleteItems(itemsToDelete);
        // Update the tableview.
        updateList();
    }

    @FXML
    void onDeleteTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 3, do nothing.
        if (currentMode != 3) {
            // Otherwise if the value is anything else, toggle the visibility of all input fields and labels on the
            // left side of the tableview to invisible. Toggle the visibility of deleteButton to visible and set
            // the table's selectionmodel to multiple.
            toggleInputView(false);
            inventoryTableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
        // Update currentMode to 3.
        currentMode = 3;
    }

    @FXML
    void onEditTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 2, do nothing.
        if (currentMode == 1) {
            // Otherwise if the value is 1, change the inventoryInputLabel to say "Edit Current Item" and the
            // itemInputButton text to "Save Edit".
            inputTitleLabel.setText("Edit Current Item");
            itemInputButton.setText("Save Edit");
        } else if (currentMode == 3) {
            // Otherwise if the value is 3, change the inventoryInputLabel to say "Edit Current Item" and the
            // itemInputButton text to "Edit Selected Item". Toggle the visibility of deleteButton to invisible and set
            // the table's selectionmodel to singular. Toggle all input field's visibility to be visible.
            inputTitleLabel.setText("Edit Current Item");
            itemInputButton.setText("Save Edit");
            toggleInputView(true);
            inventoryTableview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        // Update currentMode to 2.
        currentMode = 2;
    }

    @FXML
    void onItemInputPressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 1, call the verifyAll method on the inputs of the three text fields.
            // Also call the checkDuplicateSerialNum method to see if the serial number is unique.
            // If the result is true, call addItem on the three inputs.
            // Otherwise, call the individual verifier methods and display an alert for each thing that returns false.
        // Otherwise, if the value is 2, call each verifier method.
            // Call the individual verifier methods and display an alert for each thing that returns false UNLESS
            // the input was blank. Also call the checkDuplicateSerialNum method to see if the serial number is unique.
            // Call the editItem method afterwards.
        // If the input was valid/accepted, clear the text fields afterwards.
        if (currentMode == 1) {
            onAddItemPressed();
        }
    }

    @FXML
    void onAddItemPressed() {
        InputVerifier verifier = new InputVerifier();
        StringBuilder alertMessage = new StringBuilder("Error! The following parameters are invalid:\n");
        String itemName = nameInputBox.getText();
        String value = valueInputBox.getText();
        String serialNum = serialInputBox.getText();
        String result = verifier.getInvalidAddString(itemName, value, serialNum, inventory.getCurrentInventory());
        if (result.length() == 0) {
            BigDecimal convertedVal = new BigDecimal(value);
            convertedVal = convertedVal.setScale(2, RoundingMode.UP);
            inventory.addItem(itemName,convertedVal,serialNum);
            clearInputBoxes();
            updateList();
        } else {
            alertMessage.append(result);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void clearInputBoxes() {
        nameInputBox.clear();
        valueInputBox.clear();
        serialInputBox.clear();
    }

    @FXML
    void updateList() {
        visibleList = inventory.getCurrentInventory();
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    void toggleInputView(boolean mode) {
        inputTitleLabel.setVisible(mode);
        itemNameLabel.setVisible(mode);
        nameInputBox.setVisible(mode);
        characterCountLabel.setVisible(mode);
        serialNumberLabel.setVisible(mode);
        serialInputBox.setVisible(mode);
        monetaryValueLabel.setVisible(mode);
        valueInputBox.setVisible(mode);
        itemInputButton.setVisible(mode);
        deleteInstructLabel.setVisible(!mode);
        deleteButton.setVisible(!mode);
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
        inventory = new Inventory();
        visibleList = new ArrayList<>();
        // Initialize the currentMode to the value of 1.
        currentMode = 1;
        // Initialize the 3 columns of the tableview for itemName, value, and serialNumber.
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        serialNumColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
        // Specifically enable resizing of the itemName column due to its length.
        nameColumn.setCellFactory(tc -> {
            TableCell<Item, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Region.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(nameColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
        // Create a character counter for the description box by binding the label to the text within the
            // nameInput box.
        characterCountLabel.textProperty().bind(nameInputBox.textProperty()
                .length()
                .asString("Character Count: %d"));
        // Set the items for the tableview.
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
    }

}
