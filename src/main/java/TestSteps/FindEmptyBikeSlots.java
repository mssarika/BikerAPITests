package TestSteps;

import java.util.Iterator;

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

public class FindEmptyBikeSlots {
	
	RequestSpecification httpRequest;
	Response httpResponse;
	String jsonMimeType = "application/json";
	
	@Given("the CityBikes network endpoint \"(.*)\"")
	public void CreateRequest(String url)
	{
		System.out.println("\nFunctionality under test --> Finding stations with empty bike slots");
		RestAssured.baseURI=url;
		httpRequest=RestAssured.given();
	}
	
	@When("request is send")
	public void SendRequest()
	{
		httpResponse=httpRequest.request(Method.GET);
	}
	
	@Then("user should be able to fetch all the stations with empty slots if available")
	public void ValidateRequest()
	{
		//validating the response code and content type
	    Assert.assertEquals("Request failed",HttpStatus.SC_OK, httpResponse.getStatusCode());
	    Assert.assertTrue(httpResponse.contentType().contains(jsonMimeType));
	   //gets all available stations for this particular network
	   JSONObject json_body=new JSONObject(httpResponse.body().asString());
	   JSONObject json_networks=json_body.getJSONObject("network");
	   JSONArray json_stations=json_networks.getJSONArray("stations");
	   //validates if the station node is populated
	   Assert.assertFalse("No stations found", json_stations.isEmpty());
	   System.out.println("Total number of stations retrieved : "+json_stations.length());
	   //each station is checked to see if the empty bike slot is available , if so it is listed
	   int totalStations=0;
	   Iterator<Object> it=json_stations.iterator();
	   while(it.hasNext())
	   {
		   JSONObject station=(JSONObject) it.next();
		   int count=station.getInt("empty_slots");
		   if(count!=0)
		   {
			   totalStations++;
			   System.out.println("Empty bike slot available in station : "+station.getString("name"));
		   }
			   
	   }
	   if(totalStations==0)
		   System.out.println("No stations have empty bike slots");
	}

}
