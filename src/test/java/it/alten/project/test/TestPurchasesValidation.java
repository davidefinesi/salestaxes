package it.alten.project.test;

import static org.junit.Assert.assertEquals;
import it.alten.project.bean.Item;
import it.alten.project.utils.SalesTaxesHelper;
import it.alten.project.vo.ItemListVO;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Classe di Test che si occupa di verificare l'esattezza dei processi di validazione
 * sia dei codici degli articoli che dei prezzi così come sono configurati
 * 
 * @author Prisma
 *
 */
public class TestPurchasesValidation {
	
	private static Logger logger = Logger.getLogger(TestPurchasesValidation.class);

	
	@Test
	public void testPriceValidation() {
		
		logger.info("TEST:: PriceValidationPurchase1...");
		
		try {
			new Item("PURCHASE1", "book", "12.s9", true, false);
		} catch (Exception e) {
			assertEquals("Errore nel prezzo di partenza '12.s9' dell'articolo 'BOOK' dell'ordine PURCHASE1: formato errato", e.getMessage());
		}
		
		try {
			new Item("PURCHASE1", "music CD", "14.9!", true, false);
		} catch (Exception e) {
			assertEquals("Errore nel prezzo di partenza '14.9!' dell'articolo 'MUSIC CD' dell'ordine PURCHASE1: formato errato", e.getMessage());
		}
		
		try {
			new Item("PURCHASE1", "chocolate bar", ".", true, false);
		} catch (Exception e) {
			assertEquals("Errore nel prezzo di partenza '.' dell'articolo 'CHOCOLATE BAR' dell'ordine PURCHASE1: formato errato", e.getMessage());
		}

	}
	
	
	
	@Test
	public void testItemCodeValidation() {
		
		logger.info("TEST:: ItemCodeValidation...");
		
		// istanzio la classe helper
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper();
		} catch (Exception e) {
			logger.error("ERRORE: " + e);
		}
		
		ItemListVO vo = helper.getItemListByPurchaseNumber("purchase1.items.test1");
		assertEquals("Errore di traduzione dal codice 'PUA' nel nome dell'articolo per l'ordine PURCHASE1", vo.getErrorMessagesList().get(0)); 
		
		vo = helper.getItemListByPurchaseNumber("purchase1.items.test2");
		assertEquals("Errore di traduzione dal codice 'P' nel nome dell'articolo per l'ordine PURCHASE1", vo.getErrorMessagesList().get(0)); 

	}

}
