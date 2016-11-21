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
 * RESTful webservice that handle purchases
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
		
		// by facade class getting the html string that contains the description of the purchases available
		return SalesTaxesFacade.getPurchasesDescription();
	}
	
	
	@GET  
	@Path("/purchases/{purchaseId}")  
	@Produces(MediaType.TEXT_HTML)  
	public String getPurchaseOutput(@PathParam("purchaseId") String purchaseId){
		
		String receipt = EMPTY_STRING;
		
		// if the purchaseId is not equal to "-1" I'm doing a specified purchase, otherwise I do the complete purchase
		if (!COMPLETE_PURCHASE_ID.equals(purchaseId)) {
			
			logger.info("Purchase made " + purchaseId + "...");
			
			// from facade class I get the receipt string (HTML) of the purchase 
			receipt = SalesTaxesFacade.getPurchaseReceipt(purchaseId);
		} else {
			
			logger.info("Complete purchase...");
			
			// from facade class I get the receipt string (HTML) of the complete purchase
			receipt = SalesTaxesFacade.getCompletePurchaseReceipt();
		}
		
		return receipt;
	}
	
}
