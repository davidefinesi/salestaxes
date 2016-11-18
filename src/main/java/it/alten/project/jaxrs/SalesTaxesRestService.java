package it.alten.project.jaxrs;


import static it.alten.project.utils.Constants.COMPLETE_PURCHASE_ID;
import static it.alten.project.utils.Constants.EMPTY_STRING;
import it.alten.project.facade.SalesTaxesFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;


/**
 * Classe WebService di tipo REST che si occupa di gestire gli acquisti
 * 
 * @author Prisma
 *
 */

@Path("/salestaxesservice")
public class SalesTaxesRestService {
	
	private static Logger logger = Logger.getLogger(SalesTaxesRestService.class);

	
	@GET  
	@Path("/purchasesDescription")  
	@Produces(MediaType.TEXT_HTML)  
	public String getPurchasesDescription(){
		
		logger.info("Purchases description...");
		
		// attraverso il facade recupero la stringa (HTML) della descrizione degli ordini effettuabili
		return SalesTaxesFacade.getPurchasesDescription();
	}
	
	
	@GET  
	@Path("/purchases/{purchaseId}")  
	@Produces(MediaType.TEXT_HTML)  
	public String getPurchaseOutput(@PathParam("purchaseId") String purchaseId){
		
		String receipt = EMPTY_STRING;
		
		// se il purchaseId è diverso da "-1" alla sto effettuando un ordine specifico altrimenti eseguo tutti e tre gli ordini
		if (!COMPLETE_PURCHASE_ID.equals(purchaseId)) {
			
			logger.info("Effettuato acquisto " + purchaseId + "...");
			
			// attraverso il facade recupero la stringa (HTML) della ricevuta dell'ordine effettuato
			receipt = SalesTaxesFacade.getPurchaseReceipt(purchaseId);
		} else {
			
			logger.info("Effettuato acquisto completo...");
			
			// attraverso il facade recupero la stringa (HTML) della ricevuta dell'ordine completo effettuato
			receipt = SalesTaxesFacade.getCompletePurchaseReceipt();
		}
		
		return receipt;
	}
	
}
