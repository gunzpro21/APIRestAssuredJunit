package mainStream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;

import apiPaths.UserPaths;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonObjects.Position;
import utils.UtilFacilities;

/**
 * <h1>Add a ping!</h1> PositionTest contains all test of position module
 * 
 * @author Joe Phan | email: truong.phan@modec.com
 * @since 2023 Dec 24th
 */

@RunWith(MyControllerTest.class)
public class PositionTest extends BaseTest {
	Faker faker = new Faker();
	private String COOKIE = "csrftoken=uYzIE7tCm2IMO7ycbGsS1lrddi5MqE2h; _clck=4b2m41%7C2%7Cfi2%7C0%7C1423; AppServiceAuthSession=BueNA9RxIymR7U1R7k6m7cNX+wxaR0kBNtIXb5X+lz3k3Fy5zcLuEAs0RsgNQD9+ZekTGzHiTOBrUP4Niw111u3VTbk1MA8KcNiQZERGBjMTz1E8BEe8Xuw8Ew4ctL5YFWJ2V+ORUmE+55WbNJaJbsIRVerjKrawJR+RblGWT/SXmY5Zyxoh4hVgKiIfTh1C5lFv9ECUDwEwr6TBP1/YDZpgAYHleN7oozyPuiBaT8swHk5iaEaTbSxy36bJ/O6SOYx7EfzTLxqw13ELjspdDaw/fs2f9REtSJ1wWNzLBniY+GWq5RJ6warGY0uMDzBLHy4p0pE689tClWk7IkWde52ST4wcroDhR2fp5Ci5w50Wtgf8p9HeX5GArQm4jZf13+VTnhpq8XxGNjrhr8S/FaUum/iREjBtDyoVLfhnU9FdAIv4EHSLxnXeAIG/rcegCiDIM7zjQ5/5jojd85xZ5fqPqFjGGvl/GtV4pmq6Iva0aRcuX5TMNxRGewHAqVuGYnt4HQI8/5Ff2FSUl+AYHiA034rLIrYPgVM/fa/LE0EtrX5GWHjRncZ9zgagYFR5RmQ/cYP2jsGOFdzjy7iHBg+zTuhtbkUo/eiLgjuvKl9hF54c1dpDdgdGGcuof4o4QHL8nBMVbcEll6gIS6NWnJwHeXCl1Xthsvv45um0Hl/ydEwR1stT9CJhvNgINOdRHi4+FB1GhVhDyR8yC8+Cz6QrU0dAPAUUlsFKPPd7uAHBnr1YVxkQmiO5nExtY52kz3cc4OpvGErlXo1S65g14BqdkZdhcl6Q/5IP3eG506EnKUM83rJFwr+ygY3WpQoD4i5wsNkqFrJehEL3xuP4CTWiFmAEF9f7FDZc8sIpxSM4IPSgN6QcNkHQyEUofN7Enn6i8iVlYx4lz48XYrnSQtqEwycxGOMk+TBB65+EtvF/dsYA5fflkHXp5+VZeMOoHhFSBLF3jPrEkcU0WNmoifg4vSp2+zn78AUgAXZ/lvpuZigYI0HjDG6imiqUSTWn1paqqlmn1IrglcJaC/nFmqUKnzMhC6Av/wDvhLu2yD5HK7p30fT2W0ke5/kLTod3EndcR5uvlPaaCcT3Vu58C+mhFPyAXW+apzkW00OVRjuW8+CCJyA6E7dT4ThsxU9P;"
			+ " _clsk=1xsy68k%7C1704163358332%7C7%7C1%7Cu.clarity.ms%2Fcollect";

	@Test
	public void getPosition() {
		extentTest.assignCategory("Position");

		Response response = given().contentType(ContentType.JSON).headers("Cookie", COOKIE)
				.get(UserPaths.GET_POSITON_DETAIL);

		int statusCode = response.getStatusCode();
		extentTest.info(String.valueOf(statusCode));

		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		assertThat(statusCode, is(200));

		extentTest.pass("You get a GET request!!!");
	}

	@Test 
	/* can not get 1 API for input data for test - need more API call to input
	 * cannot obtain cookie for automated access - need to get from browser
	 * ask dev for good implement
	 * */
	public void updatePositionDetail() {
		extentTest.assignCategory("Position");

		// get current test data
		Response response = given().contentType(ContentType.JSON).headers("Cookie", COOKIE)
				.get(UserPaths.GET_POSITON_DETAIL);
		reader = response.body().asString();
		extentTest.info(MarkupHelper.createCodeBlock(reader, CodeLanguage.JSON));
		
		//Position inputPos = new Position();
		//inputPos.setCompany_id(Integer.parseInt(response.jsonPath().getString("requisition_details.company_id")));
	
		// create test data
		Position pos = new Position();
		pos.setCompany_id(Integer.parseInt(response.jsonPath().getString("requisition_details.company_id")));
		pos.setWork_location_id(Integer.parseInt(response.jsonPath().getString("requisition_details.work_location_id")));
		pos.setHome_location_id(Integer.parseInt(response.jsonPath().getString("requisition_details.home_location_id")));
		pos.setEmployment_status_id(Integer.parseInt(response.jsonPath().getString("requisition_details.employment_status_id")));
		pos.setRequestor("truong.phan@modec.com");
		pos.setRemarks("Re" + faker.name().firstName());
		pos.setDemobilisation_date("2024-12-26");
		pos.setMobilisation_date("2023-12-26");
		pos.setProject_position("test-API-19Dec2023-011");
		pos.setOrg_chart_id("chart.API." + UtilFacilities.getCurrentDate());
		Boolean budget= Boolean.parseBoolean(response.jsonPath().getString("requisition_details.budgeted"));
		budget= true? false : true;
		pos.setBudgeted(budget);
		pos.setSection_id(Integer.parseInt(response.jsonPath().getString("requisition_details.section_id")));
		pos.setDepartment("Digital & Analytics");
		pos.setCategory("1 - Project Management");
		pos.setArea("General");
		pos.setProject_id(1); //requisition_details.project_id not provided
		pos.setWork_basis(1);
		
		
		extentTest.info("input data: " + pos.toString());

		response = given().contentType(ContentType.JSON).header("Cookie", COOKIE).body(pos)
				.post(UserPaths.UPDATE_POSITION_DETAIL);
		int statusCode = response.getStatusCode();

		assertThat(statusCode, is(200));
		extentTest.pass("Update an position successfully!");

		// check test data
		//assertTrue( pos.equals(posinputPos));
	}

}
