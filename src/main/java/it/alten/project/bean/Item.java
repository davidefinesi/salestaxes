package it.alten.project.bean;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.FORMAT_STRING;
import it.alten.project.utils.TaxCalculator;
import it.alten.project.vo.ReceiptVO;

import org.apache.log4j.Logger;



/**
 * Classe che rappresenta la struttura dati con la quale gestisco gli articoli dei vari ordini
 * 
 * @author Prisma
 *
 */
public class Item {

	private String price;
	private String finalPrice;
	private String percentageTotalTax;
	private String itemName;
	private boolean isExempt;
	private boolean isImported;
	
	private static Logger logger = Logger.getLogger(Item.class);
	
	
	public Item(String purchaseName, String itemName, String priceFromProperties, boolean isExempt, boolean isImported) throws Exception{
		
		this.itemName = itemName;
		this.isExempt = isExempt;
		this.isImported = isImported;
		
		// leggo il prezzo dal file di properties e ne faccio una validazione di formato
		this.price = validateAndFormatPrice(purchaseName, priceFromProperties);
		 
		// recupero e setto informazioni sul prezzo finale e sull'ammontare delle tasse applicate
		TaxCalculator calculator = new TaxCalculator(this.itemName);
		ReceiptVO vo = calculator.getReceiptInformations(new Double(price).doubleValue(), isExempt, isImported);
		
		this.finalPrice = vo.getFinalPrice();
		this.percentageTotalTax = vo.getPercentageTotalTax(); 
	}
	
	
	

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getPercentageTotalTax() {
		return percentageTotalTax;
	}

	public void setPercentageTotalTax(String percentageTotalTax) {
		this.percentageTotalTax = percentageTotalTax;
	}

	public boolean isExempt() {
		return isExempt;
	}

	public void setExempt(boolean isExempt) {
		this.isExempt = isExempt;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/*
	 * Metodo privato che legge dal file Properites il prezzo e lo restituisce in un formato idoneo
	 */
	private String validateAndFormatPrice(String purchaseName, String price) throws Exception{
		
		// mi assicuro che sia in un formato valido
		try {
			Double.parseDouble(price);
			
			// eseguo un arrotondamento e restituisco
			String stringPrice = String.format(FORMAT_STRING, new Double(price));
			return stringPrice.replace(CHAR_COMMA, CHAR_DOT);
			
		} catch (NumberFormatException e) {
			logger.error("Errore nel prezzo di partenza '" + price + "' dell'articolo '" + itemName.toUpperCase() + "' dell'ordine " + purchaseName + ": formato errato");
			throw new Exception("Errore nel prezzo di partenza '" + price + "' dell'articolo '" + itemName.toUpperCase() + "' dell'ordine " + purchaseName + ": formato errato", e);
		}
	}
		
}
