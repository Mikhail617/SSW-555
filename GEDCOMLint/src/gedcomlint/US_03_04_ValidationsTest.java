package gedcomlint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class US_03_04_ValidationsTest {

	@Test
	public void testIsGreater() throws ParseException {
		US_03_04_Validations validations = new US_03_04_Validations();
		assertTrue(validations.isGreater("12 DEC 2019", "12 DEC 2018"));
		assertFalse(validations.isGreater("12 JAN 2016", "12 MAR 2017"));
		assertFalse(validations.isGreater("12 JAN 2016", null));
	}
	
	@Test
	public void isBirthAfterDeathTest() throws ParseException {
		US_03_04_Validations validations = new US_03_04_Validations();
		
		Individual indv1 = new Individual();
		indv1.setBirthDate(null);
		indv1.setDeathDate(null);
		assertFalse(validations.isBirthAfterDeath(indv1));
		
		indv1.setBirthDate("12 JAN 1981");
		indv1.setDeathDate("12 JAN 2001");
		assertFalse(validations.isBirthAfterDeath(indv1));
		
		indv1.setBirthDate("12 JAN 2001");
		indv1.setDeathDate("12 JAN 1981");
		assertTrue(validations.isBirthAfterDeath(indv1));
	}

	
	@Test
	public void isMarriageAfterDivorceTest() throws ParseException {
		US_03_04_Validations validations = new US_03_04_Validations();
		
		Family fam1 = new Family();
		fam1.setMarriageDate(null);
		fam1.setDivorceDate(null);
		assertFalse(validations.isMarriageAfterDivorce(fam1));
		
		fam1.setMarriageDate("12 JAN 1981");
		fam1.setDivorceDate("12 JAN 2001");
		assertFalse(validations.isMarriageAfterDivorce(fam1));

		fam1.setMarriageDate("12 JAN 2001");
		fam1.setDivorceDate("12 JAN 1981");
		assertTrue(validations.isMarriageAfterDivorce(fam1));

	}
	

}
