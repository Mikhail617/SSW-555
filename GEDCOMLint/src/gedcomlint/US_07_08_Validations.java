package gedcomlint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class US_07_08_Validations {
	
	public void validate150YearOld(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
		
		for(Individual indv : allIndividuals) {
			if(hasBirthDate(indv) && hasDeathDate(indv)) {
				if( isAgeGreaterThan150(indv.getBirthDate(), indv.getDeathDate()) ) {
	    			System.out.println("ERROR: Individual: US07: " + indv.id + " has age more than 150 years.");
				}
			}
		}
	}

	public void validateBirthBeforeParentsMarriage(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
		for(Individual indv : allIndividuals) {
			if(hasBirthDate(indv)) {
				Family family = getFamily(indv, allFamilies);
				if(family != null && hasMarriageDate(family) && isGreater(family.getMarriageDate(), indv.getBirthDate())) {
	    			System.out.println("ERROR: Individual: US08: " + indv.id + " has birth date before his/her parents marriage date.");
				}
			}
		}
	}
	
	public Family getFamily(Individual indv, List<Family> allFamilies) {
		String indvId = indv.id;
		
		for(Family family : allFamilies) {
			if(family.childrenId.contains(indvId)) {
				return family;
			}
		}
		
		return null;
	}
	
	
	public boolean hasBirthDate(Individual indv) throws ParseException {
		return !indv.getBirthDate().equals("NA");
	}

	public boolean hasMarriageDate(Family fam) throws ParseException {
		return !fam.getMarriageDate().equals("NA");
	}

	public boolean hasDeathDate(Individual indv) throws ParseException {
		return !indv.getDeathDate().equals("NA");
	}

	public boolean isAgeGreaterThan150(String birthDate, String deathDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

		if(birthDate != null && deathDate != null) {
    		Date bd = sdf.parse(birthDate);
    		Date dd = sdf.parse(deathDate);
    		
    		if(dd.getYear() - bd.getYear() >= 150 ) {
    			return true;
    		}
		}
		return false;
		
	}

	public Individual getIndividualFromId(List<Individual> allIndividuals, String id) {
		if(id == null)
			return null;
		
		for(Individual indv : allIndividuals) {
			if(indv.getId().equals(id)) {
				return indv;
			}
		}
		return null;
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
