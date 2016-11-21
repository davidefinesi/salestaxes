package it.alten.project.utils;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.EMPTY_STRING;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Utility class
 * 
 * @author Prisma
 *
 */
public class SalesTaxesUtility {
	
	/*
	 * Method that provide the rounding up of the price by the defined increment 
	 */
	public static BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			BigDecimal result = divided.multiply(increment);
			return result;
		}
	}
	
	/*
	 * Metodo di utilità che a partire da un prezzo in formato String (##,00) restituisce un Double
	 * Method that from a string price (##,00) returns a Double
	 */
	public static double getPriceDoubleValue(String price){
		return new Double(price.replace(CHAR_COMMA, CHAR_DOT)).doubleValue();
	}
	
	
	/*
	 * Method that checks if a string is not null and not empty
	 */
	public static boolean isNotNullAndEmptyString(String value){
		if (value != null && !EMPTY_STRING.equals(value)) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Method that from an error message list returns the relative html string 
	 */
	public static String getErrorMessagesString(List<String> errorMessagesList){
		String receipt = EMPTY_STRING;
		if (!errorMessagesList.isEmpty()) {
			for (int i = 0; i < errorMessagesList.size(); i++) {
				String errorMessage = (String)errorMessagesList.get(i);
				receipt = receipt + "<div class='errorMessageStyle'>" + errorMessage + "</div><br/>";
			}
		}
		
		return receipt;
	}
}
