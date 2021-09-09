/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vaccines;

import helper.MenuHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 *
 * @author Kaine
 */
public class Vaccines extends ArrayList<Vaccine>{

    public Vaccines(int i) {
        super(i);
    }

    public Vaccines() {
    }

    public Vaccines(Collection<? extends Vaccine> clctn) {
        super(clctn);
    }
    
     public String chooseVaccine() {
        MenuHelper menu = new MenuHelper(this.size());
        for (int i = 0; i < this.size(); i++) {
            menu.add(this.get(i).getName());
        }
        
        int choice;
        do {
            System.out.println("Enter number to choose vaccine");
            choice = menu.getChoice();
            System.out.println("0 - Exit");
        } while (choice < 0 && choice > this.size());
        
        if (choice == 0){
            return null;
        }
        
        return this.get(choice - 1).getVaccineID();
    }
     
      public Vaccine searchVaccineByID(String ID) {
        for (Vaccine item : this) {
            if (item.getVaccineID() == null ? ID == null : item.getVaccineID().equals(ID)) {
                return item;
            }
        }
        return null;
    }
    

    public boolean loadFromFile(String fileName) throws FileNotFoundException, IOException {
        File f = new File(fileName);
        if (!f.exists()) return false;
	FileReader fr = new FileReader(f);
	BufferedReader bf = new BufferedReader(fr);
	String details;
        while ((details = bf.readLine()) != null) {
				StringTokenizer stk = new StringTokenizer(details);
				String vaccineID = stk.nextToken(",").trim();
				String name= stk.nextToken(",").trim();
				Vaccine e = new Vaccine(vaccineID, name);
				this.add(e);
			}
        return true;
    }
        
    public boolean saveToFile(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		if (!f.exists()) return false;
		try (PrintWriter out = new PrintWriter(f)) {
			this.forEach(item -> out.println(item.toFile()));
		}
		return true;
    }
  
}
