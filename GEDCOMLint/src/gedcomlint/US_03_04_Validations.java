package gedcomlint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class US_03_04_Validations {
	
	public void validateBirthBeforeDeath(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
    	for(Individual currentIndv : allIndividuals) {
    		if( isBirthAfterDeath(currentIndv) ) {
    			System.out.println("ERROR: INDIVIDUAL: US03: " + currentIndv.id + ": Birth date " + currentIndv.birthDate + " occurs before death date "+ currentIndv.deathDate +".");
    		}
    	}
	}
	
	public boolean isBirthAfterDeath(Individual indv) throws ParseException {
		return !indv.getBirthDate().equals("NA") && 
				!indv.getDeathDate().equals("NA") && 
				isGreater(indv.getBirthDate(), indv.getDeathDate());
	}
	
	public void validateMarriageBeforeDivorce(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
    	for(Family fam: allFamilies) {
    		if(isMarriageAfterDivorce(fam)) {
    			System.out.println("ERROR: FAMILY: US01: " + fam.id + ": Marriage date " + fam.getMarriageDate() + " occurs after the divorce date " + fam.getDivorceDate() + ".");
    		}
    	}
	}

	public boolean isMarriageAfterDivorce(Family fam) throws ParseException {
		return !fam.getMarriageDate().equals("NA") &&
				!fam.getDivorceDate().equals("NA") && 
				isGreater(fam.getMarriageDate(), fam.getDivorceDate());
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
