package demo;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import data.UserInfo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Example2Test {
	
	private Response response;
	private UserInfo user;
	private ResponseBody resBody;
	private JsonPath bodyJson;
	
	public void init() {
		user = new UserInfo(null, "nam", 24, "Ky su");
		
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api/users"; 
		
		RequestSpecification req = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.body(user);
		
		response = req.post();
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();		
	}
	@Test
	public void TC01_StatusCodeTest() {
		assertEquals(404, response.getStatusCode(), "Status Check Failed!");
	}
	@Test
	public void TC02_MessageChecked() {
		assertEquals(true, resBody.asString().contains("message"), "Message field check Failed!");
	}
	@Test
	public void TC03_verifyOnMessageContainName() {
		String resName = bodyJson.get("message");
		if (null == resName) resName = "";
        assertEquals(true, resName.contains("name"),"Message not contain invalid field!");

	}
	@AfterClass
	public void afterTest() {
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
	}
}
