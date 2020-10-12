Biker API tests
---------------

This project contains 5 test scenarios in 4 feature files to test the Biker API endpoint. 

To run the tests on your machine please follow the below steps : 

1. Clone the repository to a folder on to your machine
2. Navigate to the folder
3. Check the PreRequisites and install the missing softwares 

PreRequisites for running the tests :
-----------------------------------
	* Java 8
	* maven 
		-To install maven on your system please refer https://maven.apache.org/install.html 

4. Run the tests
 
Running the tests: 
-------------------
To Run the tests : Please run the RunTest.bat file found in the base folder OR 
run command 'mvn test -Dtest=TestRunner' via command prompt after navigating to the base folder where the pom.xml of the project exists

This will run all the below specified scenarios.

Scenarios implemented 
---------------------
1.Validate that Frankfurt is in Germany and find the latitude and longitude
2.Validate that the CityBikes API endpoint is retrieving data
3.List all the stations with empty slots
4.Request with an invalid network endpoint
5.User should recieve 'METHOD NOT ALLOWED' response when a POST request is send to the endpoint