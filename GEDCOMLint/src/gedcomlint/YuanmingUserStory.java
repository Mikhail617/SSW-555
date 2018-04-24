package gedcomlint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import javax.print.attribute.Size2DSyntax;

public class YuanmingUserStory {

	// Here is 8 User Story:
	// US30 List living married List all living married people in a GEDCOM file
	// US31 List living single List all living people over 30 who have never been
	// married in a GEDCOM file
	// US32 List multiple births List all multiple births in a GEDCOM file
	// US35 List recent births List all people in a GEDCOM file who were born in the
	// last 30 days
	// US36 List recent deaths List all people in a GEDCOM file who died in the last
	// 30 days
	// US37 List recent survivors List all living spouses and descendants of people
	// in a GEDCOM file who died in the last 30 days
	// US38 List upcoming birthdays List all living people in a GEDCOM file whose
	// birthdays occur in the next 30 days
	// US39 List upcoming anniversaries List all living couples in a GEDCOM file
	// whose marriage anniversaries occur in the next 30 days

	public static int getAge(String birthDate) {
		try {

			if (birthDate != null) {
				String birthdayYearString = "";
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i != 4; ++i) {
					String tempString = birthDate.trim();
					builder.insert(0, tempString.charAt(tempString.length() - 1 - i));
				}
				birthdayYearString = builder.toString();

				String currentordeathYearString = "";
				// if(deathDate != null)
				// {
				// builder = new StringBuilder();
				// for(int i = 0; i != 4; ++i)
				// {
				// String tempString = deathDate.trim();
				// builder.insert(0, tempString.charAt(tempString.length() - 1 - i));
				// }
				// currentordeathYearString = builder.toString();
				// }
				// else
				// {
				// Calendar calendar = Calendar.getInstance();
				// calendar.setTime(new Date());
				// currentordeathYearString = Integer.toString(calendar.get(Calendar.YEAR));
				// }
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
		for (Individual currentIndv : tempIndividuals) {
			// Individual currentIndv = tempIndividuals.get(i);

			System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n",
					currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(), currentIndv.getBirthDate(),
					currentIndv.getAge(), currentIndv.isAlive(), currentIndv.getDeathDate(),
					currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
		}
	}
	
	public static void printIndividual(Individual currentIndv) {
		
			System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n",
					currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(), currentIndv.getBirthDate(),
					currentIndv.getAge(), currentIndv.isAlive(), currentIndv.getDeathDate(),
					currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
		
	}
	

	public static void US30(List<Individual> allIndividuals, List<Family> allFamilies) {
		List<Individual> tempIndividuals = new ArrayList<Individual>();
		List<Family> tempFamilies = new ArrayList<Family>();
		ArrayList<String> tempId = new ArrayList<String>();

		for (Family fam : allFamilies) {
			tempId.add(fam.getHusbandId());
			tempId.add(fam.getWifeId());
		}
		// System.out.println(tempId);
//		for (String tId : tempId) {
//			for (Individual indi : allIndividuals) {
//				if (tId.equals(indi.getId())) {
//					if (!indi.isAlive().equals("True")) {
//						tempId.remove(tId);
//					}
//				}
//			}
//		}
		// System.out.println(tempId);
		for (Individual indi : allIndividuals) {
			for (String tId : tempId) {
				if (tId.equals(indi.getId())&&indi.isAlive().equals("True")) {
					tempIndividuals.add(indi);
				}
			}
		}
		earseDuplicate(tempIndividuals);

		// System.out.println(tempIndividuals.size());
		System.out.println("US30	 List living married	List all living married people in a GEDCOM file  test");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "----------",
				"-------------------------", "-------", "------------", "-----", "-------", "------------",
				"--------------------", "--------------------");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "ID", "Name",
				"Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "----------",
				"-------------------------", "-------", "------------", "-----", "-------", "------------",
				"--------------------", "--------------------");
		printIndividuals(tempIndividuals);
		// for(Individual currentIndv:tempIndividuals) {
		//// Individual currentIndv = tempIndividuals.get(i);
		// System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n",
		// currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
		// currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
		// currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(),
		// currentIndv.getSpouseFamilyIdsAsString());
		// }

	}

	// US31 List living single List all living people over 30 who have never been
	// married in a GEDCOM file
	public static void US31(List<Individual> allIndividuals, List<Family> allFamilies) {
		List<Individual> tempIndividuals = new ArrayList<Individual>();
		List<Family> tempFamilies = new ArrayList<Family>();
		ArrayList<String> tempMarriedId = new ArrayList<String>();
		ArrayList<String> tempId = new ArrayList<String>();

		for (Individual indi : allIndividuals) {
			if (Integer.valueOf(indi.getAge()) > 30) {
				tempId.add(indi.getId());
			}
		}

		for (Family fam : allFamilies) {
			tempMarriedId.add(fam.getHusbandId());
			tempMarriedId.add(fam.getWifeId());
		}

		ArrayList<String> tempDel = new ArrayList<String>();

		for (String tId : tempId) {
			for (String tmarriedId : tempMarriedId) {
				if (tId.equals(tmarriedId)) {
					tempDel.add(tId);
				}
			}
		}
		for (String tdId : tempDel) {
			tempId.remove(tdId);
		}

		for (String tId : tempId) {
			for (Individual indi : allIndividuals) {
				if (tId.equals(indi.getId())&&indi.isAlive().equals("True")) {
					tempIndividuals.add(indi);
				}
			}
		}
		earseDuplicate(tempIndividuals);
		
		ArrayList<String> tempremoveId = new ArrayList<String>();
		
		for (Individual indi : tempIndividuals) {
			if(!indi.isAlive().equals("True")) {
				tempremoveId.add(indi.getId());
				tempIndividuals.remove(indi);
			}
		}
		
//		for (Individual indi : tempIndividuals) {
//			for (Individual indi : tempIndividuals) {
//				
//			}
//		}
		
		System.out.println(
				"US31 List living single	List all living people over 30 who have never been married in a GEDCOM file ");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "----------",
				"-------------------------", "-------", "------------", "-----", "-------", "------------",
				"--------------------", "--------------------");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "ID", "Name",
				"Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", "----------",
				"-------------------------", "-------", "------------", "-----", "-------", "------------",
				"--------------------", "--------------------");
		for (int i = 0; i < tempIndividuals.size(); i++) {
			Individual currentIndv = tempIndividuals.get(i);
			System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n",
					currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(), currentIndv.getBirthDate(),
					currentIndv.getAge(), currentIndv.isAlive(), currentIndv.getDeathDate(),
					currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
		}

	}

	// US32 List multiple births List all multiple births in a GEDCOM file
	public static void US32(List<Individual> allIndividuals, List<Family> allFamilies) {

		List<Individual> cloneIndividuals = new ArrayList<Individual>(allIndividuals);
		List<Individual> tempIndividuals = new ArrayList<Individual>();

		for (Individual indi : allIndividuals) {
			for (Individual indiC : cloneIndividuals) {
				if (!indi.equals(indiC)) {
					if (indi.getBirthDate().equals(indiC.getBirthDate())) {
						tempIndividuals.add(indi);
					}
				}
			}
		}

		earseDuplicate(tempIndividuals);
		System.out.println("US32	List multiple births	List all multiple births in a GEDCOM file");
		printIndividuals(tempIndividuals);

	}

	public static int differentDays(String birthDate) {
		Date td = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

		Date bd = null;
		try {
			bd = (Date) dateFormat.parse(birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dateFormat.format(new Date());

		long diff = td.getTime() - bd.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
		return Integer.parseInt(String.valueOf(days));
	}

	// US35 List recent births List all people in a GEDCOM file who were born in the
	// last 30 days
	public static void US35(List<Individual> allIndividuals, List<Family> allFamilies) {

		List<Individual> tempIndividuals = new ArrayList<Individual>();

		for (Individual indi : allIndividuals) {

			if (differentDays(indi.getBirthDate()) < 30) {
				tempIndividuals.add(indi);
			}

		}

		if (tempIndividuals.size() == 0) {
			System.out.println("There are no people in this GEDCOM file who were born in the last 30 days");
		} else {
			System.out.println(
					"US35	List recent births	List all people in a GEDCOM file who were born in the last 30 days");
			printIndividuals(tempIndividuals);
		}

	}

	// US36 List recent deaths List all people in a GEDCOM file who died in the last
	// 30 days
	public static void US36(List<Individual> allIndividuals, List<Family> allFamilies) {

		List<Individual> tempIndividuals = new ArrayList<Individual>();

		for (Individual indi : allIndividuals) {

			if (!indi.getDeathDate().equals("NA")) {
				if (differentDays(indi.getDeathDate()) < 30) {
					tempIndividuals.add(indi);
				}
			}

		}

		if (tempIndividuals.size() == 0) {
			System.out.println("There are no people in this GEDCOM file who died in the last 30 days");
		} else {
			System.out
					.println("US36	List recent deaths	List all people in a GEDCOM file who died in the last 30 days");
			printIndividuals(tempIndividuals);
		}

	}

	// US37 List recent survivors List all living spouses and descendants of people
	// in a GEDCOM file who died in the last 30 days
	
	public static String findSupouse(String temid,List<String> tempspouseFamilyIds,List<Family> allFamilies) {
		for(String id:tempspouseFamilyIds) {
			for(Family fam:allFamilies) {
				if(id.equals(fam.getId())) {
					if(fam.getHusbandId().equals(temid)) {
						return fam.getWifeId();
					}else {
						return fam.getHusbandId();
					}
				}
			}
		}
		return null;
	}
	
	
	public static void US37(List<Individual> allIndividuals, List<Family> allFamilies) {

		List<Individual> tempIndividuals = new ArrayList<Individual>();
		List<Individual> tempDescendants = new ArrayList<Individual>();
		List<String> tempDescendantId = new ArrayList<String>();
		for (Individual indi : allIndividuals) {

			if (!indi.getDeathDate().equals("NA")) {
				if (differentDays(indi.getDeathDate()) < 30) {
					tempIndividuals.add(indi);
				}
			}

		}

		if (tempIndividuals.size() == 0) {
			System.out.println(
					"There are no people in this GEDCOM file who died in the last 30 days, so cannot list all living spouses and descendants of people in a GEDCOM file who died in the last 30 days");
		} else {
			for (Individual indi : tempIndividuals) {
				printIndividual(indi);
				System.out.println("Here is this individual's spouses and descendants:");
				String temid  = indi.getId();
				List<String> tempspouseFamilyIds = indi.getSpouseFamilyIds();
				for(String descid:tempspouseFamilyIds) {
					tempDescendantId.add(descid);
				}
				String supid =findSupouse(temid,tempspouseFamilyIds,allFamilies);
				List<String> tempchildFamilyIds = indi.getChildFamilyIds();
				for (Individual indi2 : allIndividuals) {
					if(temid.equals(indi2.getId())) {
						printIndividual(indi2);
					}
				}
			}
			for(Individual indi3:allIndividuals) {
				for(String descid:tempDescendantId) {
					if(descid.equals(indi3.getId())) {
						tempDescendants.add(indi3);
					}
				}
			}
			printIndividuals(tempDescendants);
		}

	}

}
