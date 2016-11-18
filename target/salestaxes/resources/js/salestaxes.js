$(document).ready(function() {
	
	loadPurchasesDescription();
	
	// funzione che gestisce la selezione dell'ordine nella tendina
	$("#purchasesSelection").change(function() {
		
		// se il valore non è 0 (nessuna selezione) procedo nell'elaborazione altrimenti pulisco la parte dei risultati
		if ($(this).val() != "0") {
			
			purchaseUrl = "rest/salestaxesservice/purchases/" + $(this).val();
			
			// effettuo la chiamata ajax al servizio rest
			$.ajax({
				url : purchaseUrl
			}).then(function(data) {
				$('#purchasesResult').html(data);
			});
			
		} else {
			$('#purchasesResult').html("");
		}
		
	});
	
});


/*
 * funzione che si occupa del pre-caricamento delle tipologie di oridni presenti
 */
function loadPurchasesDescription(){
	
	// effettuo la chiamata ajax al servizio rest
	$.ajax({
        url: "rest/salestaxesservice/purchasesDescription"
    }).then(function(data) {
    	$("#purchasesDescription").html("<p>PURCHASES DESCRIPTION</p>" + data)
    });
	
}