package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**

 This class represents the inventory of parts and products. It provides methods for adding, removing, and updating parts and products,
 as well as searching for specific parts or products.
 */
public class Inventory {


    private static  ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static  ObservableList<Product> allProducts = FXCollections.observableArrayList();



    /**
     Adds a new part to the inventory.
     @param newPart the new part to add to the inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }


    /**
     Adds a new product to the inventory.
     @param newProduct the new product to add to the inventory
     */

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    /**
     Searches for a part in the inventory by part ID.
     @param partId the ID of the part to search for
     @return the part with the given ID, or null if the part is not found
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    /**
     Searches for a product in the inventory by product ID.
     @param productId the ID of the product to search for
     @return the product with the given ID, or null if the product is not found
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    /**
     * Searches for parts in the inventory that match a given name.
     * @param partName the name to search for
     * @return a list of parts that match the given name
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }
    /**
     Searches for products in the inventory that match a given name.
     @param productName the name to search for
     @return a list of products that match the given name
     */
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }
    /**
     Updates a part in the inventory.
     @param index the index of the part to update
     @param selectedPart the updated part
     */

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    /**
     Updates the product at the specified index in the allProducts list.

     @param index The index of the product to be updated.
     @param newProduct The updated product.
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
    /**
     Deletes the specified part from the allParts list.
     @param selectedPart The part to be deleted.
     @return True if the part was successfully deleted, false otherwise.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    /**
     * Deletes the specified product from the allProducts list.
     *
     * @param selectedProduct The product to be deleted.
     * @return True if the product was successfully deleted, false otherwise.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    /**
     Returns the allParts list.

     @return The allParts list.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**
     Returns the allProducts list.

     @return The allProducts list.
     */

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
