package gedcomlint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GedcomLint {
	
	private static File inFile;

	public static void main(String args[]) {
		// print command line arguments
        for (String s: args) {
            System.out.println(s);
        }
        // open filename from argument
        String filename = args[0];
        System.out.println(filename);
        
        if (0 < args.length) {
            inFile = new File(filename);
        }

        // print file contents line by line
        BufferedReader br = null;

        try {

        	String sCurrentLine;
        	br = new BufferedReader(new FileReader(inFile));

        	while ((sCurrentLine = br.readLine()) != null) {
        		System.out.println(sCurrentLine);
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
