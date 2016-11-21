
# salestaxes
Simple web application that handle purchases of items. It accept a list of items in input, with their price, and print a receipt which lists the name of all the items and their final prices (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. 
There are two kind of taxes: basic and import duty. Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

## Requires
jdk 1.7, Apache Maven 3.3.9

## Installation
Deployed on Apache Tomcat 8.0.38

## Usage with Code
    layer.animation = "squeezeDown"
    layer.animate()
    
## Functions
    animate()
    animateNext { ... }
    animateTo()
    animateToNext { ... }

## Properties
    force
    duration
    delay
    damping
    velocity
    repeatCount
    scale
    x
    y
    rotate

\* Not all properties work together. Play with the demo app.
