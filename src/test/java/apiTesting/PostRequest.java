package apiTesting;

import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
  @Test
  public void checkPost() {
	  RestAssured.baseURI ="http://restapi.demoqa.com/customer/register";
	  RequestSpecification request = RestAssured.given();
	  
	  // JSONObject is a class that represents a simple
	  // JSON. We can add Key - Value pairs using the put
	  // method
	  JSONObject requestParams = new JSONObject();
	  Random random = new Random();
	 int myRandomInt= random.nextInt(90);
	  requestParams.put("FirstName", "Joejyjt"); // Cast
	  requestParams.put("LastName", "Phanjytjtyjt");
	   
	  requestParams.put("UserName", "gunzpro21"+String.valueOf(myRandomInt));
	  requestParams.put("Password", "password11");
	  requestParams.put("Email",  "gunzpro"+String.valueOf(myRandomInt)+"@gmail.com");
	  


		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post();
		System.out.println("Status line is =>  " + response.getStatusLine());
		System.out.println("Response Body is =>  " + response.getBody().asString());
		
		int statusCode = response.getStatusCode();
		if(checkPostCode(statusCode))
			Assert.assertEquals( response.jsonPath().get("SuccessCode"), "OPERATION_SUCCESS");

		else //status =200
			Assert.assertEquals( response.jsonPath().get("FaultId"), "User already exists");
			
  }
  Boolean checkPostCode(int statusCode)
  {
	  if(statusCode==201)
		  return true;
	  return false;
  }
  
}
