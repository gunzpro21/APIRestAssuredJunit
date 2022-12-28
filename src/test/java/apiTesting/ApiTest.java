package apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import apiPaths.UserPaths;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import jsonObjects.BookingResponse;
import jsonObjects.UserInfo;
import mainStream.BaseTest;
import mainStream.MyTestRunner;
/**
 * <h1>Add a ping!</h1> The APITest give examples of all apis
 * 
 * @author Joe Phan
 * @since 2022 Oct 14th
 */


@RunWith(MyTestRunner.class)
public class ApiTest extends BaseTest{


	
	// @SuppressWarnings("deprecation")
	// @Test()
	public void myThirdRaTest() {
		Header acceptHeader = new Header("accept", "application/json");

		Response response = given().header(acceptHeader).get("https://restful-booker.herokuapp.com/booking/1");

		int statusCode = response.getStatusCode();
		String reader = response.body().asString();
		System.out.println("before: " + reader);
		// make beautiful result
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = new JsonParser().parse(reader);
		System.out.println("after: " + gson.toJson(jsonElement));
		assertThat(statusCode, is(200));
	}

	// @Test
	public void myFourthRaTest() {
		Response response = given().log().all().get("https://restful-booker.herokuapp.com/booking/1");

		String reader = response.body().asString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = new JsonParser().parse(reader);
		// System.out.println("response: "+ gson.toJson(jsonElement));
		// k co then thi eo co log, ma co then thi eo con la kieu response? :))
		ValidatableResponse myresponse = given().log().all().get("https://restful-booker.herokuapp.com/booking/1")
				.then().log().all();
		// System.out.println("check validate "+ myresponse.body());

		BookingResponse responseBody = response.as(BookingResponse.class);
		String name = responseBody.getFirstname();
		assertThat(name, is("Sally"));
	}

	// @Test death API
	public void myFourthBDD() {
		Response response = given().get("https://restful-booker.herokuapp.com/booking/1");
		String reader = response.body().asString();
		System.out.println("check validate " + reader);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement jsonElement = new JsonParser().parse(reader);
		System.out.println("response: " + gson.toJson(jsonElement));

		given().then().body("greeting.firstName", equalTo("John"))
		.and().body("greeting.lastName", equalTo("Doe"));
	}

	@Test
	public void createEmployee() {
		
		extentTest.assignCategory("Employee");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		String reader = null;
		String requestBody = "{\n" + "  \"name\": \"tammy3\",\n" + "  \"salary\": \"5000\",\n" + "  \"age\": \"20\"\n"
				+ "}";
		Response response = null;

		try {
			response = RestAssured.given().log().all().contentType(ContentType.JSON).body(requestBody).post("/create");
		} catch (Exception e) {
			e.printStackTrace();
			extentTest.warning(e.getMessage());
		}
		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		System.out.println("response body : " + reader);
		
		System.out.println("Response :" + response.asString());
		System.out.println("Status Code :" + response.getStatusCode());
		System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));
		assertEquals(response.getStatusCode(), 200);
		extentTest.pass("Create user successfully!");
	}

	@Test
	public void testFail() {
		extentTest.fail("this is fail");
		assertEquals("123", "1");
	}

	@Test
	public void testFifthRATest() {
		extentTest.info("this is RA Test");
		assertEquals(1, 1);
		
		String json = "{'foo' : 'bar', 'foos' : ['b','a','r'], 'bar' : {'foo':'bar', 'bar':false,'foobar':1234}}";
		extentTest.info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
		/*
		 * AuthPayload authPayload = new AuthPayload("admin", "password123");
		 * 
		 * Response response = given().body(authPayload).contentType("application/json")
		 * .post("https://restful-booker.herokuapp.com/auth");
		 * 
		 * String authResponse = response.getBody().print();
		 * 
		 * //logger.log(Status.INFO, authResponse); assertThat(authResponse,
		 * containsString("token"));
		 */
	}

}
