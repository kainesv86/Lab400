/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vaccines;

/**
 *
 * @author Kaine
 */
public class Vaccine {
    private final String VaccineID;
    private String name;

    public Vaccine(String VaccineID, String name) {
        this.VaccineID = VaccineID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVaccineID() {
        return VaccineID;
    }
    
    @Override
    public String toString() {
        return "VaccinesId: " + this.getVaccineID() + ", Vaccine name: " + this.getName();
    }
    
    public String toFile() {
        return this.getVaccineID() + ", " + this.getName();
    }
    
    
}
