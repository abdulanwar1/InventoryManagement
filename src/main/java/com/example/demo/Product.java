package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**

 Represents a product and contains associated parts.
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     Creates a new Product object.
     @param id the product ID
     @param name the name of the product
     @param price the price of the product
     @param stock the current inventory of the product
     @param min the minimum inventory of the product
     @param max the maximum inventory of the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     Sets the ID of the product.
     @param id the new ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     Sets the name of the product.
     @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     Sets the price of the product.
     @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     Sets the current inventory level of the product.
     @param stock the new inventory
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     Sets the minimum inventory level of the product.
     @param min the new minimum inventory
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     Sets the maximum inventory level of the product.
     @param max the new maximum inventory
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     Returns the ID of the product.
     @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     Returns the name of the product.
     @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     Returns the price of the product.
     @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     Returns the current inventory level of the product.
     @return the current inventory of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     Returns the minimum inventory of the product.
     @return the minimum inventory of the product
     */
    public int getMin() {
        return min;
    }

    /**
     Returns the maximum inventory of the product.
     @return the maximum inventory of the product
     */
    public int getMax() {
        return max;
    }

    /**
     Adds a part to the list of associated parts for the product.
     @param part the part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    /**

     This method removes the given part from the list of associated parts of the product.
     @param selectedAssociatedPart The part to be removed from the associated parts list.
     @return boolean Returns true if the part was successfully removed.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }
    /**

     This method returns an ObservableList containing all the parts associated with the product.
     @return ObservableList<Part> The ObservableList containing all associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }



}
