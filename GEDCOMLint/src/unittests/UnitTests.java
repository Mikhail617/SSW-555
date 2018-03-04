package unittests;

import org.junit.Test;

import gedcomlint.Family;
import gedcomlint.Individual;
import gedcomlint.US_09_10;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UnitTests {
	
	private static List<Individual> allIndividuals = new ArrayList<Individual>();
	private static List<Family> allFamilies = new ArrayList<Family>();
	
	   @Test
	   public void testSetup() {
	        BufferedReader br = null;

	        try {
	        	
	        	File f = new File("unit_test.ged");
		    	br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		    	
		    	Individual indv = null;
		    	Family currentFamily = null;
		    	boolean readingBirthDate = false;
		    	boolean readingDeathDate = false;
		    	boolean readingMarriageDate = false;
		    	boolean readingDivorceDate = false;
		    	// read each line from the file
		    	for (String line = br.readLine(); line != null; line = br.readLine()) {

					// Split the line into words	
					String[] words = line.split(" ");

					// check if the line is valid
					if(words != null && words.length >= 2) {
						
						boolean isSpecialScenario = false;
						if(words.length >= 3 && (words[2].equals("INDI") || words[2].equals("FAM")) ) {
							isSpecialScenario = true;
						}
						
						// obtain level, tag and value from the line
						String level = words[0];
						String tag = "";
						String value = "";
						
						if(!isSpecialScenario) {
							tag = words[1];
							for(int i=2;i<words.length;i++) {
								value += words[i] + " ";
							}
						} else {
							tag = words[2];
							value = words[1];
							for(int i=3;i<words.length;i++) {
								value += " " +words[i];
							}
						}
						
						// Check if we encounter INDI record, 
						if(tag.equals("INDI")) {
							indv = new Individual();
							indv.setId(value);
							
							allIndividuals.add(indv);
						}
						
						if(tag.equals("NAME") && indv != null) {
							indv.setName(value);
						}
						if(tag.equals("SEX")) {
							indv.setGender(value);
						}
						
						if(tag.equals("BIRT")) {
							readingBirthDate = true;
							continue;
						}
						if(tag.equals("DATE") && readingBirthDate) {
							readingBirthDate = false;
							indv.setBirthDate(value);
						}
						
						if(tag.equals("MARR")) {
							readingMarriageDate = true;
							continue;
						}
						if(tag.equals("DATE") && readingMarriageDate) {
							readingMarriageDate = false;
							currentFamily.setMarriageDate(value);
						}

						if(tag.equals("DIV")) {
							readingDivorceDate = true;
							continue;
						}
						if(tag.equals("DATE") && readingDivorceDate) {
							readingDivorceDate = false;
							currentFamily.setDivorceDate(value);
						}

						if(tag.equals("DEAT")) {
							readingDeathDate = true;
							continue;
						}
						if(tag.equals("DATE") && readingDeathDate) {
							readingDeathDate = false;
							indv.setDeathDate(value);
						}
						
						if(tag.equals("FAM")) {
							currentFamily = null;
							for (Family family:allFamilies) {
								if(family.getId().trim().equals((value.replaceAll("@", "")).trim()))
									currentFamily = family;
							}
							if(currentFamily == null) {
								currentFamily = new Family();
								currentFamily.setId(value.replaceAll("@", ""));
								allFamilies.add(currentFamily);
							}
						}
						
						if(tag.equals("FAMS")) {
							indv.getSpouseFamilyIds().add(value);
							
							// Let's add the spouse to family table
							Family fam = null;
							for (Family family:allFamilies) {
								if(family.getId().equals(value.replaceAll("@", "")))
									fam = family;
							}
							if(fam == null) {
								fam = new Family();
								fam.setId(value.replaceAll("@", ""));
								allFamilies.add(fam);
							}
							if(indv.getGender().trim().equals("M")) {
								fam.setHusbandName(indv.getName());
								fam.setHusbandId(indv.getId());
							} else {
								fam.setWifeName(indv.getName());
								fam.setWifeId(indv.getId());
							}
						}
						
						if(tag.equals("FAMC")) {
							indv.getChildFamilyIds().add(value);
							
							// Let's add the children IDs to the family table
							Family fam = null;
							for (Family family:allFamilies) {
								if(family.getId().equals(value.replaceAll("@", "")))
									fam = family;
							}
							if(fam == null) {
								fam = new Family();
								fam.setId(value.replaceAll("@", ""));
								allFamilies.add(fam);
							}
							if(fam.getChildrenId() == null) {
								List<String> childrenIds = new ArrayList<String>();
								fam.setChildrenId(childrenIds);
							} else {
								if(!fam.getChildrenId().contains(value))
									fam.getChildrenId().add(value);
							}
						}
						
					}
					
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
	   
	   @Test
	   public void testBirthAfterMotherDeath() {
		   String msg = "ERROR: Mother's death date cannot be before child's birthdate.";
		   String[] result = null;
		   try {
			result = US_09_10.checkBirthsBeforeMothersDeaths(allIndividuals, allFamilies);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   assertEquals(msg, result[0]);
	   }
	   
	   @Test
	   public void testBirthMoreThanNineMonthsAfterFatherDeath() {
		   String msg = "ERROR: Father's death date cannot be more than 9 months before child's birthdate.";
		   String[] result = null;
		   try {
			result = US_09_10.checkBirthsBeforeMothersDeaths(allIndividuals, allFamilies);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   assertEquals(msg, result[1]);		   
	   }
	   
	   /*@Test
	   public void testBirthLessThanNineMonthsAfterFatherDeath() {
		   String msg = "WARNING: Child born after father's death";
		   String[] result = null;
		   try {
			result = US09.checkBirthsBeforeMothersDeaths(allIndividuals, allFamilies);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   assertEquals(msg, result[2]);			   
	   }*/
	   
	   @Test
	   public void testNormalCase() {
		   
	   }
}
