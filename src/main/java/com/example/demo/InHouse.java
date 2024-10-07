package com.example.demo;
/**
 A subclass of the Part class.
 */
public class InHouse extends Part {
    /**
     Constructor for Inhouse parts.

     @param id        The identifier for the part.
     @param name      The name of the part.
     @param price     The price of the part.
     @param stock     The current inventory of the part.
     @param min       The minimum inventory of the part.
     @param max       The maximum inventory of the part.
     @param machineId The machine ID of the Inhouse part.
     */
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     @return the machineId
     */
    public int getMachineId() {
        return this.machineId;
    }

    /**
     @param machineId the machineId to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
