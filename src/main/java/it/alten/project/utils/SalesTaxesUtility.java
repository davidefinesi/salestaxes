package it.alten.project.utils;

import static it.alten.project.utils.Constants.CHAR_COMMA;
import static it.alten.project.utils.Constants.CHAR_DOT;
import static it.alten.project.utils.Constants.EMPTY_STRING;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Classe di utilità
 * 
 * @author Prisma
 *
 */
public class SalesTaxesUtility {
	
	/*
	 * Metodo che effettua l'arrotondamento per eccesso dell'importo in base all'incremento stabilito 
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
	 */
	public static double getPriceDoubleValue(String price){
		return new Double(price.replace(CHAR_COMMA, CHAR_DOT)).doubleValue();
	}
	
	
	/*
	 * Metodo di utilità che controlla che una stringa sia contemporaneamente non null e non vuota
	 */
	public static boolean isNotNullAndEmptyString(String value){
		if (value != null && !EMPTY_STRING.equals(value)) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Metodo che a partire dalla lista dei messaggi di errore restituisce la relativa stringa html
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
