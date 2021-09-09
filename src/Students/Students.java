/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Students;

import helper.MenuHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 *
 * @author Kaine
 */
public class Students extends ArrayList<Student> {

    public Students(int i) {
        super(i);
    }

    public Students() {
    }

    public Students(Collection<? extends Student> clctn) {
        super(clctn);
    }
    
    public Student searchStudentByID(String ID) {
        for (Student item : this) {
            if (item.getStudentId() == null ? ID == null : item.getStudentId().equals(ID)) {
                return item;
            }
        }
        return null;
    }
    
    public String chooseStudent() {
        MenuHelper menu = new MenuHelper(this.size());
        for (int i = 0; i < this.size(); i++) {
            menu.add(this.get(i).getName());
        }
        
        int choice;
        do {
            System.out.println("Enter number to choose students");
            System.out.println("0 - Exit");
            choice = menu.getChoice();
        } while (choice < 0 && choice > this.size());
        
        if (choice != 0){
            return this.get(choice - 1).getStudentId();
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
				String studentID = stk.nextToken(",").trim();
                                String name = stk.nextToken(",").trim();
				Student e = new Student(studentID, name);
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

  
    
    

