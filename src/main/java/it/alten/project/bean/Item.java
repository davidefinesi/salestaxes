package it.alten.project.bean;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.FORMAT_STRING;
import it.alten.project.utils.TaxCalculator;
import it.alten.project.vo.ReceiptVO;

import org.apache.log4j.Logger;



/**
 * Class that defines data structure of the purchase's items 
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
		
		// read the price from the properties file and do a format validate
		this.price = validateAndFormatPrice(purchaseName, priceFromProperties);
		 
		// get information about the final price and the amount of taxes
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
	 * Method that reads from the properties file the price and return it in a right format
	 */
	private String validateAndFormatPrice(String purchaseName, String price) throws Exception{
		
		// check if it is a valid format
		try {
			Double.parseDouble(price);
			
			// do the rounding and return
			String stringPrice = String.format(FORMAT_STRING, new Double(price));
			return stringPrice.replace(CHAR_COMMA, CHAR_DOT);
			
		} catch (NumberFormatException e) {
			logger.error("Error in the starting price '" + price + "' of the item '" + itemName.toUpperCase() + "' in the purchase " + purchaseName + ": wrong format");
			throw new Exception("Error in the starting price '" + price + "' of the item '" + itemName.toUpperCase() + "' in the purchase " + purchaseName + ": wrong format", e);
		}
	}
		
}
