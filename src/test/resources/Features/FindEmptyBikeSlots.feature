Feature: stations object validation 

Scenario: List all the stations with empty slots

Given the CityBikes network endpoint "http://api.citybik.es/v2/networks/edinburgh-cycle-hire"
When request is send
Then user should be able to fetch all the stations with empty slots if available