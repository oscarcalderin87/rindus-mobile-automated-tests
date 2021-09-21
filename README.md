# Prerequisites
Java (version 1.8 or higher)

Maven (version 4 or higher)

Appium (version 1.21.0 or higher)

A virtual android device created with the following data:

- Android Version: 8.1.0
- Device Name: Android_Accelerated_Oreo

# Running test cases

## Execution without report
`mvn clean test`

## Execution with report
`mvn clean test verify`

An HTML file named overview-features.html is generated in report/cucumber-html-reports

Note: the created virtual device should be running before starting the execution
