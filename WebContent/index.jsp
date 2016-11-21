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
            	<p>Simple web application that handle purchases of items. It accept a list of items in input, with their price, and print a receipt which lists the name 
            	   of all the items and their final prices (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid.</p>
            	 <p>Basic sales tax: applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt.</p>
				 <p>Import duty: applicable on all imported goods at a rate of 5%, with no exemptions.</p>
	        </div>
	        
	        <div id="purchasesDescription" class="label selection"></div>
	        
	        <div class="selection">
	        	<table>
	        		<tr>
	        			<td class="label" width="62%">Select the purchase order</td>
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



