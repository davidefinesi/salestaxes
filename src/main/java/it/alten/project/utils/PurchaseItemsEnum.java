package it.alten.project.utils;


/**
 * Enum che effettua la "traduzione" dal codice dell'articolo alla chiave della sua descrizione
 * 
 * @author Prisma
 *
 */
public enum PurchaseItemsEnum {
	
	PI1 ("PI1", "purchase.book", true, false),
	PI2 ("PI2", "purchase.chocolatebar", true, false),
	PI3 ("PI3", "purchase.musicCD", false, false),
	PI4 ("PI4", "purchase.imported.box.of.chocolate", true, true),
	PI5 ("PI5", "purchase.imported.bottle.of.perfume", false, true),
	PI6 ("PI6", "purchase.bottle.of.perfume", false, false),
	PI7 ("PI7", "purchase.headache.pills", true, false);
	
	private String codItem;
    private String itemNameKey; 
    private boolean isExempt;
    private boolean isImported; 

    
     
    public String getCodItem() {
		return codItem;
	}

	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	public String getItemNameKey() {
		return itemNameKey;
	}

	public void setItemNameKey(String itemNameKey) {
		this.itemNameKey = itemNameKey;
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

	private PurchaseItemsEnum(String codItem, String itemNameKey, boolean isExempt, boolean isImported) {
        this.codItem = codItem;
        this.itemNameKey = itemNameKey;
        this.isExempt = isExempt;
        this.isImported = isImported;
    }

}
