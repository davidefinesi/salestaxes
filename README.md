
# salestaxes
Simple web application that handle purchases of items. It accept a list of items in input, with their price, and print a receipt which lists the name of all the items and their final prices (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. 
It is possible to execute the purchase1, the purchase2, the purchase3 or the 'complete purchase' that means all the purchases togheter.
- Basic sales tax: applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. 
- Import duty: applicable on all imported goods at a rate of 5%, with no exemptions.

## Requires
jdk 1.7, Apache Maven 3.3.9

## Installation
Deployed on Apache Tomcat 8.0.38
    
## RESTful Web Services
Salestaxes web application use the RESTful web service technology to handle the purchases. There is a 'SalesTaxesRestService' that returns the receipts of the purchase, so is possible to get these both the web app page by the invocation url of the services, only passing the purchaseId:<br/>
- purchase1 --> /salestaxes/rest/salestaxesservice/purchases/1<br/>
- purchase2 --> /salestaxes/rest/salestaxesservice/purchases/2<br/>
- purchase3 --> /salestaxes/rest/salestaxesservice/purchases/3<br/>
- complete purchase --> /salestaxes/rest/salestaxesservice/purchases/-1

## Properties
PURCHASE ITEM NAMES<br/>
purchase.book<br/>
purchase.chocolatebar<br/>
purchase.musicCD<br/>
purchase.imported.bottle.of.perfume<br/>
purchase.imported.box.of.chocolate<br/>
purchase.bottle.of.perfume<br/>
purchase.headache.pills<br/>

PURCHASES DEFINITION (item code - price "##.00")<br/>
purchase1.items<br/>
purchase2.items<br/>
purchase3.items<br/>

## Logging
The web application use log4j library to write log messages. Actually it logs only on console but is possible to permit writing log messages on a file (/logs/sales_taxes.log under tomcat server) uncommenting the 'Rolling File Appender BUSINESS' on the log4j.xml configuration file and adding this appender as 'appender-ref' to the packages.


