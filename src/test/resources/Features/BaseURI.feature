Feature: CityBikes API validation

Scenario: Validate that the CityBikes API endpoint is retrieving data

Given the CityBikes api URI "http://api.citybik.es/v2/networks"
When the request is send
Then all the Networks available across the globe should be retrieved with details : name, href, location, id