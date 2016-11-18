<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<script src="<c:url value="/resources/js/jquery-1.12.3.min.js"/>"></script>
	<script src="<c:url value="/resources/js/salestaxes.js"/>"></script>
	<link rel="stylesheet" type="text/css" href="resources/css/salestaxes.css">
</head>

<body class="bodyStyle">

	<div id="container">
	    <div class="parallax img-banner" data-velocity="-0.6"></div>
		<div class="content space-top">
	        <div class="title">
	            <p>SALES TAXES WEB</p>
	        </div>
	        <div class="paragraph">
            	<p>Applicazione Web che gestisce l'ordine di acquisto di particolari articoli. A fronte della scelta del tipo di acquisto
            	da parte dell'utente il sistema fornisce un prospetto della ricevuta dell'acquisto stesso, con informazioni sui prezzi di partenza,
            	sui prezzi finali in base alla tassazione applicata, sul totale delle tasse applicate e sul totale della spesa.</p>
	        </div>
	        
	        <div id="purchasesDescription" class="label selection"></div>
	        
	        <div class="selection">
	        	<table>
	        		<tr>
	        			<td class="label" width="62%">Seleziona l'ordine di acquisto</td>
	        			<td>
	        				<select id="purchasesSelection">
								<option value="0">Select</option>
								<option value="1">Purchase1</option>
								<option value="2">Purchase2</option>
								<option value="3">Purchase3</option>
								<option value="-1">Complete purchase</option>
							</select>
						</td>
	        		</tr>
				</table>
	        </div>
	        
	        <br/>
	        <div id="purchasesResult"></div>
	        
	    </div>
		
	</div>

</body>


</html>



