package gedcomlint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;

public class GedcomLint {
	
	private static File inFile;
	
	private static HashMap<String, Set<String>> VALID_LEVEL_TAGS = new HashMap<String, Set<String>>();
	
	static {
		HashSet<String> values = new HashSet<String>();
		values.add("INDI");
		values.add("FAM");
		values.add("HEAD");
		values.add("TRLR");
		values.add("NOTE");
		VALID_LEVEL_TAGS.put("0", values);
		
		values = new HashSet<String>();
		values.add("NAME");
		values.add("SEX");
		values.add("BIRT");
		values.add("DEAT");
		values.add("FAMC");
		values.add("FAMS");
		values.add("DATE");
		values.add("MARR");
		values.add("HUSB");
		values.add("WIFE");
		values.add("CHIL");
		values.add("DIV");
		VALID_LEVEL_TAGS.put("1", values);
		
		values = new HashSet<String>();
		values.add("DATE");
		VALID_LEVEL_TAGS.put("2", values);
	}

	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		
        // open filename from argument
        String fileName = sc.nextLine();;
        
        // print file contents line by line
        BufferedReader br = null;
        try {
        	File f = new File(fileName);
        	br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        	
        	// read each line from the file
        	for (String line = br.readLine(); line != null; line = br.readLine()) {
        		// Print the input line 
        		System.out.println("--> " + line);
        		
        		// Print the output line
        		System.out.print("<-- "); 
        		// Split the line into words	
    			String[] words = line.split(" ");
    			
    			// check if the line is valid
    			if(words != null && words.length >= 2) {
    				
    				System.out.print(words[0] + "|" + words[1]);
    				
    				// check if level and tag is valid
    				if ( VALID_LEVEL_TAGS.containsKey(words[0]) 
    						&& VALID_LEVEL_TAGS.get(words[0]).contains(words[1]) ) {
    					System.out.print("|Y");
    				} else {
    					System.out.print("|N");
    				}
    				
    				if(words.length > 2) {
    					System.out.print("|");
    					for(int i=2; i<words.length; i++) {
    						System.out.print(words[i]+ " ");
    					}
    				}
    			}
    			System.out.println("");
        	}

        }  catch (IOException e) {
        	//e.printStackTrace();
        	System.out.println("Look like you passed incorrect file name");
        }  finally {
        	try {
        		if (br != null)br.close();
        		
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        }
	}
	
}