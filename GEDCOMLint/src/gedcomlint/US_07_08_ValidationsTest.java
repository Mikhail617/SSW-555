package gedcomlint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class US_07_08_ValidationsTest {

	@Test
	public void testIsGreater() throws ParseException {
		US_07_08_Validations validations = new US_07_08_Validations();
		assertTrue(validations.isGreater("12 DEC 2019", "12 DEC 2018"));
		assertFalse(validations.isGreater("12 JAN 2016", "12 MAR 2017"));
		assertFalse(validations.isGreater("12 JAN 2016", null));
	}
	
	@Test
	public void hasMarriageDateTest() throws ParseException {
		US_07_08_Validations validations = new US_07_08_Validations();

		Family fam1 = new Family();
		fam1.setMarriageDate(null);
		assertFalse(validations.hasMarriageDate(fam1));
		
		Family fam2 = new Family();
		fam1.setMarriageDate("12 DEC 2017");
		assertTrue(validations.hasMarriageDate(fam1));
	}

	@Test
	public void hasBirthateTest() throws ParseException {
		US_07_08_Validations validations = new US_07_08_Validations();

		Individual indv1 = new Individual();
		indv1.setBirthDate(null);
		assertFalse(validations.hasBirthDate(indv1));

		Individual indv2 = new Individual();
		indv2.setBirthDate("12 DEC 1999");
		assertTrue(validations.hasBirthDate(indv2));
	}
	
	@Test
	public void hasDeathDateTest() throws ParseException {
		US_07_08_Validations validations = new US_07_08_Validations();

		Individual indv1 = new Individual();
		indv1.setDeathDate(null);
		assertFalse(validations.hasDeathDate(indv1));

		Individual indv2 = new Individual();
		indv2.setDeathDate("12 DEC 1999");
		assertTrue(validations.hasDeathDate(indv2));
	}

}
