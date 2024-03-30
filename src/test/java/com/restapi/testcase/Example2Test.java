package com.restapi.testcase;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Example2Test {
	private Response response;
	private ResponseBody resBody;
	private JsonPath bodyJson;
	int userId;
	
	@BeforeClass
	public void init() {
		userId = 40;
		
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api/users";
		
		RequestSpecification req = RestAssured.given().contentType(ContentType.JSON)
				.when()
				.pathParam("userId", userId);
		
		response = req.get("/{userId}");
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
	}
	
	@Test
	public void TC01_statusCode() {
		assertEquals(response.getStatusCode(), 404, "Status Check Failed");
		//System.out.println(response.getStatusCode());
	}
	@Test
	public void TC02_MessageChecked() {
		assertEquals(true, resBody.asString().contains("message"), "Message field check Failed!");
		/*if(resBody.asString().contains("message"))
			assertEquals("User not found!", bodyJson.get("message"), "Message does not contain 'User not found!'");
		else
			assertEquals(true, false, "Message check failed!");*/
	}
	
	@Test
	public void TC03_verifyOnMessageContent() {
		String resName = bodyJson.get("message");
		if(resName == null)resName = "";
		assertEquals(resName, "User not found!" , "Message is not matching!");
	}
}
