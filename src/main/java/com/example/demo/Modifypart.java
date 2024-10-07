package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**

 This class represents the Modify Part form controller, responsible for managing the user interface

 for modifying an existing part in the inventory management system.
 */
public class Modifypart implements Initializable {
    public RadioButton InHouseRBtn;
    public RadioButton OutsourcedRBtn;
    public Label DModifyLabel;
    public TextField IDField;
    public TextField NameField;
    public TextField InventoryField;
    public TextField PriceField;
    public TextField MinField;
    public TextField MaxField;
    public TextField DField;
    private boolean isInHouse;

    private Part selectedPart;


    /**

     Handles the In-House radio button selection by changing the label text and setting isInHouse to true.
     @param event the event that occurred
     */

    @FXML
    public void InHouseRBtn(ActionEvent event) {
        DModifyLabel.setText("Machine ID");
        isInHouse = true;
    }

    /**

     Handles the Outsourced radio button selection by changing the label text and setting isInHouse to false.
     @param event the event that occurred
     */
    @FXML
    public void OutsourcedRBtn(ActionEvent event) {
        DModifyLabel.setText("Company Name");
        isInHouse = false;
    }
    /**
      Saves the modifications made to the selected part by creating a new Part object with the new data
      and updating it in the Inventory, then closes the Modify Part window.
      RUNTIME ERROR: The project was running properly, however, when I tested the test cases against my project,
      it would not display the proper message and would just crash.
      @param event the ActionEvent representing the clicking of the Save button
     */

    @FXML
    public void SaveClick(ActionEvent event) throws IOException {

        int id = selectedPart.getId();
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

                int machineId = Integer.parseInt(DField.getText());
                InHouse selectedPart1 = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(selectedPart1);
                Inventory.deletePart(selectedPart);

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

                String companyName = DField.getText();
                Outsourced selectedPart1 = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(selectedPart1);
                Inventory.deletePart(selectedPart);
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
          Closes the Modify Part window without saving any changes.

          @param event the ActionEvent representing the clicking of the Cancel button
          @throws IOException if an I/O error occurs while closing the window
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
    Initializes the form by setting the initial code that needs to be implemented.
    It initializes the selected part, checks if its can instance of inhouse, and gets values.
    @param url the location used to resolve relative paths for the root object.

    @param resourceBundle the resource bundle used to localize the root object.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        selectedPart = Mainscreencontroller.Changingpart();

        if (selectedPart instanceof InHouse) {
            InHouseRBtn.setSelected(true);
            DModifyLabel.setText("Machine ID");
            DField.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            InHouseRBtn.setSelected(false);
            DModifyLabel.setText("Company Name");
            DField.setText(((Outsourced) selectedPart).getCompanyName());
        }

        IDField.setText(String.valueOf(selectedPart.getId()));
        NameField.setText(selectedPart.getName());
        InventoryField.setText(String.valueOf(selectedPart.getStock()));
        PriceField.setText(String.valueOf(selectedPart.getPrice()));
        MaxField.setText(String.valueOf(selectedPart.getMax()));
        MinField.setText(String.valueOf(selectedPart.getMin()));
    }
    }




