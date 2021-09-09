/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Injections;

import helper.ScannerCus;
import helper.Validator;
import java.sql.Date;

/**
 *
 * @author Kaine
 */
public class Detail {
    private String place;
    private Date date;

    public Detail(String place, Date date) {
        this.place = place;
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public static Detail makeDetail(int index) {
        ScannerCus sc = new ScannerCus();
        String place = sc.getString(Validator.StringType.NORMAL, 0, 255, "Enter the place " + (index + 1) + ": ");
        Date date = sc.getDate("Enter injections time (yyyy-mm-dd): ");
    
        return new Detail(place, date);
    }
    
    @Override
    public String toString() {
        return "Place: " + this.place + ", Date: " + this.date;
    }
    
    
}
