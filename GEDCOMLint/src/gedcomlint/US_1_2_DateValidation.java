package gedcomlint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class US_1_2_DateValidation {
	private List<Individual> allIndividuals = new ArrayList<Individual>();
	private List<Family> allFamilies = new ArrayList<Family>();

	public void readIndividualsAndFamilies(File f) {
        BufferedReader br = null;

        try {
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
				String[] words = getWordsFromLine(line);

				// check if the line is valid
				if(isValidLine(words)) {
					
					// obtain level, tag and value from the line
					String level = getLevel(words);
					String tag = getTag(words);
					String value = getValue(words);
					
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
	
	public void validateDateGreaterThanToday() throws ParseException {
		System.out.println("Start : Validating if any date is greater than today");
		
    	// at this stage we have all Individuals and Families 
    	for(Individual currentIndv : allIndividuals) {
    		if(!currentIndv.getBirthDate().equals("NA") && isGreaterThanToday(currentIndv.getBirthDate())) {
    			System.out.println(currentIndv.name + " has invalid birth date.");
    		}
    		
    		if(!currentIndv.getDeathDate().equals("NA") && isGreaterThanToday(currentIndv.getDeathDate())) {
    			System.out.println(currentIndv.name + " has invalid death date.");
    		}
    	}

    	for(Family fam: allFamilies) {
    		if(!fam.getMarriageDate().equals("NA") && isGreaterThanToday(fam.getMarriageDate())) {
    			System.out.println(fam.id + " has invalid marriage date.");
    		}
    	}
    	
    	System.out.println("Complete : Validating if any date is greater than today");
	}

	public void validateBirthBeforeMarriage() throws ParseException {
		System.out.println("Start : Validating if birth date is after marriage date");
    	// at this stage we have all Individuals and Families 
    	for(Individual currentIndv : allIndividuals) {
    		
    		Family famOfPerson = getFamily(currentIndv.getId());
    		if(famOfPerson != null) {
        		if(isGreater(currentIndv.getBirthDate(), famOfPerson.getMarriageDate())) {
        			System.out.println(currentIndv.name + " has invalid birth date or marriage date.");
        		}
    		}
    	}
    	System.out.println("Complete : Validating if birth date is after marriage date");
	}

	private Family getFamily(String id) {
    	for(Family fam: allFamilies) {
    		if( fam.getHusbandId() != null && fam.getHusbandId().equals(id) ) {
    			return fam;
    		}
    		if( fam.getWifeId() != null && fam.getWifeId().equals(id) ) {
    			return fam;
    		}
    	}
    	
    	return null;
	}
	
	public String[] getWordsFromLine(String line) {
		if(line == null)
			return new String[0];
		else
			return line.split(" ");
	}

	public boolean isValidLine(String[] words) {
		return (words != null && words.length >= 2);
	}
	
	public boolean isSpecialScenario(String[] words) {
		if(words.length >= 3 && (words[2].equals("INDI") || words[2].equals("FAM")) ) {
			return true;
		}
		return false;
	}
	
	private String getLevel(String[] words) {
		// obtain level, tag and value from the line
		String level = words[0];
		return level;
	}

	private String getTag(String[] words) {
		String tag = "";
		
		if(!isSpecialScenario(words)) {
			tag = words[1];
		} else {
			tag = words[2];
		}
		return tag;
	}

	private String getValue(String[] words) {
		String value = "";
		
		if(!isSpecialScenario(words)) {
			for(int i=2;i<words.length;i++) {
				value += words[i] + " ";
			}
		} else {
			value = words[1];
			for(int i=3;i<words.length;i++) {
				value += " " +words[i];
			}
		}
		return value;
	}
	
	public boolean isGreaterThanToday(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();

		if(date != null) {
    		Date date1 = sdf.parse(date);
    		if( date1.compareTo(today) > 0 ) {
    			return true;
    		}
		}
		return false;
	}
	
	public boolean isGreater(String date1, String date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

		if(date1 != null && date2 != null) {
    		Date d1 = sdf.parse(date1);
    		Date d2 = sdf.parse(date2);
    		if( d1.compareTo(d2) > 0 ) {
    			return true;
    		}
		}
		return false;
	}
	
	
}


