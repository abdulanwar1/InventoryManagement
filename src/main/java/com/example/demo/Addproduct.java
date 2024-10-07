package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**

 This class handles the Add Product form of the inventory management system.
 It allows the user to input details of a new product and add it to the inventory.
 */
public class Addproduct implements Initializable {
    public TextField IDField;
    public TextField NameField;
    public TextField InventoryField;
    public TextField PriceField;
    public TextField MinField;
    public TextField MaxField;
    public TextField SearchField;
    public javafx.scene.control.TableView TableView;
    public TableColumn IDColumn;
    public TableColumn NameColumn;
    public TableColumn InventoryColumn;
    public TableColumn PriceColumn;

    public TableColumn PartIDColumn;
    public TableColumn PartNameColumn;
    public TableColumn PartInventoryColumn;
    public TableColumn PartPriceColumn;
    public javafx.scene.control.TableView associatedProductTable;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();




    /**

     Handles the search button click event. Searches for parts by part ID or name and displays the search results

     in the associated parts table.

     @param event the event object
     */
    @FXML
    public void SearchBtn(ActionEvent event) {

        String searchTerm = SearchField.getText().trim().toLowerCase();
        if (searchTerm.isEmpty()) {
            TableView.setItems(Inventory.getAllParts());
        }
         else {
            ObservableList<Part> searchResults = FXCollections.observableArrayList();
            try {
                int Id = Integer.parseInt(searchTerm);
                Part part = Inventory.lookupPart(Id);
                if (part != null) {
                    searchResults.add(part);
                }
            } catch (NumberFormatException e) {
                searchResults = Inventory.lookupPart(searchTerm);
            }
            if (searchResults.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Part not found");
                alert.setContentText("The part you searched for could not be found.");
                alert.showAndWait();
            } else {
                // Highlight or filter the search results.
                TableView.setItems(searchResults);
            }
        }
    }
    /**

     Handles the add button click event. Adds a selected part from the search results to the list of associated parts

     for the new product being added.

     @param event the event object
     */

    @FXML
    public void AddBtn(ActionEvent event) {
        Part selectedPart = (Part) TableView.getSelectionModel().getSelectedItem();

        if (selectedPart != null) {
            associatedParts.add(selectedPart);

            }
        else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Select a part from the list of options");
                alert.showAndWait();
                return;
            }
        }

    /**

     Handles the delete button click event. Deletes a selected associated part from the list of associated parts
     for the new product being added.
     @param event the event object
     */
    @FXML
    public void DeleteBtn(ActionEvent event) {
        Part selectedPart = (Part) associatedProductTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not selected");
            alert.setContentText("Please select a part to remove.");
            alert.showAndWait();
            return;
        }
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Are you sure you want to delete this part?");
        confirmAlert.setContentText("This not reversible");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            associatedParts.remove(selectedPart);

        }
    }



     /**

     This method is called when the save button is clicked. It retrieves data entered by the user, validates it, creates a new product object

     and saves it to the inventory. It also displays an error message if the data is invalid.

     @param event The event triggered by clicking the save button

     @throws IOException If there is an error while saving the product to the inventory
     */
    @FXML
    public void SaveClick(ActionEvent event) throws IOException {


            int id = (int) (Math.random() * 50);
            String name = NameField.getText();
            int stock = 0;
            int min;
            int max = 0;
            double price;
            try {
                stock = Integer.parseInt(InventoryField.getText());

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for inventory. Please enter an integer.");
                alert.showAndWait();
                return;
            }

            try {
                price = Double.parseDouble(PriceField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for price Please enter a double or int.");
                alert.showAndWait();
                return;
            }
            try {
                min = Integer.parseInt(MinField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Min. Please enter an integer.");
                alert.showAndWait();
                return;
            }
            try {
                max = Integer.parseInt(MaxField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Max. Please enter an integer.");
                alert.showAndWait();
                return;
            }

            if (min >= max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: maximum must be greater than minimum");
                alert.showAndWait();
                return;
            }

            else if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: Inventory must be within min and max.");
                alert.showAndWait();
                return;
            }

            Product newProduct = new Product(id, name, price, stock, min, max);
            for (Part part : associatedParts) {
                newProduct.addAssociatedPart(part);
            }
            Inventory.getAllProducts().add(newProduct);

            Parent mainScreen = FXMLLoader.load(getClass().getResource("/com/example/demo/Mainview.fxml"));
            Scene scene = new Scene(mainScreen);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();


    }


    /**

     This method is called when the cancel button is clicked. It goes back to the main screen of the inventory management system.
     @param event The event triggered by clicking the cancel button
     @throws IOException If there is an error while loading the main screen
     */
    @FXML
    public void CancelClick(ActionEvent event) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("/com/example/demo/Mainview.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /**

     Initializes the form by setting the initial code that needs to be implemented. It initializes the associated parts.
     @param url The URL of the AddProduct.fxml file
     @param resourceBundle The resource bundle associated with the AddProduct.fxml file
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableView.setItems(Inventory.getAllParts());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedProductTable.setItems(associatedParts);
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
