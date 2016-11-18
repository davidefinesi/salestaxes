package it.alten.project.test;

import static org.junit.Assert.assertEquals;
import it.alten.project.utils.SalesTaxesHelper;

import org.junit.Test;


/**
 * Classe di Test che si occupa di verificare l'esattezza dei processi di validazione
 * sia dei codici degli articoli che dei prezzi così come sono configurati
 * 
 * @author Prisma
 *
 */
public class TestPropertiesFile {
	
	@Test
	public void testValidPropertiesFile1() {
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper("/my_salestaxes.properties");
		} catch (Exception e) {
			assertEquals("File di properties non presente", e.getMessage());
		}

	}
	
	
	@Test
	public void testValidPropertiesFile2() {
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper("");
		} catch (Exception e) {
			assertEquals("File di properties non presente", e.getMessage());
		}

	}

}
