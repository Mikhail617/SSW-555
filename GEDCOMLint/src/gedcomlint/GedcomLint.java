package gedcomlint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;

public class GedcomLint {
	
	public static HashMap<String, Set<String>> VALID_LEVEL_TAGS = new HashMap<String, Set<String>>();
	
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

	public static void main(String args[]) throws ParseException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
	
        // open filename from argument
        String fileName = sc.nextLine();;
    	File f = new File(fileName);
    	
    	// Implementation/Execution related to Project 2 and 3
		//Project02.parseAndPrintGEDCOMData(f);
		Project03.printINDIAndFAMTables(f);
    	
    	// Execution of user story 1 and 2
		ReadFile file = new ReadFile(f);
		
    	US_1_2_DateValidation validations = new US_1_2_DateValidation();
    	validations.validateDateGreaterThanToday(file.getIndividuals(), file.getFamilies());
    	validations.validateBirthBeforeMarriage(file.getIndividuals(), file.getFamilies());
    	
    	US_03_04_Validations validations2 = new US_03_04_Validations();
    	validations2.validateBirthBeforeDeath(file.getIndividuals(), file.getFamilies());
    	validations2.validateMarriageBeforeDivorce(file.getIndividuals(), file.getFamilies());
    	
    	
    	// Execution of other user stories
    	// Execution of user story 30 and 31
    	YuanmingUserStory.US30(file.getIndividuals(), file.getFamilies());
    	YuanmingUserStory.US31(file.getIndividuals(), file.getFamilies());
	YuanmingUserStory.US32(file.getIndividuals(), file.getFamilies());
    	YuanmingUserStory.US35(file.getIndividuals(), file.getFamilies());
    	// User stories 9 and 10
    	try {
			US_09_10.parseGedcomFile(f);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
