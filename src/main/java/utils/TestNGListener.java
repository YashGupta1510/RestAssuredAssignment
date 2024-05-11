package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import pages.BasePage;

public class TestNGListener extends BasePage implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	log.info("------------------Test Passed-------------------");
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	log.error("------------------Test Failed-------------------");
        extentTest.log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	log.warn("------------------Test Skipped-------------------");
        extentTest.log(Status.SKIP, "Test Skipped");
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("------------------Test Failed But is within success percentage-------------------");
		extentTest.log(Status.WARNING, "Test Failed But is within success percentage");
	}

	@Override
	public void onStart(ITestContext context) {
		log.info("------------------"+context.getName() +" Test Started-------------------");
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("------------------"+context.getName() +" Test Finished-------------------");
	}
	

}

