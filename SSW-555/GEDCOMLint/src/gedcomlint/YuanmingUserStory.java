package gedcomlint;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import javax.print.attribute.Size2DSyntax;

public class YuanmingUserStory {
	
//	Here is 8 User Story:
//	US30	List living married	List all living married people in a GEDCOM file
//	US31	List living single	List all living people over 30 who have never been married in a GEDCOM file
//	US32	List multiple births	List all multiple births in a GEDCOM file
//	US35	List recent births	List all people in a GEDCOM file who were born in the last 30 days
//	US36	List recent deaths	List all people in a GEDCOM file who died in the last 30 days
//	US37	List recent survivors	List all living spouses and descendants of people in a GEDCOM file who died in the last 30 days
//	US38	List upcoming birthdays	List all living people in a GEDCOM file whose birthdays occur in the next 30 days
//	US39	List upcoming anniversaries	List all living couples in a GEDCOM file whose marriage anniversaries occur in the next 30 days
	
	
	

	public static int getAge(String birthDate) {
		try {
						
			if(birthDate != null) 
			{				
				String birthdayYearString = "";
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i != 4; ++i)
				{					
					String tempString = birthDate.trim();					
					builder.insert(0, tempString.charAt(tempString.length() - 1 - i));
				}
				birthdayYearString = builder.toString();
				
				String currentordeathYearString = "";				
//				if(deathDate != null) 
//				{			
//					builder = new StringBuilder();
//					for(int i = 0; i != 4; ++i)
//					{
//						String tempString = deathDate.trim();
//						builder.insert(0, tempString.charAt(tempString.length() - 1 - i));
//					}
//					currentordeathYearString = builder.toString();
//				}
//				else
//				{
//					Calendar calendar = Calendar.getInstance();
//					calendar.setTime(new Date());
//					currentordeathYearString = Integer.toString(calendar.get(Calendar.YEAR));
//				}
				int birthYear = 0, currentordeathYear = 0;
				birthYear = Integer.valueOf(birthdayYearString);
				currentordeathYear = Integer.valueOf(currentordeathYearString);
				return currentordeathYear - birthYear;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<Individual> earseDuplicate(List<Individual> tempIndividuals) {
		
		for (int i = 0; i < tempIndividuals.size() - 1; i++) {
			for (int j = tempIndividuals.size() - 1; j > i; j--) {
				if (tempIndividuals.get(j).equals(tempIndividuals.get(i))) {
					tempIndividuals.remove(j);
				}
			}
		}
		return tempIndividuals;
	}
	
	public static void printIndividuals(List<Individual> tempIndividuals) {
		for(Individual currentIndv:tempIndividuals) {
//    		Individual currentIndv = tempIndividuals.get(i);
    		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
    				currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
    				currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
    				currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
		}
	}
	
	
	public static void US30(List<Individual> allIndividuals, List<Family> allFamilies) {
		List<Individual> tempIndividuals = new ArrayList<Individual>();
		List<Family> tempFamilies = new ArrayList<Family>();
		ArrayList<String> tempId = new ArrayList<String>();
		
		for(Family fam: allFamilies) {
			tempId.add(fam.getHusbandId());
			tempId.add(fam.getWifeId());
		}
//		System.out.println(tempId);
		for(String tId:tempId) {
			for(Individual indi:allIndividuals) {
				if(tId.equals(indi.getId())) {
					if(indi.isAlive().equals("false")) {
						tempId.remove(tId);
					}
				}
			}
		}
		//System.out.println(tempId);
		for(Individual indi:allIndividuals) {
			for(String tId:tempId){
				if(tId.equals(indi.getId())) {
					tempIndividuals.add(indi);
				}
			}
		}
		earseDuplicate(tempIndividuals);
		
		
//		System.out.println(tempIndividuals.size());
		System.out.println("US30	 List living married	List all living married people in a GEDCOM file");
		
		printIndividuals(tempIndividuals);
//		for(Individual currentIndv:tempIndividuals) {
////    		Individual currentIndv = tempIndividuals.get(i);
//    		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    				currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
//    				currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
//    				currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
//		}
		
	}
	
	
	public static void US31(List<Individual> allIndividuals, List<Family> allFamilies) {
		List<Individual> tempIndividuals = new ArrayList<Individual>();
		List<Family> tempFamilies = new ArrayList<Family>();
		ArrayList<String> tempMarriedId = new ArrayList<String>();
		ArrayList<String> tempId = new ArrayList<String>();
		
		for(Individual indi:allIndividuals) {
			if(Integer.valueOf(indi.getAge())>30) {
				tempId.add(indi.getId());
			}
		}
	
		for(Family fam: allFamilies) {
			tempMarriedId.add(fam.getHusbandId());
			tempMarriedId.add(fam.getWifeId());
		}
		
		ArrayList<String> tempDel = new ArrayList<String>();
		
		for(String tId:tempId) {
			for(String tmarriedId:tempMarriedId) {
				if(tId.equals(tmarriedId)) {
					tempDel.add(tId);
				}
			}
		}
		for(String tdId:tempDel) {
			tempId.remove(tdId);
		}
		
		
		for(String tId:tempId) {
			for(Individual indi:allIndividuals) {
				if(tId.equals(indi.getId())) {
					tempIndividuals.add(indi);
				}
			}
		}
		earseDuplicate(tempIndividuals);
		System.out.println("US31 List living single	List all living people over 30 who have never been married in a GEDCOM file");
		for(int i=0;i<tempIndividuals.size();i++) {
    		Individual currentIndv = tempIndividuals.get(i);
    		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
    				currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
    				currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
    				currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
   		}
		
		
	}
	

	
}
