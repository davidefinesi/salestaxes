package it.alten.project.facade;

import it.alten.project.utils.SalesTaxesHelper;
import it.alten.project.utils.SalesTaxesUtility;
import it.alten.project.vo.ItemListVO;


/**
 * Classe Facade nata dalla necessità di disaccoppiare lo strato del servizio REST da quello di "business" e
 * nel contempo semplificare con un unico punto di accesso per il servizio tutta la logica di business stessa
 * 
 * @author Prisma
 *
 */
public class SalesTaxesFacade {
	
	private static String PROPERTY_FILE = "/salestaxes.properties";
	private static SalesTaxesHelper helper;
	
	
	/*
	 * Metodo di business che restituisce la stringa (html) rappresentante la "ricevuta" dell'ordine effettuato
	 */
	public static String getPurchaseReceipt(String purchaseId){
		
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			// recupero la lista degli articoli
			ItemListVO itemList1VO = helper.getItemListByPurchaseNumber("purchase" + purchaseId + ".items");
			
			// eseguo la stampa della ricevuta
			return helper.getStringReceipt(itemList1VO.getItemList(), purchaseId);
		
		} catch (Exception e) {
			return "ERRORE: " + e;
		}
	}
	
	
	/*
	 * Metodo di business che restituisce la stringa (html) rappresentante la "ricevuta" dell'ordine completo (tutti e tre)
	 */
	public static String getCompletePurchaseReceipt(){
		
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			//  PURCHASE 1, PURCHASE 2, PURCHASE 3 
			ItemListVO itemList1VO = helper.getItemListByPurchaseNumber("purchase1.items");
			ItemListVO itemList2VO = helper.getItemListByPurchaseNumber("purchase2.items");
			ItemListVO itemList3VO = helper.getItemListByPurchaseNumber("purchase3.items");
			
			String receipt1 = helper.getStringReceipt(itemList1VO.getItemList(), String.valueOf(1));
			String receipt2 = helper.getStringReceipt(itemList2VO.getItemList(), String.valueOf(2));
			String receipt3 = helper.getStringReceipt(itemList3VO.getItemList(), String.valueOf(3));
			
			return receipt1 + "<br/><br/>" + receipt2 + "<br/><br/>"+ receipt3;
		
		} catch (Exception e) {
			return "ERRORE: " + e;
		}
	}
	
	
	/*
	 * Metodo di business che restituisce la stringa (html) rappresentante la descrizione degli ordini effettuabili
	 */
	public static String getPurchasesDescription(){
		
		try {
			helper = new SalesTaxesHelper(PROPERTY_FILE);
		
			//  PURCHASE 1, PURCHASE 2, PURCHASE 3 
			ItemListVO itemList1VO = helper.getItemListByPurchaseNumber("purchase1.items");
			ItemListVO itemList2VO = helper.getItemListByPurchaseNumber("purchase2.items");
			ItemListVO itemList3VO = helper.getItemListByPurchaseNumber("purchase3.items");
			
			String receipt1 = helper.getPurchaseDescription(itemList1VO.getItemList(), String.valueOf(1));
			String receipt2 = helper.getPurchaseDescription(itemList2VO.getItemList(), String.valueOf(2));
			String receipt3 = helper.getPurchaseDescription(itemList3VO.getItemList(), String.valueOf(3));
			
			String errorMessagesSring = SalesTaxesUtility.getErrorMessagesString(itemList1VO.getErrorMessagesList());
			errorMessagesSring = errorMessagesSring + SalesTaxesUtility.getErrorMessagesString(itemList2VO.getErrorMessagesList());
			errorMessagesSring = errorMessagesSring + SalesTaxesUtility.getErrorMessagesString(itemList3VO.getErrorMessagesList());
			
			return errorMessagesSring + receipt1 + receipt2 + receipt3;
		
		} catch (Exception e) {
			return "ERRORE: " + e;
		}
	}

}
