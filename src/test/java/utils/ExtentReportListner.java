package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
/**
 * <h1>Add a ping!</h1> ExtentReportListner extents RunListener of Junit and reuse it for ExtentReports
 * This class create a report file and configure filename, it also configure @Test methods.
 * @author Joe Phan | email: grenade.eminem@gmail.com
 * @since 2022 Oct 14th
 */
public class ExtentReportListner extends RunListener {
	protected static ExtentSparkReporter extentSpark;
	protected static ExtentReports extentReports;
	protected static ExtentTest extentTest;
	// Start and End time of the tests
	long startTime;
	long endTime;

	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private String getResultPath() {

		String resultpath = "Report " + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (!new File(resultpath).isDirectory()) {
			new File(resultpath);
		}
		return resultpath;
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		String ReportLocation = "test-output/" + getResultPath() + "/";
		// Start time of the tests
		startTime = new Date().getTime();
		String pattern = "dMMMyyyy_HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String SstartTime = simpleDateFormat.format(new Date());
		System.out.println("* Time: "+SstartTime);
		// Print the number of tests before the all tests execution.
		System.out.println("Tests started! Number of Test case: " + description.testCount() + "\n");

		// extentSpark = new ExtentSparkReporter("test-output/Extent/" +
		// "RA_Report.html");
		extentSpark = new ExtentSparkReporter(ReportLocation + "RA_Report" + SstartTime + ".html").viewConfigurer()
				.viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST ,ViewName.CATEGORY})
				.apply();
		extentSpark.config().setReportName("Rest Assure API Project");
		extentSpark.config().setTheme(Theme.DARK);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSpark);
		extentReports.setSystemInfo("Author", "Joe Phan");
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name").toString());
		//extentReports.addTestRunnerOutput("Test Execution Report");
		extentTest = extentReports.createTest(description.getMethodName());
		extentTest.info(description.getMethodName());

		System.out.println(description.getMethodName());

		super.testRunStarted(description);
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		System.out.println("\nTests finished! Number of Test case: " + result.getRunCount());
		// TODO Auto-generated method stub
		extentReports.flush();
		super.testRunFinished(result);
	}

	@Override
	public void testStarted(Description description) throws Exception {
		extentTest = extentReports.createTest(description.getMethodName());
		extentTest.info("The Test " + description.getMethodName() + " start!");
		// TODO Auto-generated method stub
		super.testStarted(description);
	}

	@Override
	public void testFinished(Description description) throws Exception {
		System.out.println("Tests finished!" + description.getMethodName());
		extentTest.info("The Test " + description.getMethodName() + " is finished!");

		super.testFinished(description);
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		extentTest.fail("Something is failed: " + failure.getMessage());
		super.testFailure(failure);
	}

	@Override
	public void testAssumptionFailure(Failure failure) {
		extentTest.warning("Some failure exist!!! ==>" + failure.getException());
		super.testAssumptionFailure(failure);
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		extentTest.skip("Test skiped: " + description.getMethodName());
		super.testIgnored(description);
		Ignore ignore = description.getAnnotation(Ignore.class);
		String ignoreMessage = String.format("@Ignore test method '%s()': '%s'", description.getMethodName(),
				ignore.value());
		System.out.println(ignoreMessage + "\n");
	}

}
