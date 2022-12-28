package apiTesting;


import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

	@Test
	public void GetWeatherDetails() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city/Hyderabad";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET);

		String responseBody = response.getBody().asString();
		System.out.println("Response status line is =>  " + response.getStatusLine().toString());
		System.out.println("Response Body is =>  " + responseBody);

		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */);

		// Get the status line from the Response and store it in a variable called
		// statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /* actual value */, "HTTP/1.1 200 OK" /* expected value */,
				"Correct status code returned");

		Headers allHeaders = response.headers();
		System.out.println("-------------------------------------------------------------------------------------");
		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}

		// Header named Content-Type
		String contentType2 = response.header("Content-Type");
		Assert.assertEquals(contentType2 /* actual value */, "application/json" /* expected value */);

		// Header named Server
		String serverType2 = response.header("Server");
		Assert.assertEquals(serverType2 /* actual value */, "nginx" /* expected value */);

		// Reader header of a give name. In this line we will get
		String contentEncoding2 = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding2 /* actual value */, "gzip" /* expected value */);

		
		
		System.out.println("--------------------------------------http-request-----------------------------------------------");
		Response myResponse = httpRequest.get();
		System.out.println("This is header: " + myResponse.getHeaders());
		String contentType = myResponse.header("Content-Type");
		System.out.println("\nContent-Type value: " + contentType);

		// Reader header of a give name. In this line we will get
		// Header named Server
		System.out.println("-------------------------------------------------------------------------------------");
		String serverType = myResponse.header("Server");
		System.out.println("--Server value: " + serverType);

		System.out.println("-------------------------------------------------------------------------------------");
		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String acceptLanguage = myResponse.header("Content-Encoding");
		System.out.println("--Content-Encoding: " + acceptLanguage);
		
		System.out.println("--------------------------------- ^_^ ------------- ^_^ --------------------------------------");
		System.out.println("cuc shit all : "+response.toString());
	}

}
