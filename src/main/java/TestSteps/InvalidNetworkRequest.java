package TestSteps;

import org.apache.http.HttpStatus;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InvalidNetworkRequest {

	RequestSpecification httpRequest;
	Response httpResponse;
	String jsonMimeType = "application/json";
	String test_network_id;
	
	//when a get request is send to an invalid network endpoint 404 error should be retrieved
	@Given("an invalid network endpoint \"(.*)\"")
	public void CreateRequest(String url)
	{
		RestAssured.baseURI=url;
		httpRequest=RestAssured.given();
	}

	@When("a request is made")
	public void SendRequest()
	{
		httpResponse=httpRequest.request(Method.GET);
	}	
	
	@Then("the request should return 404 NOT FOUND error")
	public void ValidateResponse()
	{
		Assert.assertEquals("Unexpected Response",HttpStatus.SC_NOT_FOUND, httpResponse.getStatusCode());
	}
}
