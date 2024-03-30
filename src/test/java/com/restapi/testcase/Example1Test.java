package com.restapi.testcase;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class Example1Test {

	private Response response;
	private ResponseBody resBody;
	private JsonPath bodyJson;
	int userId;
	
	@BeforeClass
	public void init() {
		userId = 2;
		
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
	public void TC01_StatusCode() {
		
		assertEquals(response.getStatusCode(),200, "Status Check Failed");
		//System.out.println(response.getStatusCode());
	}
	
	@Test
	public void TC02_idCheck() {
		
		assertTrue(resBody.asString().contains("id"), "id field check Failed!");
		
	}
	
	@Test
	public void TC03_emailCheck() {
		assertTrue(resBody.asString().contains("email"), "email field check Failed!");
	}
	@Test
	public void TC04_firstNameCheck() {
		assertTrue(resBody.asString().contains("first_name"), "first_name field check Failed!");
	}
	
	@Test
	public void TC05_lastNameCheck() {
		assertTrue(resBody.asString().contains("last_name"), "last_name field check Failed!");
	}
	@Test
	public void TC06_avatarCheck() {
		assertTrue(resBody.asString().contains("avatar"), "avatar field check Failed!");
	}
	@Test
	public void TC07_compareIdAndUserId() {
		int resId = bodyJson.getInt("data.id");
		assertEquals(resId, userId, "id is not matching");
		//System.out.println(resId);
	}
	@AfterClass
	public void aftertest() {
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}
}
