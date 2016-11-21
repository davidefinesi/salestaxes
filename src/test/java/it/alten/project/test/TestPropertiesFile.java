package it.alten.project.test;

import static it.alten.project.utils.Constants.EMPTY_STRING;
import static org.junit.Assert.assertEquals;
import it.alten.project.utils.SalesTaxesHelper;

import org.junit.Test;


/**
 * Classe di Test che si occupa di verificare l'esattezza dei processi di validazione
 * sia dei codici degli articoli che dei prezzi cos� come sono configurati
 * 
 * @author Prisma
 *
 */
public class TestPropertiesFile {
	
	@Test
	public void testValidPropertiesFile1() {
		
		try {
			// istanzio la classe helper
			new SalesTaxesHelper("/my_salestaxes.properties");
		} catch (Exception e) {
			assertEquals("Properties file not present", e.getMessage());
		}

	}
	
	
	@Test
	public void testValidPropertiesFile2() {
		
		try {
			// istanzio la classe helper
			new SalesTaxesHelper(EMPTY_STRING);
		} catch (Exception e) {
			assertEquals("Properties file not present", e.getMessage());
		}

	}

}
