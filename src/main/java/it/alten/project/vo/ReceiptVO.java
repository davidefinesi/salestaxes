package it.alten.project.vo;


/**
 * Classe che definisce una struttura dati per la ricevuta, contenente il prezzo finale e l'ammontare totale delle tasse effettivamete applicate
 * 
 * @author Prisma
 *
 */
public class ReceiptVO {
	
	private String finalPrice;
	private String percentageTotalTax;
	
	
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
}
