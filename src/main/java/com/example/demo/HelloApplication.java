package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 The HelloApplication class extends the Application class and is used to start the entire project.

 Mohammad Haji
 The javadocs folder is included in the project.
 FUTURE ENHANCEMENTS: When speaking about enhancing the project, I would first add another column to the parts table that
 would calculate the total price of the all the products. Secondly, I would add something similar to the product table;
 It will display the entire price for the product and the associated parts. Finally, instead of just informing that it has associated parts
 when deleting a project, I would display the associated parts and they can potentially delete the parts from the popup warning.
 */
public class HelloApplication extends Application {

    /**

     The start method called by the Application. It sets up
     the main view of the project and displays it.
     @param stage The first stage of the project.
     @throws IOException If an exception occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Mainview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setScene(scene);
        stage.show();
    }
    /**

     The main method is the start of the project. It creates some sample parts and products, adds them to
     the inventory, and then launches the project.
     @param args Initial line implemented at the start
     */
    public static void main(String[] args) {
        InHouse p1 = new InHouse(1,"tire",20.0,4,2,5,1);
        Inventory.addPart(p1);
        Outsourced p2 = new Outsourced(2,"seat",30.0,5,2,6,"Bodyshop");
        Inventory.addPart(p2);
        InHouse p3 = new InHouse(3,"steering wheel",40.0,6,2,8,2);
        Inventory.addPart(p3);
        Outsourced p4 = new Outsourced(4,"hood",50.0,7,2,10,"Autozone");
        Inventory.addPart(p4);
        Product car1 = new Product(1,"toyota",8000.00,2,1,4);
        Inventory.addProduct(car1);
        Product car2 = new Product(2,"Honda",9000.00,3,1,4);
        Inventory.addProduct(car2);
        Product car3 = new Product(3,"Dodge",10000.00,2,1,4);
        Inventory.addProduct(car3);
        Product car4 = new Product(4,"Lexus",12000.00,3,1,4);
        Inventory.addProduct(car4);


        launch();
    }
}