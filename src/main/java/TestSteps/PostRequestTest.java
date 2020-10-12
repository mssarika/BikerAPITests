package TestSteps;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestTest {
	
	RequestSpecification httpRequest;
	boolean failed=false;
	
	//when a post request is send in place of get the request should fail
	@Given("the CityBikes api endpoint http://api.citybik.es/v2/networks")
	public void CreeateRequest()
	{
		RestAssured.baseURI="http://api.citybik.es/v2/networks";
		httpRequest=RestAssured.given();
	}
	
	@When("I makes a POST request")
	public void SendRequest()
	{
		try {Response httpResponse=httpRequest.request(Method.POST);
		System.out.println(httpResponse.asString());}
		catch(NullPointerException nle)	{failed=true;}
	}
	
	@Then("the request should fail")
	public void ValidateResponse()
	{
		Assert.assertTrue("POST request to the endpoint was successful", failed);
	}
}
