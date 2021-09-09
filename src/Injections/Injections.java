/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Injections;


import Students.Students;
import Vaccines.Vaccines;
import helper.MenuHelper;
import helper.ScannerCus;
import helper.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.time.LocalDate;

/**
 *
 * @author Kaine
 */
public class Injections extends ArrayList<Injection> {

    public Injections(int i) {
        super(i);
    }

    public Injections() {
    }

    public Injections(Collection<? extends Injection> clctn) {
        super(clctn);
    }
    
    public void printAllInjections(Students students, Vaccines vaccines) {
        if (this.isEmpty()) {
            System.out.println("There are no injection");
            return;
        }
        for (int i = 0; i < this.size(); i++) {
            System.out.println("=====================================================");
            System.out.println("InjectionId: " + this.get(i).getInjectionID());
            ArrayList<Detail> details = this.get(i).getDetails();
            if (!details.isEmpty()) {
                for (int j = 0; j < details.size(); j++) {
                    System.out.println(details.get(j).toString());
                }
            }
            
            System.out.println(students.searchStudentByID(this.get(i).getStudentID()).toString());
            System.out.println(vaccines.searchVaccineByID(this.get(i).getVaccineID()).toString());
            System.out.println("=====================================================");
        }
    }
    
    public void addInjection(Students students, Vaccines vaccines) {
        ScannerCus sc = new ScannerCus();
        boolean isExist;
        String injectionID;
        do {   
            isExist = false;
            injectionID = sc.getString(Validator.StringType.STRING, 0, 255, "Enter InjectionID: ");
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getInjectionID().equals(injectionID)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) System.out.println("This Id was existed");
        } while (isExist);
        
        ArrayList<Detail> details = new ArrayList<Detail>();
        Detail e = Detail.makeDetail(0);
        if (!e.getPlace().isEmpty()) {
            details.add(e);
            do {
                e = Detail.makeDetail(1);
                LocalDate lowerDate = details.get(0).getDate().toLocalDate().plusDays(28);
                LocalDate upperDate = details.get(0).getDate().toLocalDate().plusDays(84);
                if (Date.valueOf(lowerDate).compareTo(e.getDate()) < 0 && Date.valueOf(upperDate).compareTo(e.getDate()) > 0) {
                    break;
                } else {
                    System.out.println("Invalid value, must around " + lowerDate + " - " + upperDate);
                }
            } while (true);
            if (!e.getPlace().isEmpty()) {
                System.out.println("");
                details.add(e);
            }
        }
        String studentID;
        do {
            studentID = students.chooseStudent();
            if (searchInjectionByStudentID(studentID) == null) {
                break;
            } else {
                System.out.println("This student already got injection");
            }
        } while (true);
     
        if (studentID == null) {
            System.out.println("Choose student already cancel, add injection failed");
            return;
        }
        
        String vaccineID = vaccines.chooseVaccine();
        
        this.add(new Injection(injectionID, details, studentID, vaccineID));
    } 
    
    public Injection searchInjectionByID(String ID) {
        for (Injection item : this) {
            if (item.getInjectionID()== null ? ID == null : item.getInjectionID().equals(ID)) {
                return item;
            }
        }
        return null;
    }
       
    public void updateInjections(String injectionID) {
        Injection e = searchInjectionByID(injectionID);
        if (e == null) {
            System.out.println("ID given not found");
            return;
        }
        
        int index = this.indexOf(e);
        
        int size = e.getDetails().size();
        
        switch (size) {
            case 0:
                e.getDetails().add(Detail.makeDetail(e.getDetails().size()));
                break;
            case 1:
                do {
                    Detail date = e.getDetails().get(0);
                     Detail entity = Detail.makeDetail(1);
                    LocalDate lowerDate = date.getDate().toLocalDate().plusDays(28);
                    LocalDate upperDate = date.getDate().toLocalDate().plusDays(84);
                    if (Date.valueOf(lowerDate).compareTo(entity.getDate()) < 0 && Date.valueOf(upperDate).compareTo(entity.getDate()) > 0) {
                     e.getDetails().add(Detail.makeDetail(e.getDetails().size()));
                     break;
                    } else {
                        System.out.println("Invalid value, must around " + lowerDate + " - " + upperDate);
                    }
                } while (true);  
                break;
            default:
                break;
        }
        
        if (e.getDetails().size() == 2) {
            System.out.println("Student has completed 2 injections!");
        }
    }
    
    public void deleteInjection() {
        if (this.isEmpty()) {
            System.out.println("There are no injection!");
            return;
        }
        
        ScannerCus sc = new ScannerCus();
        
        MenuHelper menu = new MenuHelper(this.size());
        for (Injection item : this) {
            menu.add(item.toString());
        }
        int choice;
        
        do {
            System.out.println("0 - To cancel delete");
            choice = menu.getChoice();
        } while (choice < 0 && choice > this.size());
        
        if (choice != 0 ) {
            System.out.println("Are you sure to delete this injection?");
            boolean isDelete = sc.getBoolean();
            if (isDelete) {
                this.remove(this.get(choice - 1));
                System.out.println("Delete successfully!");
            } else {
                System.out.println("Cancel deleting!");
            }    
        } else {
            System.out.println("Cancel deleting!");
        }
    }
    
    public Injection searchInjectionByStudentID(String studentID) {
        for (Injection item : this) {
            if (item.getStudentID() == null ? studentID == null : item.getStudentID().equals(studentID)) {
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
				String injectionID = stk.nextToken(",").trim();
                                String studentID = stk.nextToken(",").trim();
                                String vaccineID = stk.nextToken(",").trim();
                                ArrayList<Detail> ds = new ArrayList<Detail>(2);
                                String place;
                                Date date;
                                if (stk.hasMoreTokens()) {
                                    place = stk.nextToken(",").trim();
                                    date = Date.valueOf(stk.nextToken(",").trim());
                                    ds.add(new Detail(place, date));
                                }
                                if (stk.hasMoreTokens()) {
                                    place = stk.nextToken(",").trim();
                                    date = Date.valueOf(stk.nextToken(",").trim());
                                    ds.add(new Detail(place, date));
                                }
                                
				Injection e = new Injection(injectionID, ds, studentID, vaccineID);
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
