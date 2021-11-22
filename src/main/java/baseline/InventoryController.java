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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private Label characterCountLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteInstructLabel;

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
    private TextField searchBox;

    @FXML
    private TextField serialInputBox;

    @FXML
    private TableColumn<Item, String> serialNumColumn;

    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    @FXML
    private TextField valueInputBox;

    @FXML
    private void onAddTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 1, do nothing.
        if (currentMode == 2) {
            // Otherwise, if the value is 2, change the inventoryInputLabel to say "Add to Inventory" and the
            // itemInputButton text to "Add Item".
            inputTitleLabel.setText("Add to Inventory");
            itemInputButton.setText("Add Item");
        } else if (currentMode == 3) {
            // Otherwise, if the value is 3, change the inventoryInputLabel to say "Add to Inventory" and the
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
    private void onEditTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 2, do nothing.
        if (currentMode == 1) {
            // Otherwise, if the value is 1, change the inventoryInputLabel to say "Edit Current Item" and the
            // itemInputButton text to "Save Edit".
            inputTitleLabel.setText("Edit Current Item");
            itemInputButton.setText("Save Edit");
        } else if (currentMode == 3) {
            // Otherwise, if the value is 3, change the inventoryInputLabel to say "Edit Current Item" and the
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
    private void onDeleteTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 3, do nothing.
        if (currentMode != 3) {
            // Otherwise, call toggleInputView to make all input fields and labels invisible.
            toggleInputView(false);
            // Set the tableview selection model to allow multiple selections.
            inventoryTableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
        // Update currentMode to 3.
        currentMode = 3;
    }

    @FXML
    private void onClearAllPressed(ActionEvent event) {
        // Create an alert to confirm if the user wants to clear all items.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you would like to clear the entire list?");
        Optional<ButtonType> result = alert.showAndWait();
        // If they press okay, clear the inventory.
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Call the inventory's clearList method.
            inventory.clearList();
            // Update the tableview.
            updateTable();
        }
    }

    @FXML
    private void onItemInputPressed(ActionEvent event) {
        // If the value is 1, call the onAddItemPressed method.
        if (currentMode == 1) {
            onAddItemPressed();
        } else {
            // Otherwise, call the on editItemPressed method.
            onEditItemPressed();
        }
    }

    @FXML
    private void onAddItemPressed() {
        // Create a new InputVerifier object and a new StringBuilder for a potential error message.
        InputVerifier verifier = new InputVerifier();
        StringBuilder alertMessage = new StringBuilder("Error! The following issues are present:\n");
        // Get text from all input boxes.
        String itemName = nameInputBox.getText();
        String value = valueInputBox.getText();
        String serialNum = serialInputBox.getText();
        // Use the verifier's getInvalidAddString method to get a string with all errors involved with the input.
        String result = verifier.getAddingErrorString(itemName, value, serialNum, inventory.getCurrentInventory());
        // If the length was 0, no input errors were made.
        if (result.length() == 0) {
            // Convert the value input to a BigDecimal and add the item to the list.
            BigDecimal convertedVal = new BigDecimal(value);
            convertedVal = convertedVal.setScale(2, RoundingMode.UP);
            inventory.addItem(itemName,convertedVal,serialNum);
            // Clear the input boxes and update the tableview.
            clearInputBoxes();
            updateTable();
        } else {
            // Otherwise, create an alert box and have the error string display as a result.
            alertMessage.append(result);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void onEditItemPressed() {
        // Create a new InputVerifier object and a new StringBuilder for a potential error message.
        InputVerifier verifier = new InputVerifier();
        StringBuilder alertMessage = new StringBuilder("Error! The following issues are present:\n");
        // Get text from all input boxes and the currently selected item.
        String itemName = nameInputBox.getText();
        String value = valueInputBox.getText();
        String serialNum = serialInputBox.getText();
        Item itemToDelete = inventoryTableview.getSelectionModel().getSelectedItem();
        if (itemToDelete == null) {
            return;
        }
        // Use the verifier's getInvalidEditString method to get a string with all errors involved with the input.
        String result = verifier.getEditingErrorString(itemName, value, serialNum, inventory.getCurrentInventory());
        // If the length was 0, no input errors were made.
        if (result.length() == 0) {
            try {
                // Try to convert the text in the valueInputBox to a BigDecimal.
                BigDecimal convertedVal = new BigDecimal(value);
                convertedVal = convertedVal.setScale(2, RoundingMode.UP);
                // Call the edit item method on the selected item with the appropriate parameters.
                inventory.editItem(itemToDelete,itemName,convertedVal,serialNum);
            } catch (NumberFormatException e) {
                // If here is reached, then the valueInputBox was blank, meaning the user wanted no change.
                // An invalid value is passed to result in no change.
                inventory.editItem(itemToDelete,itemName,BigDecimal.valueOf(-1),serialNum);
            }
            // Clear the input boxes and update the tableview.
            clearInputBoxes();
            updateTable();
        } else {
            // Otherwise, create an alert box and have the error string display as a result.
            alertMessage.append(result);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        }
    }

    @FXML
    private void onDeleteButtonPressed(ActionEvent event) {
        // Get the currently selected items from the list.
        List<Item> itemsToDelete = inventoryTableview.getSelectionModel().getSelectedItems();
        // Create an alert to confirm if the user wants to delete the selected items.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you would like to delete the selected items?");
        Optional<ButtonType> result = alert.showAndWait();
        // If they press okay, delete the selected items from the inventory.
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Call the inventory's deleteItems method on the list of selected items.
            inventory.deleteItems(itemsToDelete);
            // Update the tableview.
            updateTable();
        }
    }

    @FXML
    private void onSearchTyped(KeyEvent event) {
        // When a key is typed into the search box, call the searchList method and set the result to the
        // tableview.
        visibleList = inventory.searchList(searchBox.getText());
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    private void onImportFilePressed(ActionEvent event) {
        // Create a FileChooser and set the extensions for HTML, TSV, and JSON.
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV files (*.txt)",
                "*.txt"), new FileChooser.ExtensionFilter("HTML files (*.html)",
                "*.html"), new FileChooser.ExtensionFilter("JSON files (*.json)",
                "*.json"));
        // Get the file from the user.
        File file = fileChooser.showOpenDialog(searchBox.getScene().getWindow());
        // Call the inventory's importTSV method and update the tableview.
        if (file != null) {
            inventory.importFile(file);
            updateTable();
        }
    }

    @FXML
    private void onExportFilePressed(ActionEvent event) {
        // Create a FileChooser and set the extensions for HTML, TSV, and JSON.
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TSV files (*.txt)",
                "*.txt"), new FileChooser.ExtensionFilter("HTML files (*.html)",
                "*.html"), new FileChooser.ExtensionFilter("JSON files (*.json)",
                "*.json"));
        // Get the file from the user.
        File file = fileChooser.showSaveDialog(searchBox.getScene().getWindow());
        // Call the inventory's importTSV method and update the tableview.
        if (file != null) {
            inventory.exportFile(file);
            updateTable();
        }
    }

    @FXML
    private void clearInputBoxes() {
        // Clear all input boxes.
        nameInputBox.clear();
        valueInputBox.clear();
        serialInputBox.clear();
    }

    @FXML
    private void updateTable() {
        // Refreshes and updates the tableview with the current master list.
        visibleList = inventory.getCurrentInventory();
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    private void toggleInputView(boolean mode) {
        // Change the visibility of all input related text boxes and labels.
        inputTitleLabel.setVisible(mode);
        itemNameLabel.setVisible(mode);
        nameInputBox.setVisible(mode);
        characterCountLabel.setVisible(mode);
        serialNumberLabel.setVisible(mode);
        serialInputBox.setVisible(mode);
        monetaryValueLabel.setVisible(mode);
        valueInputBox.setVisible(mode);
        itemInputButton.setVisible(mode);
        // Change the visibility of the delete label and button to be the opposite of the input related objects.
        deleteInstructLabel.setVisible(!mode);
        deleteButton.setVisible(!mode);
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
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("itemValue"));
        serialNumColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        // Specifically enable resizing of the itemName column due to the possible input length.
        nameColumn.setCellFactory(tc -> {
            TableCell<Item, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Region.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(nameColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
        // Create a character counter for the description box by binding the label to the length of the text within the
            // nameInputBox.
        characterCountLabel.textProperty().bind(nameInputBox.textProperty()
                .length()
                .asString("Character Count: %d"));
        // Set the items for the tableview.
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
    }
}
