package it.alten.project.utils;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DASH;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.CHAR_SEMICOLON;
import static it.alten.project.utils.Constants.EMPTY_STRING;
import static it.alten.project.utils.Constants.FORMAT_STRING;
import static it.alten.project.utils.Constants.ROUNDING_INCREMENT;
import it.alten.project.bean.Item;
import it.alten.project.vo.ItemListVO;
import it.alten.project.vo.ReceiptVO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Helper class that provide the services tightly bound to the purchase operation
 * 
 * @author Prisma
 *
 */
public class SalesTaxesHelper {
	
	private static Logger logger = Logger.getLogger(SalesTaxesHelper.class);
	private Properties prop;

	
	public SalesTaxesHelper(String propertyFile) throws Exception{
		// charge informations from properties file
		prop = loadProperties(propertyFile);
	}
	

	/*
	 * Method that charge the properties file
	 */
	private Properties loadProperties(String propertyFile) throws Exception{
        
		Properties prop = new Properties();
		InputStream in = null;
		in = SalesTaxesUtility.class.getResourceAsStream(propertyFile);
        try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("Error occurred when reading from the input stream");
			throw new Exception("Error occurred when reading from the input stream", e);
		} catch (IllegalArgumentException e1){
			logger.error("Input stream contains a malformed Unicode escape sequence");
			throw new Exception("Input stream contains a malformed Unicode escape sequence", e1);
		} catch (NullPointerException e) {
			logger.error("Properties file not present");
			throw new Exception("Properties file not present", e);
		}
        
        return prop;
	}
	
	

	/*
	 * Method that print the receipt of the purchase
	 */
	public String getStringReceipt(List<Item> itemList, String purchaseNumber){
		
		Double totalTaxes = new Double(0.00);
		Double totalPrices = new Double(0.00);
		
		String receipt = EMPTY_STRING;
		receipt = "<strong>Input " + purchaseNumber + ":</strong><br/>";
		
		// print of all the items with their initial prices
		for (int i = 0; i < itemList.size(); i++) { 
			Item item = itemList.get(i);
			receipt = receipt + "1 " + item.getItemName() + " at " + item.getPrice();
			if (itemList.size()>1 && i<itemList.size()-1) {
				receipt = receipt + " / "; 
			}
			  
			// calculate the total of the prices and the total of the taxes
			totalPrices = Double.valueOf(totalPrices.doubleValue() +  SalesTaxesUtility.getPriceDoubleValue(item.getFinalPrice()));
			totalTaxes = Double.valueOf(totalTaxes.doubleValue() +  SalesTaxesUtility.getPriceDoubleValue(item.getPercentageTotalTax()));
		}
		
		// do the rounding up
		String totalTaxesRounded = String.format(FORMAT_STRING, totalTaxes);
		String totalPriceRounded = String.format(FORMAT_STRING, totalPrices);
		
		logger.debug("Sales Taxes: " + totalTaxesRounded);
		logger.debug("Total: " + totalPriceRounded);
		
		// do the output print of the the items, with the final price and the totals 
		receipt = receipt + "<br/><br/><strong>Output " + purchaseNumber + ":</strong><br/>";
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			receipt = receipt + "1 " + item.getItemName() + ": " + item.getFinalPrice().replace(CHAR_COMMA, CHAR_DOT);
			if (itemList.size()>1 && i<itemList.size()-1) {
				receipt = receipt + " / "; 
			}
		}
		
		return "<div class='receiptStyle'>" + 
				receipt + "<br/>Sales Taxes: " + 
				totalTaxesRounded.replace(CHAR_COMMA, CHAR_DOT) + 
				"<br/>Total: " + totalPriceRounded.replace(CHAR_COMMA, CHAR_DOT) +
				"</div>";
	}
	
	
	/*
	 * Method that print the receipt without the output
	 */
	public String getPurchaseDescription(List<Item> itemList, String purchaseNumber){
		
		String receipt = EMPTY_STRING;
		receipt = "<strong>Purchase " + purchaseNumber + " --> </strong>";
		
		// do the print of all the items and their starting prices
		for (Item item : itemList) { 
			receipt = receipt + " 1 " + item.getItemName() + "; ";
		}
		
		return "<div class='descriptionStyle'>" + receipt + "</div>";
	}
	
	
	/*
	 * Method that returns an object that wrap the items list of the purchase,
	 * with their prices by the purchase's number, and a list with eventual errors
	 */
	public ItemListVO getItemListByPurchaseNumber(String purchase) {
		
		ItemListVO vo = new ItemListVO();
		List<Item> itemList = new ArrayList<Item>();
		List<String> errorMessagesList = new ArrayList<String>();
		
		String purchase1ItemDef = (String)prop.get(purchase);
		String[] purchase1ItemDefArray = purchase1ItemDef.split(CHAR_SEMICOLON);
		
		// if at least an item exists in the purchase go ahead
		if (purchase1ItemDefArray.length>0) {
			for (int i = 0; i < purchase1ItemDefArray.length; i++) {
				String[] itemArray = purchase1ItemDefArray[i].split(CHAR_DASH);
				
				// if every item is composed by two parts (code and price) go ahead
				if (itemArray.length == 2) {
					String codItem = purchase1ItemDefArray[i].split(CHAR_DASH)[0];
					String priceItem = purchase1ItemDefArray[i].split(CHAR_DASH)[1];
					
					// if code is not null go ahead
					if (SalesTaxesUtility.isNotNullAndEmptyString(codItem)) {
						String purchaseName = purchase.split("\\.")[0].toUpperCase();
						String itemNameKey;
						try {
							itemNameKey = PurchaseItemsEnum.valueOf(codItem).getItemNameKey();
							boolean isExempt = PurchaseItemsEnum.valueOf(codItem).isExempt();
							boolean isImported = PurchaseItemsEnum.valueOf(codItem).isImported();
							try {
								// create the item object
								itemList.add(new Item(purchaseName,
										             (String)prop.getProperty(itemNameKey), 
										             priceItem, 
										             isExempt, 
										             isImported));
							} catch (Exception e) {
								logger.error(e);
								errorMessagesList.add(e.getMessage());
							}
						} catch (Exception e1) {
							logger.error("Error occured in the translation from the code '" + codItem + "' to the item name fro the purchase " + purchaseName);
							errorMessagesList.add("Error occured in the translation from the code '" + codItem + "' to the item name fro the purchase " + purchaseName);
						}
					}
				}
			}
		}
		
		vo.setItemList(itemList);
		vo.setErrorMessagesList(errorMessagesList);
		
		return vo;
	}
	
}
