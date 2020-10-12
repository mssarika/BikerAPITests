Feature: Frankfurt City Validation

Scenario: Validate that Frankfurt is in Germany and find the latitude and longitude

Given the CityBikes api endpoint "http://api.citybik.es/v2/networks/"
When the city is queried with network_id "visa-frankfurt" and the request send
Then verify that the city "Frankfurt" is in country "DE" and retrieve the latitude and longitude
