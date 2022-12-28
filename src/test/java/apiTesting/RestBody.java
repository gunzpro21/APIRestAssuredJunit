package apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestBody {
	
  @Test
  public void VerifyCityInJsonResponse()
  {
  	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
  	RequestSpecification httpRequest = RestAssured.given();
  	Response response = httpRequest.get("/Hyderabad");
   
  	System.out.println("================ ++++++++++  =====================");
  	System.out.println("Response Body is =>  " + response.getBody().asString());
  	// First get the JsonPath object instance from the Response interface
  	JsonPath jsonPathEvaluator = response.jsonPath();
   
  	// Then simply query the JsonPath object to get a String value of the node
  	// specified by JsonPath: City (Note: You should not put $. in the Java code)
  	String city = jsonPathEvaluator.get("City");
   
  	// Let us print the city variable to see what we got
  	System.out.println("City received from Response " + city);
  	System.out.println("check huminity : " + jsonPathEvaluator.get("Humidity"));
  //	System.out.println("jasonpath : " + jsonPathEvaluator.getBoolean(city));
   
  	// Validate the response
  	Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
  	
  	System.out.println("================ ++++++++++  =====================");
  	// Let us print the city variable to see what we got
 	System.out.println("City received from Response " + jsonPathEvaluator.get("City"));
  
 	// Print the temperature node
 	System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));
  
 	// Print the humidity node
 	System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));
  
 	// Print weather description
 	System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));
  
 	// Print Wind Speed
 	System.out.println("WindSpeed received from Response " + jsonPathEvaluator.get("WindSpeed"));
  
 	// Print Wind Direction Degree
 	System.out.println("WindDirectionDegree received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
   
  }
}
