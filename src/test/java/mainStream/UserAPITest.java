package mainStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;

import apiPaths.UserPaths;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonObjects.UserInfo;
/**
 * <h1>Add a ping!</h1> UserAPI contains all test of user module
 * 
 * @author Joe Phan | email: grenade.eminem@gmail.com
 * @since 2022 Oct 14th
 */
@RunWith(MyControllerTest.class)
public class UserAPITest extends BaseTest {
	@Test
	public void getListUserInFirstPage() {
		extentTest.assignCategory("User");
		Response response = given().get(UserPaths.GET_LIST_OF_USERS);
		int statusCode = response.getStatusCode();
		extentTest.info(String.valueOf(statusCode));

		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		assertThat(statusCode, is(200));

		extentTest.pass("You get a GET request!!!");
	}

	//@Test
	public void getSingleUserNotFound() {
		extentTest.assignCategory("User");
		Response response = given().get(UserPaths.GET_USER_NOT_FOUND);
		int statusCode = response.getStatusCode();
		String reader = null;
		extentTest.info(String.valueOf(statusCode));

		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		assertThat(statusCode, is(404));

		extentTest.pass("GET request User is not found!");
	}
	
	//@Test
	public void getValidSingleUser() {
		extentTest.assignCategory("User");
		Response response = given().get(UserPaths.GET_VALID_USER);
		int statusCode = response.getStatusCode();
		extentTest.info(String.valueOf(statusCode));

		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		assertThat(statusCode, is(200));

		extentTest.pass("Get an User successfully!");
	}

	@Test
	public void updateUser() {
		extentTest.assignCategory("User");
		Faker faker = new Faker();
		UserInfo userInfo = new UserInfo(faker);
		
		extentTest.info("User data: " + userInfo.toString());
		
		Response response = given().contentType(ContentType.JSON).body(userInfo).put(UserPaths.UPDATE_USER);
		int statusCode = response.getStatusCode();

		extentTest.info(String.valueOf(statusCode));
		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		
		UserInfo responseBody = response.as(UserInfo.class);
		
		extentTest.info("Sent data: " +userInfo.toString()+" | Response: "+responseBody.toString());
		assertTrue(userInfo.equals(responseBody));
		extentTest.pass("Update an User successfully!");
	}
	
	@Test
	public void createUser() {
		extentTest.assignCategory("User");
		Faker faker = new Faker();
		UserInfo userInfo = new UserInfo(faker);
		
		Response response = given().contentType(ContentType.JSON).body(userInfo).post(UserPaths.CREATE_A_USER);
		int statusCode = response.getStatusCode();

		extentTest.info(String.valueOf(statusCode));
		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));

		assertThat(statusCode, is(201));
		extentTest.pass("Create user successfully!!!");
	}
}
