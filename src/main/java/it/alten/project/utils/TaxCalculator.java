package it.alten.project.utils;

import static it.alten.project.utils.Constants.FORMAT_STRING;
import static it.alten.project.utils.Constants.ROUNDING_INCREMENT;
import it.alten.project.vo.ReceiptVO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;


/**
 * Classe Calcolatore che si occupa del calcolo delle tasse effettivamente applicate
 * 
 * @author Prisma
 *
 */
public class TaxCalculator {
	
	private static Logger logger = Logger.getLogger(TaxCalculator.class);

	private Double finalPriceDouble = new Double(0.0);
	private BigDecimal percentageBasicTaxRounded = new BigDecimal(0.0);
	private BigDecimal percentageDutyTaxRounded = new BigDecimal(0.0);
	private BigDecimal percentageTotalTax = new BigDecimal(0.0);
	private ReceiptVO receipt = new ReceiptVO();
	private String itemName;
	
	
	public TaxCalculator(String itemName) throws Exception{
		this.itemName = itemName;
	}
	
	
	/*
	 * Metodo che restituisce il prezzo finale e l'ammontare totale delle tasse sul prodotto, al netto della regola sull'arrotondamento
	 */
	public ReceiptVO getReceiptInformations(double priceDouble, boolean isExempt, boolean isImported){
		
		logger.debug("Calcolo della ricevuta per l'articolo " + itemName + " con un prezzo pari a " + String.format(FORMAT_STRING, priceDouble));
		
		finalPriceDouble = priceDouble;
		
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
		receipt.setFinalPrice(finalPriceDoubleString);
		receipt.setPercentageTotalTax(percentageTotalTaxString);
		
		return receipt;
	}
	
}
