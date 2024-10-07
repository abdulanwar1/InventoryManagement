package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**

 This class handles the Add Part form of the inventory management system.
 It allows the user to input details of a new part and add it to the inventory.
 */
public class Addpart implements Initializable {


    @FXML
    private RadioButton InHouseRBtn;

    @FXML
    private RadioButton OutsourcedRBtn;

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField InventoryField;

    @FXML
    private TextField PriceField;

    @FXML
    private TextField MaxField;

    @FXML
    private TextField MinField;

    @FXML
    private TextField DAddField;

    @FXML
    private Label AddLabel;

    private boolean isInHouse;


    /**

     Updates the data label to display "Machine ID" when In-House is selected.
     This is an event handler for the InHouseRBtn.
     @param event The ActionEvent that occurred.
     */
    @FXML
    public void InHouseRBtn(ActionEvent event) {
        AddLabel.setText("Machine ID");
        isInHouse = true;
    }
    /**

     Updates the data label to display "Company Name" when Outsourced is selected.
     This is an event handler for the OutsourcedRBtn.
     @param event The ActionEvent that occurred.
     */

    @FXML
    public void OutsourcedRBtn(ActionEvent event) {
        AddLabel.setText("Company Name");
        isInHouse = false;
    }

    /**

     Displays a confirmation dialog to the user when they click the "Cancel" button.

     If the user confirms the cancellation, the form is closed and the user is returned to the Main form.

     @param event The ActionEvent that occurred.
     */
    @FXML
    public void CancelClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Cancel Adding Part");
        alert.setContentText("Are you sure you want to cancel adding the part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent main;
            try {
                main = FXMLLoader.load(getClass().getResource("Mainview.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(main);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
    /**

     Saves the newly created part by retrieving the input values, creating a new Part object,

     adding it to the inventory, and navigating back to the main form.

     @param event the ActionEvent triggered by the Save button click
     */
    @FXML
    public  void SaveClick(ActionEvent event) throws IOException {
        int id = (int) (Math.random() * 100);
        String name = NameField.getText();
        int stock = 0;
        double price;
        int max;
        int min;

        if (isInHouse) {
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
                max = Integer.parseInt(MaxField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Max. Cannot be null and only accepts Integer.");
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

            try {

                int machineId = Integer.parseInt(DAddField.getText());
                InHouse selectedPart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(selectedPart);

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Machine ID. Cannot be null and only accepts Integer.");
                alert.showAndWait();
                return;
            }

        } if (!isInHouse) {
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
                max = Integer.parseInt(MaxField.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Max. Cannot be null and only accepts Integer.");
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

            try {
                String companyName = DAddField.getText();
                Outsourced selectedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(selectedPart);

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input for Company Name. Cannot be null");
                alert.showAndWait();
                return;
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Mainview.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();


}
    /**

     Initializes the form by setting the initial code that needs to be implemented.

     @param url the location used to resolve relative paths for the root object.

     @param resourceBundle the resource bundle used to localize the root object.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        isInHouse = true;

    }
}
