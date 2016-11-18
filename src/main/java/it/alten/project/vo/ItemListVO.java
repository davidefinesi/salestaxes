package it.alten.project.vo;

import it.alten.project.bean.Item;

import java.util.List;


/**
 * Classe che definisce una struttura dati per la ricevuta, contenente il prezzo finale e l'ammontare totale delle tasse effettivamete applicate
 * 
 * @author Prisma
 *
 */
public class ItemListVO {
	
	private List<Item> itemList;
	private List<String> errorMessagesList;
	
	
	public List<Item> getItemList() {
		return itemList;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public List<String> getErrorMessagesList() {
		return errorMessagesList;
	}
	public void setErrorMessagesList(List<String> errorMessagesList) {
		this.errorMessagesList = errorMessagesList;
	}
	
}
