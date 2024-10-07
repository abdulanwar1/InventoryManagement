package com.example.demo;

import javafx.application.Platform;
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

 The Mainview class is the controller for the main view of the application.

 It initializes the view and handles user actions, such as adding, deleting, and modifying parts and products.
 It handles user input and updates the UI to display the inventory of parts and products.
 */
public class Mainscreencontroller implements Initializable {
    public static Part Changingpart() {
        return selectedPart;
    }
    private static Part selectedPart;
    public static Product Changingproduct() {
        return selectedProduct;
    }


    private static Product selectedProduct;
     @FXML
     Label MainLabel;
    public Button ExitButton;
    public Button AddPartsButton;
    public TextField SearchField;
    public TableView<Part>MainPartsTableView;

    public TextField MainProductsSearchField;

    public TableColumn ProductIDColumn;

    @FXML
    private TableView<Product>MainProductsTableView;

    public TableColumn ProductNameColumn;
    public TableColumn ProductInventoryColumn;
    public TableColumn ProductPriceColumn;
    public TableColumn IDColumn;
    public TableColumn NameColumn;
    public TableColumn InventoryColumn;
    public TableColumn PriceColumn;




    /**


     Opens the Add parts window when the button is clicked.
     @param event the event triggered by clicking the "Add parts" button
     @throws IOException if an I/O exception occurs when loading the addparts window
     */

    @FXML
    public void AddPartsClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Addpart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**

     Called when the DeletePartsClick is clicked.
     Deletes the selected part from the table view after confirming with the user.
     Displays an error message if no product is selected.
     @param event The event triggered by clicking the DeletePartsClick.
     */

    @FXML
    public void DeletePartsClick(ActionEvent event) {

        Part selectedPart = MainPartsTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not selected");
            alert.setContentText("Please select a part to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this part?");
            alert.setContentText("This is not reversible.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }

            }
        }

    /**

     Handles the Modify Parts button click event. Opens the Modify Part form with the selected part's data.
     @param event The button click event.
     @throws IOException If an I/O error occurs.
     */

    @FXML
    public void ModifyPartsClick(ActionEvent event) throws IOException {

        selectedPart = MainPartsTableView.getSelectionModel().getSelectedItem();
        if (MainPartsTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a part first");
            alert.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Modifypart.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**

     Handles the click event for the search button in the parts view. Searches for parts that match the search term and updates the parts table view accordingly.
     If the search term is empty, displays all parts.
     If no parts are found with the search term, displays an alert.
     @param event the action event triggered by the search button click
     */
    @FXML
    public void PartsSearchClick(ActionEvent event) {
        String searchTerm = SearchField.getText();
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();

        if (searchTerm.isEmpty()) {
            MainPartsTableView.setItems(Inventory.getAllParts());
        } else {
            for (Part part : Inventory.getAllParts()) {
                if (part.getName().contains(searchTerm) || Integer.toString(part.getId()).contains(searchTerm)) {
                    matchingParts.add(part);
                }
            }
            if (!matchingParts.isEmpty()) {
                MainPartsTableView.setItems(matchingParts);
                IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                InventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("No Parts Found");
                alert.setHeaderText(null);
                alert.setContentText("No parts found with the search term: " + searchTerm);
                alert.showAndWait();
            }
        }
    }
    /**

     Handles the Modify Products button click event.
     Opens the Modify Product form with the selected product from the table view.
     If no product is selected, an error message is displayed.
     @param event The event object generated by the click event.
     @throws IOException if the ModifyProduct.fxml file is not found.
     */
    @FXML
    public void ModifyProductsClick(ActionEvent event) throws IOException {

        selectedProduct = MainProductsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a product first");
            alert.show();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/example/demo/Modifyproduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**

     Called when the Delete Products button is clicked.
     Deletes the selected product from the table view after confirming with the user.
     Displays an error message if no product is selected.
     @param event The event triggered by clicking the Delete Products button.
     */


    @FXML
    public void DeleteProductsClick(ActionEvent event) {
        Product selectedProduct = MainProductsTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Product selectedDeleteProduct = MainProductsTableView.getSelectionModel().getSelectedItem();
                if (selectedDeleteProduct.getAllAssociatedParts().size() > 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please remove associated parts first.");
                    alert.showAndWait();
                    return;

                }
                else {
                    Inventory.deleteProduct(selectedProduct);
                }


            }
        }
    }
    /**

     Handles the action when the user clicks the search button to search for products.
     If the search term is empty, displays all products in the table view. Otherwise, searches for products
     whose name or ID contains the search term and displays the matching products in the table view.
     If no products match the search term, displays a dialog box indicating that no products were found.
     RUNTIME ERROR: The main issue I was having with this method is the observable list. When I first made this method,
     I did not include the observable list. It would not display any products which made it very confusing. I then added the
     "matchingProducts."
     @param event The ActionEvent object generated when the search button is clicked.
     */
    @FXML
    public  void SearchProductsClick(ActionEvent event) {
        String searchTerm = MainProductsSearchField.getText().trim().toLowerCase();
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();

        if (searchTerm.isEmpty()) {
            MainProductsTableView.setItems(Inventory.getAllProducts());
        } else {
            for (Product product : Inventory.getAllProducts()) {
                if (product.getName().toLowerCase().contains(searchTerm) || Integer.toString(product.getId()).contains(searchTerm)) {
                    matchingProducts.add(product);
                }
            }
            if (!matchingProducts.isEmpty()) {
                MainProductsTableView.setItems(matchingProducts);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("No Products Found");
                alert.setHeaderText(null);
                alert.setContentText("No products found with the search term: " + searchTerm);
                alert.showAndWait();
            }
        }
    }
    /**


     Opens the "Add Products" window when the button is clicked.
     @param event the event triggered by clicking the "Add Products" button
     @throws IOException if an I/O exception occurs when loading the "Add Products" window
     */
    @FXML
    public void AddProductsClick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Addproduct.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    /**

     Handles the event when the user clicks the Exit button.
     Closes the application window and exits the application.
     @param event the event triggered by the user's click on the Exit button
     */
    @FXML
    public void ExitClick(ActionEvent event) {
        Platform.exit();
    }





    /**

     Initializes the Mainview class.

     It initializes the parts and products table views with their respective data and property values.

     @param url The location of the fxml file.

     @param resourceBundle The resources used to localize the fxml file.
     */


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        MainPartsTableView.setItems(Inventory.getAllParts());
        System.out.println(Inventory.getAllParts().size());
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        MainProductsTableView.setItems(Inventory.getAllProducts());
        ProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
