package it.alten.project.test;

import static org.junit.Assert.assertEquals;
import it.alten.project.bean.Item;
import it.alten.project.utils.SalesTaxesHelper;
import it.alten.project.vo.ItemListVO;

import org.junit.Test;


/**
 * Test class that checks the validation processes, both of item codes and of prices
 * 
 * @author Prisma
 *
 */
public class TestPurchasesValidation {
	
	private static String PROPERTY_FILE = "/salestaxes.properties";

	
	@Test
	public void testPriceValidation() {
		
		try {
			new Item("PURCHASE1", "book", "12.s9", true, false);
		} catch (Exception e) {
			assertEquals("Error in the starting price '12.s9' of the item 'BOOK' in the purchase PURCHASE1: wrong format", e.getMessage());
		}
		
		try {
			new Item("PURCHASE1", "music CD", "14.9!", true, false);
		} catch (Exception e) {
			assertEquals("Error in the starting price '14.9!' of the item 'MUSIC CD' in the purchase PURCHASE1: wrong format", e.getMessage());
		}
		
		try {
			new Item("PURCHASE1", "chocolate bar", ".", true, false);
		} catch (Exception e) {
			assertEquals("Error in the starting price '.' of the item 'CHOCOLATE BAR' in the purchase PURCHASE1: wrong format", e.getMessage());
		}

	}
	
	
	
	@Test
	public void testItemCodeValidation() {
		
		// create helper class
		SalesTaxesHelper helper = null;
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			ItemListVO vo = helper.getItemListByPurchaseNumber("purchase1.items.test1");
			assertEquals("Error occured in the translation from the code 'PUA' to the item name fro the purchase PURCHASE1", vo.getErrorMessagesList().get(0)); 
			
			vo = helper.getItemListByPurchaseNumber("purchase1.items.test2");
			assertEquals("Error occured in the translation from the code 'P' to the item name fro the purchase PURCHASE1", vo.getErrorMessagesList().get(0)); 
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
