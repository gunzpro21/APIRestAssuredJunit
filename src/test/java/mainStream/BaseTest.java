package mainStream;


import org.junit.BeforeClass;

import dataProvider.FileReaderManager;
import enums.E_Environment;
import io.restassured.RestAssured;
import utils.ExtentReportListner;
/**
 * <h1>Add a ping!</h1> The BaseTest extent class report and it create an environment you want to test.
 * It also provide base URL for API testing.
 * @author Joe Phan
 * @since 2022 Oct 14th
 */
public class BaseTest extends ExtentReportListner{
	String reader = null;
	@BeforeClass
	public static void baseTest() {
		E_Environment envi = FileReaderManager.getInstance().getConfigReader().getEnvironment();
		RestAssured.baseURI = FileReaderManager.getInstance().getConfigReader().getApplicationUrl(envi);
		
	}
}
