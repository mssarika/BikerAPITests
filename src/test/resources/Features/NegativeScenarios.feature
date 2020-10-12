Feature: Unexpected client request

Scenario: Request with an invalid network endpoint

Given an invalid network endpoint "http://api.citybik.es/v2/networks/frankfurt"
When a request is made
Then the request should return 404 NOT FOUND error

Scenario: User should recieve 'METHOD NOT ALLOWED' response when a POST request is send to the endpoint
Given the CityBikes api endpoint "http://api.citybik.es/v2/networks"
When I makes a POST request
Then the request should fail