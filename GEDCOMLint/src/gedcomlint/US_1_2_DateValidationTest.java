package gedcomlint;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class US_1_2_DateValidationTest {

	@Test
	public void testGetWordsFromLine() {
		US_1_2_DateValidation validations = new US_1_2_DateValidation();
		assertEquals(3, validations.getWordsFromLine("a b c").length);
		assertEquals(0, validations.getWordsFromLine(null).length);
	}
	
	@Test
	public void testIsValidLine() {
		US_1_2_DateValidation validations = new US_1_2_DateValidation();		
		assertTrue(validations.isValidLine("1 BIRT".split(" ")));
		assertTrue(validations.isValidLine("2 DATE 1 JAN 1966".split(" ")));
		assertFalse(validations.isValidLine("Test".split(" ")));
	}
	
	@Test
	public void testIsSpecialScenario() {
		US_1_2_DateValidation validations = new US_1_2_DateValidation();		
		assertTrue(validations.isSpecialScenario("0 @I1@ INDI".split(" ")));
		assertFalse(validations.isSpecialScenario("2 DATE 1 JAN 1966".split(" ")));
		
	}
	
	@Test
	public void testIsGreaterThanToday() throws ParseException {
		US_1_2_DateValidation validations = new US_1_2_DateValidation();
		assertTrue(validations.isGreaterThanToday("12 DEC 2019"));
		assertFalse(validations.isGreaterThanToday("12 DEC 2017"));
		assertFalse(validations.isGreaterThanToday(null));
	}
	
	@Test
	public void testIsGreater() throws ParseException {
		US_1_2_DateValidation validations = new US_1_2_DateValidation();
		assertTrue(validations.isGreater("12 DEC 2019", "12 DEC 2018"));
		assertFalse(validations.isGreater("12 JAN 2016", "12 MAR 2017"));
		assertFalse(validations.isGreater("12 JAN 2016", null));
	}
	

}
