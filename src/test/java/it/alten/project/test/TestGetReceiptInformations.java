package it.alten.project.test;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.FORMAT_STRING;
import static org.junit.Assert.*;
import it.alten.project.utils.SalesTaxesHelper;
import it.alten.project.vo.ReceiptVO;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Classe di Test che si occupa di verificare l'esattezza dei prezzi finali di ciascun articolo 
 * e dell'ammonatare delle tasse applicate, per ciascun ordine 
 * 
 * @author Prisma
 *
 */
public class TestGetReceiptInformations {
	
	private static String PROPERTY_FILE = "/salestaxes.properties";

	
	@Test
	public void testGetReceiptInformationsPurchase1() {
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			// recupero informazioni sul primo articolo
			ReceiptVO receipt = helper.getReceiptInformations("BOOK", 12.49, true, false);
			Double finalPrice1Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = receipt.getPercentageTotalTax();
			
			double expected = 12.49;
			
			// verifico prezzo finale
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul secondo articolo
			receipt = helper.getReceiptInformations("MUSIC CD", 14.99, false, false);
			Double finalPrice2Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = receipt.getPercentageTotalTax();
			
			expected = 16.49;
			
			// verifico prezzo finale
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul terzo articolo
			receipt = helper.getReceiptInformations("CHOCOLATE BAR", 0.85, true, false);
			Double finalPrice3Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax3 = receipt.getPercentageTotalTax();
			
			expected = 0.85;
			
			// verifico prezzo finale
			assertEquals(finalPrice3Double.toString(), String.valueOf(expected));
			
			
			// eseguo la sommatoria delle tasse per calcolare il totale
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax3.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 1.50;
			
			// verifico ammontare totale delle tasse
			assertEquals((String.format(FORMAT_STRING, expected)).replace(CHAR_COMMA, CHAR_DOT), (String.format(FORMAT_STRING, salesTaxes)).replace(CHAR_COMMA, CHAR_DOT));
			
			
			// eseguo la sommatoria dei prezzi
			Double total = finalPrice1Double + finalPrice2Double + finalPrice3Double;
			
			expected = 29.83;
			
			// verifico ammontare prezzo
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	@Test
	public void testGetReceiptInformationsPurchase2() {
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			// recupero informazioni sul primo articolo
			ReceiptVO receipt = helper.getReceiptInformations("IMPORTED BOX OF CHOCOLATES", 10.00, true, true);
			Double finalPrice1Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = receipt.getPercentageTotalTax();
			
			double expected = 10.50;
			
			// verifico prezzo finale
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul secondo articolo
			receipt = helper.getReceiptInformations("IMPORTED BOTTLE OF PERFUME", 47.50, false, true);
			Double finalPrice2Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = receipt.getPercentageTotalTax();
			
			expected = 54.65;
			
			// verifico prezzo finale
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// eseguo la sommatoria delle tasse per calcolare il totale
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 7.65;
			
			// verifico ammontare totale delle tasse
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, salesTaxes).replace(CHAR_COMMA, CHAR_DOT)));
			
			
			// eseguo la sommatoria dei prezzi
			Double total = finalPrice1Double + finalPrice2Double;
			
			expected = 65.15;
			
			// verifico ammontare prezzo
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	@Test
	public void testGetReceiptInformationsPurchase3() {
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			// recupero informazioni sul primo articolo
			ReceiptVO receipt = helper.getReceiptInformations("IMPORTED BOTTLE OF PERFUME", 27.99, false, true);
			Double finalPrice1Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax1 = receipt.getPercentageTotalTax();
			
			double expected = 32.19;
			
			// verifico prezzo finale
			assertEquals(finalPrice1Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul secondo articolo
			receipt = helper.getReceiptInformations("BOTTLE OF PERFUME", 18.99, false, false);
			Double finalPrice2Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax2 = receipt.getPercentageTotalTax();
			
			expected = 20.89;
			
			// verifico prezzo finale
			assertEquals(finalPrice2Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul terzo articolo
			receipt = helper.getReceiptInformations("PACKET OF HEADACHE PILLS", 9.75, true, false);
			Double finalPrice3Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax3 = receipt.getPercentageTotalTax();
			
			expected = 9.75;
			
			// verifico prezzo finale
			assertEquals(finalPrice3Double.toString(), String.valueOf(expected));
			
			
			// recupero informazioni sul quarto articolo
			receipt = helper.getReceiptInformations("IMPORTED BOX OF CHOCOLATES", 11.25, true, true);
			Double finalPrice4Double = new Double((receipt.getFinalPrice()).replace(CHAR_COMMA, CHAR_DOT));
			String percentageTotalTax4 = receipt.getPercentageTotalTax();
			
			expected = 11.85;
			
			// verifico prezzo finale
			assertEquals(finalPrice4Double.toString(), String.valueOf(expected));
			
			
			// eseguo la sommatoria delle tasse per calcolare il totale
			Double salesTaxes = new Double(percentageTotalTax1.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax2.replace(CHAR_COMMA, CHAR_DOT)) + 
							    new Double(percentageTotalTax3.replace(CHAR_COMMA, CHAR_DOT)) +
							    new Double(percentageTotalTax4.replace(CHAR_COMMA, CHAR_DOT));
			
			expected = 6.70;
			
			// verifico ammontare totale delle tasse
			assertEquals((String.format(FORMAT_STRING, expected)).replace(CHAR_COMMA, CHAR_DOT), (String.format(FORMAT_STRING, salesTaxes)).replace(CHAR_COMMA, CHAR_DOT));
			
			
			// eseguo sommatoria sui prezzi
			Double total = finalPrice1Double + finalPrice2Double + finalPrice3Double + finalPrice4Double;

			expected = 74.68;
			
			// verifico ammontare prezzo
			assertEquals(String.valueOf(expected), (String.format(FORMAT_STRING, total)).replace(CHAR_COMMA, CHAR_DOT));
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
