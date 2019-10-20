# Simple bill calculation program
This is a simple program that will calculate a user shopping bill taking into considerations certain discount amounts and percentages that
are applicable for the following : 
- Certain user types (Employee, Affiliate, Premium).
- Certain Shopping items (Everything except Groceries).
- Certain bill amount (bill with total value that is > 100 will get special discount)

# Technical details:
- This is a simple program written using java.
- This program is built using gradle.
- Unit testing is written and run using Junit 5.
- There is 8 Test cases that covers different angles of different user types, having different types of items in his 
shopping cart that accumulates to different payable amounts.

# Run and build:
- Pre-requisites: java and gradle must be installed.
- Checkout the code or just download the code as zipped folder and extract to your machine.
- Navigate to the assignment directory where the project was extracted.
- Run the following command to build: "gradle build".
- If the logs shows "Build Successful" message, that means the project has been built properly and the test
cases are all passed successfully.
-  To check the test cases execution result navigate to "build/reports/test/test", and check the html 
generated report there to see the all the test cases and the success percentage.
- To check the code quality navigate to build/reports/spotbugs html reports for the main and the test packages.
- To see test coverage report execute the command : "gradle jacocoTestReport" and then navigate to the directory
build/reports/jacoco/test/html and open the html page index.html to see the test coverage percentage
to all classes and packages.
