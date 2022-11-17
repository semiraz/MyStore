# Magento e-commerce website for automation testing - LUMA shop
 

#### Smoke Test automation suite for testing https://magento.softwaretestingboard.com/

## General info
 "Luma" is a dummy website for learning test automation. Contains all basic flows of an e-commerce website for sportswear and equipment.

  Users can search for wanted product(s), choose a size, color and quantity and go to checkout. 
  Before finishing an order, the user needs to fill a form with personal details and shipping address.
   Uniquely for this app, the user does not need to create an account unless he/she wants to check their order.
 
 ## Test Suite
 Test automation suite for "Luma" e-commerce web application written with Selenium TestNG framework in Java using Maven following POM pattern.

  Contains E2E smoke test for core functionality. All pages are separate in the pages package(src/main/java/pages) to not violate Single Responsibility Principle.
 In the Utilities class are all variables and methods so we can change them according to our wishes to pass or fail the test. In the test/java/data package is a json fail with all data for the shipping form.

 Also, created and automated extent reports log(in src/test/java/test_components package). Created Listeners class which is implementing ITestListener - listener for test running. It captures screenshots for failed cases and attaches them to the extent reporter. 


## Environment

`Ubuntu 22.04.1 LTS`

`Java Version 11.0.16`

`Google Chrome Version 106`


## Deployment


To run test:
```
mvn test
```

To run testng.xml suite use:
```
mvn test -PSmoke
```


## Status

Currently the project contains only smoke test.

It is possible to see logs in browser(index.html) after running testng.xml file.
