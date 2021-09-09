/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Injections;

import java.util.ArrayList;

/**
 *
 * @author Kaine
 */
public class Injection {
    private final String injectionID;
    private ArrayList<Detail> details;
    private final String studentID;
    private final String vaccineID;

    public Injection(String injectionID, ArrayList<Detail> details, String studentID, String vaccineID) {
        this.injectionID = injectionID;
        this.details = details;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }
    
    @Override
    public String toString() {
        String build = "InjectionId: " + this.injectionID + ", Number of injection: " + this.getDetails().size() + ", StudentID: " + this.getStudentID() + ", VaccineID: " + this.getVaccineID();
        return build;
    }
    
    public String toFile() {
        String build = this.getInjectionID() + ", " + this.getStudentID() + ", " + this.getVaccineID();
        ArrayList<Detail> details = this.getDetails();
        
        for (Detail detail : details) {
            if (!detail.getPlace().isEmpty()) {
                build = build + ", " + detail.getPlace() + ", " + detail.getDate();
            }
        }

        return build;
    }

}
