package apiTesting;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jsonObjects.Book;

public class JsonPathTest {

	@Test
	public void EadJsonArrayToList() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
		RequestSpecification request = RestAssured.given();

		Response response = request.get();
		System.out.println("Response Body -> " + response.body().asString());

		// Using JsonPath we can convert an Array of Json objects into
		// List of Class Type representing the Json Object.
		// In the below code we can use JsonPath.getList(<NodeName>) method
		// to get a list of Books.
		List<Book> books = response.jsonPath().getList("books", Book.class);
		System.out.println("Books as String " + books.toString());

		for (Book book : books) {
			System.out.println("Author name of book: " + book.author);
		}

		// We can convert the Json Response directly into a Java Array by using
		// JsonPath.getObject method. Here we have to specify that we want to
		// deserialize the Json into an Array of Book. This can be done by specifying
		// Book[].class as the second argument to the getObject method.
		Book[] books2 = response.jsonPath().getObject("books", Book[].class);
		System.out.println();
		for (Book book : books2) {
			System.out.println("Book title " + book.title);
		}
		assertEquals("", "");
	}
}
