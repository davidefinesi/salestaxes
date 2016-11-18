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
 * Classe Helper che fornisce dei servizi strettamente legati all'operazione di ordinazione
 * 
 * @author Prisma
 *
 */
public class SalesTaxesHelper {
	
	private static Logger logger = Logger.getLogger(SalesTaxesHelper.class);
	private Properties prop;
	private String PROPERTY_FILE = "/salestaxes.properties";

	
	public SalesTaxesHelper() throws Exception{
		// carico informazioni dal file di properties
		prop = loadProperties();
	}
	
	/*
	 * Metodo che effettua il caricamento del file di properties
	 */
	private Properties loadProperties() throws Exception{
        
		Properties prop = new Properties();
		InputStream in = null;
		in = SalesTaxesUtility.class.getResourceAsStream(PROPERTY_FILE);
        try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("Errore durante la lettura/scrittura dall'InputStream");
			throw new Exception("Errore durante la lettura/scrittura dall'InputStream", e);
		} catch (IllegalArgumentException e1){
			logger.error("L'InputStream contiene una sequenza 'malformed' secondo Unicode");
			throw new Exception("L'InputStream contiene una sequenza 'malformed' secondo Unicode", e1);
		}
        
        return prop;
	}
	
	/*
	 * Metodo che restituisce il prezzo finale e l'ammontare totale delle tasse sul prodotto, al netto della regola sull'arrotondamento
	 */
	public ReceiptVO getReceiptInformations(String itemName, double priceDouble, boolean isExempt, boolean isImported){
		
		logger.debug("Calcolo della ricevuta per l'articolo " + itemName + " con un prezzo pari a " + String.format(FORMAT_STRING, priceDouble));
		Double finalPriceDouble = priceDouble;
		BigDecimal percentageBasicTaxRounded = new BigDecimal(0.0);
		BigDecimal percentageDutyTaxRounded = new BigDecimal(0.0);
		BigDecimal percentageTotalTax = new BigDecimal(0.0);
		
		// se il prodotto non è tra quelli esenti si applica la basic sales tax (10%) ed eseguo l'arrotondamento in su dello 0.05
		if (!isExempt) {
			Double percentageBasicTax = Double.valueOf(priceDouble * 10 / 100);
			percentageBasicTaxRounded = SalesTaxesUtility.round(new BigDecimal(percentageBasicTax), new BigDecimal(ROUNDING_INCREMENT), RoundingMode.UP);
			logger.debug("L'articolo " + itemName + " non è tra quelli esenti dalla Basic sales tax (10%)");
		}
		
		// se il prodotto è importato si applica la tassa di importazione (5%) ed eseguo l'arrotondamento in su dello 0.05
		if (isImported) {
			Double percentageDutyTax = Double.valueOf(priceDouble * 5 / 100);
			percentageDutyTaxRounded = SalesTaxesUtility.round(new BigDecimal(percentageDutyTax), new BigDecimal(ROUNDING_INCREMENT), RoundingMode.UP);
			logger.debug("L'articolo " + itemName + " è di importazione percui soggetto alla Import duty (5%)");
		}
		
		// effettuo la somma delle tasse presenti
		percentageTotalTax = percentageBasicTaxRounded.add(percentageDutyTaxRounded);
		String percentageTotalTaxString = String.format(FORMAT_STRING, percentageTotalTax);
		logger.debug("Ammontare di tasse totali applicato all'articolo " + itemName + ": " + percentageTotalTaxString);
		
		// stabilisco il prezzo finale sommando il valore del prezzo iniziale e l'ammontare totale delle tasse precedentemente calcolato
		finalPriceDouble = Double.valueOf(priceDouble + percentageTotalTax.doubleValue());
		String finalPriceDoubleString = String.format(FORMAT_STRING, finalPriceDouble);
		logger.debug("Prezzo finale per l'articolo " + itemName + " al netto delle tasse: " + finalPriceDoubleString);
		
		// restituisco un oggetto che contiene queste due informazioni
		ReceiptVO receipt = new ReceiptVO();
		receipt.setFinalPrice(finalPriceDoubleString);
		receipt.setPercentageTotalTax(percentageTotalTaxString);
		
		return receipt;
	}
	

	/*
	 * Metodo che si occupa di stampare a video la ricevuta dell'ordine
	 */
	public String getStringReceipt(List<Item> itemList, String purchaseNumber){
		
		Double totalTaxes = new Double(0.00);
		Double totalPrices = new Double(0.00);
		
		String receipt = EMPTY_STRING;
		receipt = "<strong>Input " + purchaseNumber + ":</strong><br/>";
		
		// eseguo la stampa di tutti gli articoli coi relativi prezzi di partenza  
		for (int i = 0; i < itemList.size(); i++) { 
			Item item = itemList.get(i);
			receipt = receipt + "1 " + item.getItemName() + " at " + item.getPrice();
			if (itemList.size()>1 && i<itemList.size()-1) {
				receipt = receipt + " / "; 
			}
			  
			// calcolo i totali di prezzi e tasse applicate effettivamente
			totalPrices = Double.valueOf(totalPrices.doubleValue() +  SalesTaxesUtility.getPriceDoubleValue(item.getFinalPrice()));
			totalTaxes = Double.valueOf(totalTaxes.doubleValue() +  SalesTaxesUtility.getPriceDoubleValue(item.getPercentageTotalTax()));
		}
		
		// effettuo l'arrotondamento
		String totalTaxesRounded = String.format(FORMAT_STRING, totalTaxes);
		String totalPriceRounded = String.format(FORMAT_STRING, totalPrices);
		
		logger.debug("Sales Taxes: " + totalTaxesRounded);
		logger.debug("Total: " + totalPriceRounded);
		
		// eseguo la stampa degli output degli articoli, con il prezzo finale ed i totali 
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
	 * Metodo che si occupa di stampare a video la ricevuta dell'ordine senza output
	 */
	public String getPurchaseDescription(List<Item> itemList, String purchaseNumber){
		
		String receipt = EMPTY_STRING;
		receipt = "<strong>Purchase " + purchaseNumber + " --> </strong>";
		
		// eseguo la stampa di tutti gli articoli coi relativi prezzi di partenza  
		for (Item item : itemList) { 
			receipt = receipt + " 1 " + item.getItemName() + "; ";
		}
		
		return "<div class='descriptionStyle'>" + receipt + "</div>";
	}
	
	
	/*
	 * Metodo che ritorna un oggetto che wrappa la lista degli item dell'ordine effettuato,
	 * coi rispettivi prezzi in base al numero dello stesso, ed una lista di eventuali errori
	 */
	public ItemListVO getItemListByPurchaseNumber(String purchase) {
		
		ItemListVO vo = new ItemListVO();
		List<Item> itemList = new ArrayList<Item>();
		List<String> errorMessagesList = new ArrayList<String>();
		
		String purchase1ItemDef = (String)prop.get(purchase);
		String[] purchase1ItemDefArray = purchase1ItemDef.split(CHAR_SEMICOLON);
		
		// se esiste almeno un item nell'ordine proseguo nell'elaborazione
		if (purchase1ItemDefArray.length>0) {
			for (int i = 0; i < purchase1ItemDefArray.length; i++) {
				String[] itemArray = purchase1ItemDefArray[i].split(CHAR_DASH);
				
				// se ogni item è composto di due parti (codice e prezzo) vado avanti
				if (itemArray.length == 2) {
					String codItem = purchase1ItemDefArray[i].split(CHAR_DASH)[0];
					String priceItem = purchase1ItemDefArray[i].split(CHAR_DASH)[1];
					
					// se il codice è valorizzato proseguo
					if (SalesTaxesUtility.isNotNullAndEmptyString(codItem)) {
						String purchaseName = purchase.split("\\.")[0].toUpperCase();
						String itemNameKey;
						try {
							itemNameKey = PurchaseItemsEnum.valueOf(codItem).getItemNameKey();
							boolean isExempt = PurchaseItemsEnum.valueOf(codItem).isExempt();
							boolean isImported = PurchaseItemsEnum.valueOf(codItem).isImported();
							try {
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
							logger.error("Errore di traduzione dal codice '" + codItem + "' nel nome dell'articolo per l'ordine " + purchaseName);
							errorMessagesList.add("Errore di traduzione dal codice '" + codItem + "' nel nome dell'articolo per l'ordine " + purchaseName);
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
