package it.alten.project.utils;

import static it.alten.project.utils.Constants.FORMAT_STRING;
import static it.alten.project.utils.Constants.ROUNDING_INCREMENT;
import it.alten.project.vo.ReceiptVO;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;


/**
 * Calculator class that provide the exact amount of the applicated taxes
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
	
	
	public TaxCalculator(String itemName) {
		this.itemName = itemName;
	}
	
	
	/*
	 * Method that returns the final price and the total amount of taxes on the item, in order to the rounding up rule
	 */
	public ReceiptVO getReceiptInformations(double priceDouble, boolean isExempt, boolean isImported){
		
		logger.debug("Calculation of the receipt for the item " + itemName + " with the price " + String.format(FORMAT_STRING, priceDouble));
		
		finalPriceDouble = priceDouble;
		
		// if the item is not exempt apply the basic tax (10%) and doing the rounding up to 0.05 
		if (!isExempt) {
			Double percentageBasicTax = Double.valueOf(priceDouble * 10 / 100);
			percentageBasicTaxRounded = SalesTaxesUtility.round(new BigDecimal(percentageBasicTax), new BigDecimal(ROUNDING_INCREMENT), RoundingMode.UP);
			logger.debug("The item " + itemName + " is not exempt from the Basic sales tax (10%)");
		}
		
		// if the item is imported apply the import duty (5%) and doing the rounding up to 0.05
		if (isImported) {
			Double percentageDutyTax = Double.valueOf(priceDouble * 5 / 100);
			percentageDutyTaxRounded = SalesTaxesUtility.round(new BigDecimal(percentageDutyTax), new BigDecimal(ROUNDING_INCREMENT), RoundingMode.UP);
			logger.debug("The item " + itemName + " is imported so the import duty is applicable (5%)");
		}
		
		// doing the sum of the taxes
		percentageTotalTax = percentageBasicTaxRounded.add(percentageDutyTaxRounded);
		String percentageTotalTaxString = String.format(FORMAT_STRING, percentageTotalTax);
		logger.debug("The total amount of taxes for the item " + itemName + ": " + percentageTotalTaxString);
		
		// defining the final price doing the sum between the initial price and the total amount of taxes
		finalPriceDouble = Double.valueOf(priceDouble + percentageTotalTax.doubleValue());
		String finalPriceDoubleString = String.format(FORMAT_STRING, finalPriceDouble);
		logger.debug("The final price for the item " + itemName + ": " + finalPriceDoubleString);
		
		// returning an object that wraps the two informations
		receipt.setFinalPrice(finalPriceDoubleString);
		receipt.setPercentageTotalTax(percentageTotalTaxString);
		
		return receipt;
	}
	
}
