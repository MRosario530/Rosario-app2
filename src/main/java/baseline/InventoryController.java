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

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController {

    @FXML
    private Button addItemButton;

    @FXML
    private Label characterCountLabel;

    @FXML
    private MenuItem clearAllButton;

    @FXML
    private MenuItem deleteSelectedButton;

    @FXML
    private TableView<?> inventoryTableview;

    @FXML
    private TableColumn<?, ?> nameColumn;

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
    private TableColumn<?, ?> serialNumColumn;

    @FXML
    private MenuItem uploadHTMLButton;

    @FXML
    private MenuItem uploadJSONButton;

    @FXML
    private MenuItem uploadTSVButton;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    private TextField valueInputBox;

    @FXML
    void onAddItemPressed(ActionEvent event) {

    }

    @FXML
    void onClearAllPressed(ActionEvent event) {

    }

    @FXML
    void onDeleteSelectedPressed(ActionEvent event) {

    }

    @FXML
    void onSaveHTMLPressed(ActionEvent event) {

    }

    @FXML
    void onSaveJSONPressed(ActionEvent event) {

    }

    @FXML
    void onSaveTSVPressed(ActionEvent event) {

    }

    @FXML
    void onUploadHTMLPressed(ActionEvent event) {

    }

    @FXML
    void onUploadJSONPressed(ActionEvent event) {

    }

    @FXML
    void onUploadTSVPressed(ActionEvent event) {

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

    }

}
