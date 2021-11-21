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
    private MenuItem exportHTMLButton;

    @FXML
    private MenuItem exportJSONButton;

    @FXML
    private MenuItem exportTSVButton;

    @FXML
    private TextField searchBox;

    @FXML
    private TextField serialInputBox;

    @FXML
    private TableColumn<Item, String> serialNumColumn;

    @FXML
    private MenuItem importHTMLButton;

    @FXML
    private MenuItem importJSONButton;

    @FXML
    private MenuItem importTSVButton;

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
        // Get the currently selected items from the list.
        List<Item> itemsToDelete = inventoryTableview.getSelectionModel().getSelectedItems();
        // Call the inventory's deleteItems method on the list of selected items.
        inventory.deleteItems(itemsToDelete);
        // Update the tableview.
        updateTable();
    }

    @FXML
    void onDeleteTogglePressed(ActionEvent event) {
        // Check to see what the currentMode value is.
        // If the value is 3, do nothing.
        if (currentMode != 3) {
            // Otherwise call toggleInputView to make all input fields and labels invisible.
            toggleInputView(false);
            // Set the tableview selection model to allow multiple selections.
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
        // If the value is 1, call the onAddItemPressed method.
        if (currentMode == 1) {
            onAddItemPressed();
        } else {
            // Otherwise, call the on editItemPressed method.
            onEditItemPressed();
        }
    }

    @FXML
    void onAddItemPressed() {
        // Create a new InputVerifier object and a new StringBuilder for a potential error message.
        InputVerifier verifier = new InputVerifier();
        StringBuilder alertMessage = new StringBuilder("Error! The following issues are present:\n");
        // Get text from all input boxes.
        String itemName = nameInputBox.getText();
        String value = valueInputBox.getText();
        String serialNum = serialInputBox.getText();
        // Use the verifier's getInvalidAddString method to get a string with all errors involved with the input.
        String result = verifier.getInvalidAddString(itemName, value, serialNum, inventory.getCurrentInventory());
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
            // Otherwise create an alert box and have the error string display as a result.
            alertMessage.append(result);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void onEditItemPressed() {
        // Create a new InputVerifier object and a new StringBuilder for a potential error message.
        InputVerifier verifier = new InputVerifier();
        StringBuilder alertMessage = new StringBuilder("Error! The following issues are present:\n");
        // Get text from all input boxes and the currently selected item.
        String itemName = nameInputBox.getText();
        String value = valueInputBox.getText();
        String serialNum = serialInputBox.getText();
        Item itemToDelete = inventoryTableview.getSelectionModel().getSelectedItem();
        // Use the verifier's getInvalidEditString method to get a string with all errors involved with the input.
        String result = verifier.getInvalidEditString(itemName, value, serialNum, inventory.getCurrentInventory());
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
            // Otherwise create an alert box and have the error string display as a result.
            alertMessage.append(result);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText(alertMessage.toString());
            alert.showAndWait();
        }
    }

    @FXML
    void onSearchTyped(KeyEvent event) {
        // When a key is typed into the search box, call the searchList method and set the result to the
        // tableview.
        visibleList = inventory.searchList(searchBox.getText());
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    void clearInputBoxes() {
        // Clear all input boxes.
        nameInputBox.clear();
        valueInputBox.clear();
        serialInputBox.clear();
    }

    @FXML
    void updateTable() {
        // Refreshes and updates the tableview with the current master list.
        visibleList = inventory.getCurrentInventory();
        inventoryTableview.setItems(FXCollections.observableArrayList(visibleList));
        inventoryTableview.refresh();
    }

    @FXML
    void toggleInputView(boolean mode) {
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
    void onExportHTMLPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to html.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter htmlFile = new FileChooser.ExtensionFilter("HTML file (*.html)",
                "*.html");
        // Set the extension to html.
        fileChooser.getExtensionFilters().add(htmlFile);
        // Get the location of the file the user is choosing to save to.
        File file = fileChooser.showSaveDialog(searchBox.getScene().getWindow());
        // Save the list to an html file provided a location was chosen.
        if (file != null) {
            inventory.exportHTML(file);
        }
    }

    @FXML
    void onExportJSONPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to JSON.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jsonFile = new FileChooser.ExtensionFilter("JSON file (*.json)",
                "*.json");
        // Set the extension to json.
        fileChooser.getExtensionFilters().add(jsonFile);
        // Get the location of the file the user is choosing to save to.
        File file = fileChooser.showSaveDialog(searchBox.getScene().getWindow());
        // Save the list to a json file provided a location was chosen.
        if (file != null) {
            inventory.exportJSON(file);
        }
    }

    @FXML
    void onExportTSVPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to txt.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter txtFile = new FileChooser.ExtensionFilter("TSV file (*.txt)",
                "*.txt");
        // Set the extension to txt.
        fileChooser.getExtensionFilters().add(txtFile);
        // Get the location of the file the user is choosing to save to.
        File file = fileChooser.showSaveDialog(searchBox.getScene().getWindow());
        // Save the list to a txt file provided a location was chosen.
        if (file != null) {
            inventory.exportTSV(file);
        }
    }

    @FXML
    void onImportHTMLPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to HTML.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter htmlFile = new FileChooser.ExtensionFilter("HTML file (*.html)",
                "*.html");
        fileChooser.getExtensionFilters().add(htmlFile);
        // Get the file from the user.
        File file = fileChooser.showOpenDialog(searchBox.getScene().getWindow());
        // Call the inventory's importHTML method and update the tableview.
        if (file != null) {
            inventory.importHTML(file);
            updateTable();
        }
    }

    @FXML
    void onImportJSONPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to JSON.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter jsonFile = new FileChooser.ExtensionFilter("JSON file (*.json)",
                "*.json");
        fileChooser.getExtensionFilters().add(jsonFile);
        // Get the file from the user.
        File file = fileChooser.showOpenDialog(searchBox.getScene().getWindow());
        // Call the inventory's importJSON method and update the tableview.
        if (file != null) {
            inventory.importJSON(file);
            updateTable();
        }
    }

    @FXML
    void onImportTSVPressed(ActionEvent event) {
        // Create a FileChooser and set the extension to TSV.
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter tsvFile = new FileChooser.ExtensionFilter("TSV file (*.txt)",
                "*.txt");
        fileChooser.getExtensionFilters().add(tsvFile);
        // Get the file from the user.
        File file = fileChooser.showOpenDialog(searchBox.getScene().getWindow());
        // Call the inventory's importTSV method and update the tableview.
        if (file != null) {
            inventory.importTSV(file);
            updateTable();
        }
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
