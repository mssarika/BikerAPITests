package TestSteps;

import java.util.Random;

import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseURITest {
	
	RequestSpecification httpRequest;
	Response httpResponse;
	String jsonMimeType = "application/json";
	
	@Given("the CityBikes api URI \"(.*)\"")
	public void CreateRequest(String url)
	{
		System.out.println("\nFunctionality under test --> Validating the Base URI");
		RestAssured.baseURI=url;
		httpRequest=RestAssured.given();
	}
	
	@When("the request is send")
	public void SendRequest()
	{
		httpResponse=httpRequest.request(Method.GET);
	}
	
	@Then("all the Networks available across the globe should be retrieved with details : name, href, location, id")
	public void ValidateResponse()
	{
		//validating the response code and content type
		Assert.assertEquals("Request failed",HttpStatus.SC_OK, httpResponse.getStatusCode());
	    Assert.assertTrue(httpResponse.contentType().contains(jsonMimeType));
	    //retrieving all the network nodes and validates if it is not empty
	    JSONObject json_body=new JSONObject(httpResponse.asString());
	    JSONArray json_network=json_body.getJSONArray("networks");
	    Assert.assertFalse("No networks retrieved", json_network.isEmpty());
	    System.out.println("Total number of Networks retrieved : "+json_network.length());
	    System.out.println("Choosing a random network for test");
	    int index=new Random().nextInt(json_network.length());	
	    
	    //selects a random network and gets the network endpoint, Validates if the endpoint request is successful
	    JSONObject network_node=(JSONObject) json_network.get(index);
	    System.out.println("Network_ID: "+network_node.get("id"));
	    System.out.println("Network_NAME: "+network_node.get("name"));
	    System.out.println("Network_ENDPOINT: "+network_node.get("href"));
	    System.out.println("Network_Location: "+network_node.get("location"));
	    System.out.println("Validating the Network Endpoint");
	    String url="http://api.citybik.es"+(String) network_node.get("href");
	    Assert.assertEquals("Request to the endpoint failed",HttpStatus.SC_OK,httpRequest.get(url).getStatusCode());
	    System.out.println(httpRequest.get(url).getStatusCode() + " retrieved for the endpoint");
	}
}
