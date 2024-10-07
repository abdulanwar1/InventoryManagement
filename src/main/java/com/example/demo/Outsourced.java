package com.example.demo;
/**

 A subclass of the Part class.
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     Constructs an Outsourced object.
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the inventory of the part
     @param min the minimum inventory of the part
     @param max the maximum inventory of the part
     @param companyName the name of the company that made the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     Returns the name of the company that made the part.
     @return the name of the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     Sets the name of the company that made the part.
     @param companyName the name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}