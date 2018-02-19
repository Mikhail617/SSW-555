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

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		
        // open filename from argument
        String fileName = sc.nextLine();;
    	File f = new File(fileName);

//		Project02.parseAndPrintGEDCOMData(f);
		Project03.printINDIAndFAMTables(f);
	}
	
}