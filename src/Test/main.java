/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Injections.Injection;
import Injections.Injections;

import Students.Students;
import Vaccines.Vaccines;
import helper.MenuHelper;
import helper.ScannerCus;
import helper.Validator;
import java.io.IOException;


/**
 *
 * @author Kaine
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ScannerCus sc = new ScannerCus();
        
        MenuHelper menu = new MenuHelper(6);
        
        Students students = new Students();
        Vaccines vaccines = new Vaccines();
        Injections injections = new Injections();
        
//        List<Injection> injectionsData = injections.ResquestData("injections.dat");
         if (!vaccines.loadFromFile("vaccine.dat")) {
             System.out.println("Load Vaccines file failed");
         };
         
         if (!students.loadFromFile("student.dat")) {
             System.out.println("Load Students file failed");
         };
         
         if (!injections.loadFromFile("injection.dat")) {
             System.out.println("Load Injections file failed");
         };
       
        
        
        Injection e = null;

        menu.add("Show infomation all students have injected");
        menu.add("Add student's vaccine injection information");
        menu.add("Updating information of students' vaccine injection");
        menu.add("Delete student vaccine injection information");
        menu.add("Search for injection information by studentID");
        menu.add("Others - Quit");
        int choice;
        do {
                System.out.println("Welcome to Injections Management - @ 2021 by Pham Vinh Tai");
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        injections.printAllInjections(students, vaccines);
                        break;
                    case 2:
                        injections.addInjection(students, vaccines);
                        break;
                    case 3:
                        injections.updateInjections(sc.getString(Validator.StringType.STRING, 0, 255, "Enter Injection Id to update: "));
                        break;
                    case 4:
                        injections.deleteInjection();
                        break;
                    case 5:
                        e = injections.searchInjectionByStudentID(sc.getString(Validator.StringType.STRING, 0, 255, "Enter Student Id to find: "));
                        if (e != null) {
                            System.out.println(e.toString());
                        } else {
                            System.out.println("Not found!");
                        }
                        break;
                    case 6:
                        break;
                }
        } while (choice != 6);
        
         
         if (!injections.saveToFile("injection.dat")) {
             System.out.println("Save Injections file failed");
         };
    }
    
}
