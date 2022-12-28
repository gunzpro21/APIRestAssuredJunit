package apiTesting;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonObjects.Book;

public class JsonPathTest2 {
	//obsolete URL
	//@Test
	public void JsonArrayToList() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Read all the books as a List of String. Each item in the list
	// represent a book node in the REST service Response
		List<String> allBooks = jsonPathEvaluator.getList("books.title");

	System.out.println();
	// Iterate over the list and print individual book item
		for (String book : allBooks) {
			System.out.println("Book: " + book);
		}
	}

	//@Test
	public void JsonPathUsage() throws MalformedURLException {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Read all the books as a List of String. Each item in the list
		// represent a book node in the REST service Response
		List<Book> allBooks = jsonPathEvaluator.getList("books", Book.class);

		System.out.println();
		// Iterate over the list and print individual book item
		// Note that every book entry in the list will be complete Json object of book
		for (Book book : allBooks) {
			System.out.println("Book: " + book.author);
			System.out.println("Book: " + book.description);
		}
	}

}
