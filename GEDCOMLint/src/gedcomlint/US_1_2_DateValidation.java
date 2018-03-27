package gedcomlint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class US_1_2_DateValidation {
	
	public void validateDateGreaterThanToday(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
		
    	// at this stage we have all Individuals and Families 
    	for(Individual currentIndv : allIndividuals) {
    		if(!currentIndv.getBirthDate().equals("NA") && isGreaterThanToday(currentIndv.getBirthDate())) {
    			System.out.println("ERROR: INDIVIDUAL: US01: " + currentIndv.id + ": Birthday " + currentIndv.birthDate + " occurs in future.");
    		}
    		
    		if(!currentIndv.getDeathDate().equals("NA") && isGreaterThanToday(currentIndv.getDeathDate())) {
    			System.out.println("ERROR: INDIVIDUAL: US01: " + currentIndv.id + ": Death " + currentIndv.deathDate + " occurs in future.");
    		}
    	}

    	for(Family fam: allFamilies) {
    		if(!fam.getMarriageDate().equals("NA") && isGreaterThanToday(fam.getMarriageDate())) {
    			System.out.println("ERROR: FAMILY: US01: " + fam.id + ": Marriage date " + fam.getMarriageDate() + " occurs in future.");
    		}
    	}
	}

	public void validateBirthBeforeMarriage(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
    	// at this stage we have all Individuals and Families 
    	for(Individual currentIndv : allIndividuals) {
    		
    		Family famOfPerson = getFamily(allFamilies, currentIndv.getId());
    		if(famOfPerson != null) {
        		if(isGreater(currentIndv.getBirthDate(), famOfPerson.getMarriageDate())) {
        			System.out.println("ERROR: INDIVIDUAL: US02: " +currentIndv.id + ": Birthdate " + currentIndv.birthDate + " occurs after marriage date " + famOfPerson.getMarriageDate()+".");
        		}
    		}
    	}
	}

	private Family getFamily(List<Family> allFamilies, String id) {
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


