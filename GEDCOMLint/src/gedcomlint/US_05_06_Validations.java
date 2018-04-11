package gedcomlint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class US_05_06_Validations {
	
	public void validateMarriageBeforeDeath(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
		
		for(Family fam : allFamilies) {
			if(hasMarriageDate(fam)) {
				Individual husband = getIndividualFromId(allIndividuals, fam.getHusbandId());
				Individual wife = getIndividualFromId(allIndividuals, fam.getWifeId());
				
				if( hasDeathDate(husband) ) {
					if ( isGreater(fam.getMarriageDate(), husband.getDeathDate()) ) {
		    			System.out.println("ERROR: FAMILY: US05: " + fam.id + ": Marriage date " + fam.getMarriageDate() + " occurs after the death date of husband ( "+ husband.getDeathDate() +") having id : " + husband.id + ".");
					}
				}
				
				if( hasDeathDate(wife) ) {
					if ( isGreater(fam.getMarriageDate(), wife.getDeathDate()) ) {
		    			System.out.println("ERROR: FAMILY: US05: " + fam.id + ": Marriage date " + fam.getMarriageDate() + " occurs after the death date of wife ( "+ wife.getDeathDate() +") having id : " + wife.id + ".");
					}
				}
				
			}
		}
	}


	public void validateDivorceBeforeDeath(List<Individual> allIndividuals, List<Family> allFamilies) throws ParseException {
		for(Family fam : allFamilies) {
			if(hasDivorceDate(fam)) {
				Individual husband = getIndividualFromId(allIndividuals, fam.getHusbandId());
				Individual wife = getIndividualFromId(allIndividuals, fam.getWifeId());
				
				if( hasDeathDate(husband) ) {
					if ( isGreater(fam.getDivorceDate(), husband.getDeathDate()) ) {
		    			System.out.println("ERROR: FAMILY: US05: " + fam.id + ": Divorce date " + fam.getDivorceDate() + " occurs after the death date of husband ( "+ husband.getDeathDate() +") having id : " + husband.id + ".");
					}
				}
				
				if( hasDeathDate(wife) ) {
					if ( isGreater(fam.getDivorceDate(), wife.getDeathDate()) ) {
		    			System.out.println("ERROR: FAMILY: US05: " + fam.id + ": Marriage date " + fam.getDivorceDate() + " occurs after the death date of wife ( "+ wife.getDeathDate() +") having id : " + wife.id + ".");
					}
				}
				
			}
		}
	}
	
	public boolean hasMarriageDate(Family fam) throws ParseException {
		return !fam.getMarriageDate().equals("NA");
	}


	public boolean hasDivorceDate(Family fam) throws ParseException {
		return !fam.getDivorceDate().equals("NA");
	}

	public boolean hasDeathDate(Individual indv) throws ParseException {
		return !indv.getDeathDate().equals("NA");
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
