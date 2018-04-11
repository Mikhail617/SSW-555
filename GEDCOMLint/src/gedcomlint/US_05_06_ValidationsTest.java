package gedcomlint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class US_05_06_ValidationsTest {

	@Test
	public void testIsGreater() throws ParseException {
		US_05_06_Validations validations = new US_05_06_Validations();
		assertTrue(validations.isGreater("12 DEC 2019", "12 DEC 2018"));
		assertFalse(validations.isGreater("12 JAN 2016", "12 MAR 2017"));
		assertFalse(validations.isGreater("12 JAN 2016", null));
	}
	
	@Test
	public void hasMarriageDateTest() throws ParseException {
		US_05_06_Validations validations = new US_05_06_Validations();

		Family fam1 = new Family();
		fam1.setMarriageDate(null);
		assertFalse(validations.hasMarriageDate(fam1));
		
		Family fam2 = new Family();
		fam1.setMarriageDate("12 DEC 2017");
		assertTrue(validations.hasMarriageDate(fam1));
	}

	@Test
	public void hasDivorceDateTest() throws ParseException {
		US_05_06_Validations validations = new US_05_06_Validations();

		Family fam1 = new Family();
		fam1.setDivorceDate(null);
		assertFalse(validations.hasDivorceDate(fam1));
		
		Family fam2 = new Family();
		fam2.setDivorceDate("12 DEC 2017");
		assertTrue(validations.hasDivorceDate(fam2));
	}
	
	@Test
	public void hasDeathDateTest() throws ParseException {
		US_05_06_Validations validations = new US_05_06_Validations();

		Individual indv1 = new Individual();
		indv1.setDeathDate(null);
		assertFalse(validations.hasDeathDate(indv1));

		Individual indv2 = new Individual();
		indv2.setDeathDate("12 DEC 1999");
		assertTrue(validations.hasDeathDate(indv2));
	}

}
