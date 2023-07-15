package stepDefinitions;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.Reporter;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import libraries.RestUtils;

public class Steps {
	
	RequestSpecification request;
	private static Response response;
	private static final String FIRSTNAME = RandomStringUtils.randomAlphabetic(9);
	private static final String PASSWORD = RestUtils.generatePassword(9);
	private static final String EMAIL = RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz") + "@" + "gmail.com";
	private static final String UNIQUEID = UUID.randomUUID().toString();
	
	@Given("^Define URI and base path as pre-requisites$")
	public void define_URI_and_base_path_as_prerequisite() {
		
		RestAssured.baseURI = "https://vmos2.vmos-demo.com/user";
		request = RestAssured.given();
	}

	@And("^Define headers for the post API call$")
	public void define_headers_for_the_post_API_call() {
		request.header("Content-Type", "application/json");
		request.header("tenant","695a1486-80e7-4ee6-bc55-f4911944ef2a");
	}

	@When("^Create account through post api call$")
	public void create_account_through_post_api_call() {
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		Object[] subscriptions = {"marketing"};
		HashMap<String, String> profileMap = new HashMap<>();
		profileMap.put("firstName", FIRSTNAME);
		map.put("email", EMAIL);
		map.put("password", PASSWORD);
		map.put("storeUUID", UNIQUEID);
		map.put("locale", "en-GB");
		map.put("subscriptions", subscriptions);
		map.put("profile", profileMap);
		
		response = request.body(map).post("/v1/user");
	}

	@Then("Validate the create account outcomes")
	public void validate_the_create_account_outcomes() {
		
		Reporter.log("Response Body: "+response.getBody().asString(), true);
		Reporter.log("Status Line: "+response.getStatusLine(), true);
		Reporter.log("Response Time: "+response.time(), true);
		
		Assert.assertEquals(201, response.getStatusCode());
		Assert.assertEquals("HTTP/1.1 201 Created", response.getStatusLine());
		ValidatableResponse v = response.then();
	    v.time(Matchers.lessThan(10000L));
	    
	    Assert.assertEquals(JsonPath.from(response.asString()).get("payload.user.profile.firstName"), FIRSTNAME);
	    Assert.assertEquals(JsonPath.from(response.asString()).get("payload.user.email"), EMAIL);
	    
	}
	
	@When("^Login account through post api call$")
	public void login_account_through_post_api_call() {
		
		HashMap<Object, Object> loginMap = new HashMap<Object,Object>();
		loginMap.put("email", EMAIL);
		loginMap.put("password", PASSWORD);
		response = request.body(loginMap).post("/v1/auth");
	}
	
	
	@Then("Validate the login account outcomes")
	public void validate_the_login_account_outcomes() {
		Reporter.log("Response Body: "+response.getBody().asString(), true);
		Reporter.log("Status Line: "+response.getStatusLine(), true);
		Reporter.log("Response Time: "+response.time(), true);
		
		Assert.assertEquals(201, response.getStatusCode());
		Assert.assertEquals("HTTP/1.1 201 Created", response.getStatusLine());
		ValidatableResponse v = response.then();
	    v.time(Matchers.lessThan(10000L));
	    
	    Assert.assertEquals(JsonPath.from(response.asString()).get("payload.user.profile.firstName"), FIRSTNAME);
	    Assert.assertEquals(JsonPath.from(response.asString()).get("payload.user.email"), EMAIL);
	    
	}

}
