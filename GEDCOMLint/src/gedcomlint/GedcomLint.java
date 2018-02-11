package gedcomlint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;

public class GedcomLint {
	
	private static File inFile;

	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		
		// print command line arguments
        for (String s: args) {
            System.out.println(s);
        }
        // open filename from argument
        String fileName = sc.nextLine();;
        System.out.println(fileName);
        
        
        // print file contents line by line
        
        BufferedReader br = null;

        try {
        	
        	File f = new File(fileName);
        	InputStream out = new FileInputStream(f);

        	String sCurrentLine;
        	

        	br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        	
        	
        	for (String line = br.readLine(); line != null; line = br.readLine()) {
        		System.out.println("--> " + line);  
        		
        		System.out.print("<--  "); 
        		
        		for(int i=0; i<line.length(); i++) {
        			
        			String[] sl = line.split(" ");
        			for (int j = 0 ; j <sl.length ; j++ ) {
        				System.out.print(sl[j]); 
        				System.out.print("|");
        			}
            		
        		}
        		System.out.println(""); 

        	}

        } 

        catch (IOException e) {
        	e.printStackTrace();
        	
        } 

        finally {
        	try {
        		if (br != null)br.close();
        		
        	} catch (IOException ex) {
        		ex.printStackTrace();
        		
        	}
        	
        }
        
	}
	
}