package gedcomlint;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gedcomlint.Family;
import gedcomlint.Individual;
import gedcomlint.ReadFile;
import gedcomlint.YuanmingUserStory;

public class YuanmingTest {

	public static File f = new File("Project-01.ged");
	
//	@Test
//	public void testReadfileIndividuals() {
//		
//		List<Individual> allIndividuals = ReadFile.getIndividuals(f);
//		
//	 	System.out.println("Individuals");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    			
//	 	for(int i=0;i<allIndividuals.size();i++) {
//	 		Individual currentIndv = allIndividuals.get(i);
//	 		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    				currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
//    				currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
//    				currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
//	 	}
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    	
//	}
//	
//	@Test
//	public void testReadFamilies() {
//		
//		List<Family> allFamilies = ReadFile.getFamilies(f);
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    	
//	 	System.out.println("Families");
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//    	
//    	
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//	 	for(Family fam: allFamilies)
//	 		System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-10s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    				fam.getId(), fam.getMarriageDate() == null ? "NA" : fam.getMarriageDate(), fam.getDivorceDate() == null ? "NA" : fam.getDivorceDate(), fam.getHusbandId(),
//    								fam.getHusbandName(),fam.getWifeId(), fam.getWifeName(),fam.getChildrenIdAsString());
//	 		System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-10s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//    							
//		
//	}
//	
//	@Test
//	public void testReadTogether() {
//		List<Individual> allIndividuals = ReadFile.getIndividuals(f);
//		
//	 	System.out.println("Individuals");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"ID", "Name", "Gender", "Birthday", "Age", "Alive", "Death", "Child", "Spouse");
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    			
//	 	for(int i=0;i<allIndividuals.size();i++) {
//	 		Individual currentIndv = allIndividuals.get(i);
//	 		System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    				currentIndv.getId(), currentIndv.getName(), currentIndv.getGender(),
//    				currentIndv.getBirthDate(), currentIndv.getAge(), currentIndv.isAlive(),
//    				currentIndv.getDeathDate(), currentIndv.getChildFamilyIdsAsString(), currentIndv.getSpouseFamilyIdsAsString());
//	 	}
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    	
//	 	List<Family> allFamilies = ReadFile.getFamilies(f);
//	 	System.out.format("|%1$-10s|%2$-25s|%3$-7s|%4$-12s|%5$-5s|%6$-7s|%7$-12s|%8$-20s|%9$-20s|\n", 
//    			"----------", "-------------------------", "-------", "------------", "-----", "-------", "------------", "--------------------", "--------------------");
//    	
//	 	System.out.println("Families");
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//    	
//    	
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"ID", "Married", "Divorced", "Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
//	 	System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-5s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//	 	for(Family fam: allFamilies)
//	 		System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-10s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    				fam.getId(), fam.getMarriageDate() == null ? "NA" : fam.getMarriageDate(), fam.getDivorceDate() == null ? "NA" : fam.getDivorceDate(), fam.getHusbandId(),
//    								fam.getHusbandName(),fam.getWifeId(), fam.getWifeName(),fam.getChildrenIdAsString());
//	 		System.out.format("|%1$-10s|%2$-12s|%3$-12s|%4$-10s|%5$-25s|%6$-10s|%7$-25s|%8$-20s|\n", 
//    			"----------", "------------", "------------", "----------", "-------------------------", "----------", "-------------------------", "--------------------");
//    		
//	}
//	
	@Test
	public void testU30() {
		ReadFile file = new ReadFile(f);
		List<Individual> allIndividuals = file.getIndividuals();
		List<Family> allFamilies = file.getFamilies();
	
		YuanmingUserStory.US30(allIndividuals, allFamilies);
	}
	
	@Test
	public void testU31() {
		ReadFile file = new ReadFile(f);
		List<Individual> allIndividuals = file.getIndividuals();
		List<Family> allFamilies = file.getFamilies();
	
		YuanmingUserStory.US31(allIndividuals, allFamilies);
	}
	

}
