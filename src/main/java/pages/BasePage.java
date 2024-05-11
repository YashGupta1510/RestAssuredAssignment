package pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.FileReader;


public class BasePage implements ISuiteListener, IInvokedMethodListener{
	
	public BasePage() {
		try {
			FileReader.readConfig();
			BASE_URL = FileReader.props.getProperty("BaseUrl");
			KEY = FileReader.props.getProperty("Key");
			TOKEN = FileReader.props.getProperty("Token");
			AUTH_PARAM = "?key="+KEY+"&token="+TOKEN;
		} catch (IOException e) {
			System.out.println("File not Found");
		}
	}
	
	
	protected static Logger log = LogManager.getLogger();
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	public static RequestSpecification request;
	public static Response response;
	
	public static String BASE_URL;
	protected static String KEY;
	protected static String TOKEN;
	protected static String AUTH_PARAM;
	
	
	public void setReport() {
		log.info("\n\n"
				+ ""
				+ "          __   _,--=\"=--,_   __\r\n"
				+ "         /  \\.\"    .-.    \"./  \\\r\n"
				+ "        /  ,/  _   : :   _  \\/` \\\r\n"
				+ "        \\  `| /o\\  :_:  /o\\ |\\__/\r\n"
				+ "         `-'| :=\"~` _ `~\"=: |\r\n"
				+ "            \\`     (_)     `/\r\n"
				+ "     .-\"-.   \\      |      /   .-\"-.\r\n"
				+ ".---{     }--|  /,.-'-.,\\  |--{     }---.\r\n"
				+ " )  (_)_)_)  \\_/`~-===-~`\\_/  (_(_(_)  (\r\n"
				+ "(        						        )\r\n"
				+ " )         A NEW RUN BEGINS HERE       (\r\n"
				+ "(           					       )\r\n"
				+ "'---------------------------------------'\n\n ");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report/testReport.html");
		extent = new ExtentReports();
		sparkReporter.config().setDocumentTitle("Simple Automation Report");
		sparkReporter.config().setReportName("RestAssured API Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setCss(".badge{font-size:100%}");
		sparkReporter.config().setTimeStampFormat("EEEE, dd MMMM, yyyy, hh:mm a '('zzz')'");
		extent.attachReporter(sparkReporter);
	}
	
	
	public void finishReport() {
		extent.flush();
	}


	@Override
	public void onStart(ISuite suite) {
		setReport();
	}


	@Override
	public void onFinish(ISuite suite) {
		finishReport();		
	}


	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			extentTest = extent.createTest(method.getTestMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {		
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(result.getTestName());
		} else {
			extentTest.skip(result.getTestName());
		}
		extent.removeTest(result.getTestName());
		
	}

}
