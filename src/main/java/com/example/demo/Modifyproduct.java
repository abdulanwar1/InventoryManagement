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

 Controller class for the "Modify product" screen.
 Allows the user to modify an existing product in the inventory system.
 */
public class Modifyproduct implements Initializable {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public TextField IDField;
    public TextField NameField;
    public TextField InventoryField;
    public TextField PriceField;
    public TextField MinField;
    public TextField MaxField;
    public TableView TableView;
    public TableColumn IDColumn;
    public TableColumn NameColumn;
    public TableColumn InventoryColumn;
    public TableColumn PriceColumn;
    public TextField SearchField;
    public TableView DeleteView;
    public TableColumn PartIDColumn;
    public TableColumn PartNameColumn;
    public TableColumn PartInventoryColumn;
    public TableColumn PartPriceColumn;

    private Product selectedProduct;

    /**

     This method is called when the "Search" button is clicked on the "Modify Product" screen.
     It retrieves the search term entered by the user, searches for matching parts in the Inventory,
     and updates the associated parts table with the search results. If no parts are found,
     an error message is displayed. If the search term is empty, all parts in the Inventory are displayed.
     @param event the event that triggered the method call
     */
    @FXML
    public void SearchPartClick(ActionEvent event) {

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
                alert.setHeaderText("No part found");
                alert.setContentText("The part you searched for could not be found.");
                alert.showAndWait();
            } else {
                TableView.setItems(searchResults);
            }
        }
    }



    /**

     This method is called when the user clicks on the "Add" button in the "Modify Product" screen.
     It adds the currently selected part from the "All Parts" table to the list of associated parts for the selected product.
     If no part is selected, nothing happens.
     The associated parts table is updated with the new list of associated parts for the selected product.
     @param event The ActionEvent object generated when the "Add" button is clicked.
     */
    @FXML
    public void AddClick(ActionEvent event) {
        Part selectedPart = (Part) TableView.getSelectionModel().getSelectedItem();


        if (selectedPart != null) {
            associatedParts.add(selectedPart);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Select proper part from the list of options");
            alert.showAndWait();

        }
    }
    /**

     Handles the delete button click event.

     Removes the selected part from the associated parts list of the current product.

     If the selected part is not associated with the current product, it is removed from the inventory altogether.

     If no part is selected, an error message is displayed.

     Asks for confirmation before deleting the part.
     */
    @FXML
    public void DeleteClick(ActionEvent event) {
        Part selectedPart = (Part) DeleteView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part to remove.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Are you sure you want to delete this part?");
        confirmAlert.setContentText("This is not reversible.");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            selectedProduct.getAllAssociatedParts().remove(selectedPart);
            DeleteView.setItems(FXCollections.observableArrayList(selectedProduct.getAllAssociatedParts()));
        }
    }

    /**

     Handles the event when the "Save" button is clicked on the "Add Product" screen.
     Validates the input fields and either displays an error message or adds the new product to the inventory.
     RUNTIME ERROR: For the perspective I was seeing the code, everything seemed proper. However, when I ran it,
     it would not allow me to go back to the mainview. The issue was with my indentations and the try and catch block.

     @param event The action event triggered by clicking the "Save" button.
     @throws IOException If an input/output exception occurs while updating the inventory.
     */
    @FXML
    public void SaveButtonClick(ActionEvent event) throws IOException {


        int id = selectedProduct.getId();
        String name = NameField.getText();
        int stock = 0;
        int min;
        int max = 0;
        double price;
        try {
            stock = Integer.parseInt(InventoryField.getText());

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for inventory. Cannot be null and only accepts Integer.");
            alert.showAndWait();
            return;
        }

        try {
            price = Double.parseDouble(PriceField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for price. Cannot be null and only accepts Integer or Double.");
            alert.showAndWait();
            return;
        }
        try {
            min = Integer.parseInt(MinField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Min. Cannot be null and only accepts Integer.");
            alert.showAndWait();
            return;
        }
        try {
            max = Integer.parseInt(MaxField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Max. Cannot be null and only accepts Integer.");
            alert.showAndWait();
            return;
        }

        if (min >= max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory error! Minimum must be less than maximum");
            alert.showAndWait();
            return;
        }

        else if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory error! Inventory must be within min and max.");
            alert.showAndWait();
            return;
        }
        Product selectedProduct1 = new Product(id, name, price, stock, min, max);


        Inventory.addProduct(selectedProduct1);
        Inventory.deleteProduct(selectedProduct);
        for(Part part: associatedParts) {
            selectedProduct1.addAssociatedPart(part);
        }
        Parent mainScreen = FXMLLoader.load(getClass().getResource("/com/example/demo/Mainview.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }


    /**

     Handles the "Cancel" button click event to return to the main screen.
     @param event The event that triggered this method (i.e. the "Cancel" button click).
     @throws IOException if an input/output exception occurs while loading the FXML file.
     */
    @FXML
    public void CancelClick(ActionEvent event) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("Mainview.fxml"));
        Scene scene = new Scene(mainScreen);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }





    /**

     Initializes the ModifyProduct class.
     This method sets the initial values for the Modify Product form.
     It sets the values of the fields with the corresponding values of the selected product.
     It sets the columns of the associated parts table view and the modify product add table view.
     It also enables editing for all fields except Id.
     @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        selectedProduct = Mainscreencontroller.Changingproduct();


        associatedParts = selectedProduct.getAllAssociatedParts();
        TableView.setItems(Inventory.getAllParts());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        DeleteView.setItems(associatedParts);
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        IDField.setText(String.valueOf(selectedProduct.getId()));
        NameField.setText(selectedProduct.getName());
        InventoryField.setText(String.valueOf(selectedProduct.getStock()));
        PriceField.setText(String.valueOf(selectedProduct.getPrice()));
        MaxField.setText(String.valueOf(selectedProduct.getMax()));
        MinField.setText(String.valueOf(selectedProduct.getMin()));

    }
}






