package it.alten.project.test;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.FORMAT_STRING;
import static org.junit.Assert.assertEquals;
import it.alten.project.bean.Item;

import org.junit.Test;


/**
 * Test class that check if the final price of each item and the total amount of the taxes are right, for every purchase
 * 
 * @author Prisma
 *
 */
public class TestGetReceiptInformations {
	
	
	@Test
	public void testGetReceiptInformationsPurchase1() {
		
		try {
		
			// get first item informations
			Item item1 = new Item("1", "BOOK", String.valueOf(12.49), true, false);
			Double finalPrice1Double = new Double((item1.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = item1.getPercentageTotalTax();
			
			double expected = 12.49;
			
			// check the final price
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// get second item informations
			Item item2 = new Item("1", "MUSIC CD", String.valueOf(14.99), false, false);
			Double finalPrice2Double = new Double((item2.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = item2.getPercentageTotalTax();
			
			expected = 16.49;
			
			// check the final price
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// get third item informations
			Item item3 = new Item("1", "CHOCOLATE BAR", String.valueOf(0.85), true, false);
			Double finalPrice3Double = new Double((item3.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax3 = item3.getPercentageTotalTax();
			
			expected = 0.85;
			
			// check the final price
			assertEquals(finalPrice3Double.toString(), String.valueOf(expected));
			
			
			// do the sum of the taxes
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax3.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 1.50;
			
			// check the total amount of the taxes
			assertEquals((String.format(FORMAT_STRING, expected)).replace(CHAR_COMMA, CHAR_DOT), (String.format(FORMAT_STRING, salesTaxes)).replace(CHAR_COMMA, CHAR_DOT));
			
			
			// do the sum of the prices
			Double total = finalPrice1Double + finalPrice2Double + finalPrice3Double;
			
			expected = 29.83;
			
			// check the amount of the prices
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	@Test
	public void testGetReceiptInformationsPurchase2() {
		
		try {
		
			// get first item informations
			Item item1 = new Item("2", "IMPORTED BOX OF CHOCOLATES", String.valueOf(10.00), true, true);
			Double finalPrice1Double = new Double((item1.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = item1.getPercentageTotalTax();
			
			double expected = 10.50;
			
			// check the final price
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// get second item informations
			Item item2 = new Item("2", "IMPORTED BOTTLE OF PERFUME", String.valueOf(47.50), false, true);
			Double finalPrice2Double = new Double((item2.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = item2.getPercentageTotalTax();
			
			expected = 54.65;
			
			// check the final price
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// do the sum of the taxes
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 7.65;
			
			// check the total amount of the taxes
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, salesTaxes).replace(CHAR_COMMA, CHAR_DOT)));
			
			
			// do the sum of the prices
			Double total = finalPrice1Double + finalPrice2Double;
			
			expected = 65.15;
			
			// check the amount of the price
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	@Test
	public void testGetReceiptInformationsPurchase3() {
		
		try {
		
			// get first item informations
			Item item1 = new Item("3", "IMPORTED BOTTLE OF PERFUME", String.valueOf(27.99), false, true);
			Double finalPrice1Double = new Double((item1.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = item1.getPercentageTotalTax();
			
			double expected = 32.19;
			
			// check the final price
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// get second item informations
			Item item2 = new Item("3", "BOTTLE OF PERFUME", String.valueOf(18.99), false, false);
			Double finalPrice2Double = new Double((item2.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = item2.getPercentageTotalTax();
			
			expected = 20.89;
			
			// check the final price
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// get third item informations
			Item item3 = new Item("3", "PACKET OF HEADACHE PILLS", String.valueOf(9.75), true, false);
			Double finalPrice3Double = new Double((item3.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax3 = item3.getPercentageTotalTax();
			
			expected = 9.75;
			
			// check the final price
			assertEquals(finalPrice3Double.toString(), String.valueOf(expected));
			
			
			// get 4th item informations
			Item item4 = new Item("3", "IMPORTED BOX OF CHOCOLATES", String.valueOf(11.25), true, true);
			Double finalPrice4Double = new Double((item4.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax4 = item4.getPercentageTotalTax();
			
			expected = 11.85;
			
			// check the final price
			assertEquals(finalPrice4Double.toString(), String.valueOf(expected));
			
			
			// do the sum of the taxes
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax3.replace(CHAR_COMMA, CHAR_DOT)) +
							    new Double(percentageTotalTax4.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 6.70;
			
			// check the total amount of the taxes
			assertEquals((String.format(FORMAT_STRING, expected)).replace(CHAR_COMMA, CHAR_DOT), (String.format(FORMAT_STRING, salesTaxes)).replace(CHAR_COMMA, CHAR_DOT));
			
			
			// do the sum of the prices
			Double total = finalPrice1Double + finalPrice2Double + finalPrice3Double + finalPrice4Double;

			expected = 74.68;
			
			// check the amount of the price
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
