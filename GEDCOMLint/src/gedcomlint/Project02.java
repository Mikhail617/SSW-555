package gedcomlint;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import static gedcomlint.GedcomLint.*;

public class Project02 {
	
	public static void parseAndPrintGEDCOMData(File f) {
        BufferedReader br = null;

        try {
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
					
					boolean isSpecialScenario = false;
					if(words.length >= 3 && (words[2].equals("INDI") || words[2].equals("FAM")) ) {
						isSpecialScenario = true;
					}
					
					String level = words[0];
					String tag = "";
					if(!isSpecialScenario) {
						tag = words[1];
					} else {
						tag = words[2];
					}
					
					System.out.print(level + "|" + tag);	
					
					// check if level and tag is valid
					if ( VALID_LEVEL_TAGS.containsKey(level) 
							&& VALID_LEVEL_TAGS.get(level).contains(tag) ) {
						System.out.print("|Y");
					} else {
						System.out.print("|N");
					}
					
					if(words.length > 2) {
						System.out.print("|");
						if(isSpecialScenario) {
							System.out.print(words[1]);
							for(int i=3; i<words.length; i++) {
								System.out.print(" " + words[i]);
							}
						} else {
							for(int i=2; i<words.length; i++) {
								System.out.print(words[i]+ " ");
							}
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