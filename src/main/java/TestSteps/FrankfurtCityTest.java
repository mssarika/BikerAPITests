package TestSteps;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FrankfurtCityTest {
		
	RequestSpecification httpRequest;
	Response httpResponse;
	String jsonMimeType = "application/json";
	String test_network_id;
	
	@Given("the CityBikes api endpoint \"(.*)\"")
	public void CreateRequest(String url)
	{
		RestAssured.baseURI=url;
		httpRequest=RestAssured.given();
	}

	@When("the city is queried with network_id \"(.*)\" and the request send")
	public void SendRequest(String test_network_id)
	{
		this.test_network_id=test_network_id;
		httpResponse=httpRequest.request(Method.GET, test_network_id+"?fields=id,location");
	}
	
	@Then("verify that the city \"(.*)\" is in country \"(.*)\" and retrieve the latitude and longitude")
	public void ValidateResponse(String cityname,String countryCode)
	{	//validating the response code and content type
		System.out.println("\nFunctionality under test --> Validating the country and fetching the latitude/longitude");
	    Assert.assertEquals("Request failed",HttpStatus.SC_OK, httpResponse.getStatusCode());
	    Assert.assertTrue(httpResponse.contentType().contains(jsonMimeType));
	    
	    System.out.println("Validating the netword_id("+test_network_id+") , City Name("+cityname+") and the Country("+countryCode+")");
	    //verifies if the network id retrieved is as expected
		JSONObject jobj_body=new JSONObject(httpResponse.body().asString());
		String network_id=jobj_body.getJSONObject("network").getString("id");		
		Assert.assertEquals("Mismatch in network-id, Response recieved for a different city", test_network_id, network_id);
		
		//verifies if the city and country retrieved is as expected
		JSONObject jobj_location=jobj_body.getJSONObject("network").getJSONObject("location");
		Assert.assertEquals("City name validation failed",cityname ,jobj_location.get("city"));
		Assert.assertEquals("Country validation failed",countryCode,jobj_location.get("country"));
		//printing the latitude and longitude
		System.out.println("Latitude of the city : "+jobj_location.get("latitude"));
		System.out.println("Longitude of the city : "+jobj_location.get("longitude"));
	}
}
