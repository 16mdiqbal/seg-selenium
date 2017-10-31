package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import wrappers.GenericHandlers;

public class CustomListener extends GenericHandlers implements ITestListener {

	/**
	 * int CREATED = -1; 
	 * int SUCCESS = 1; 
	 * int FAILURE = 2; 
	 * int SKIP = 3; 
	 * int SUCCESS_PERCENTAGE_FAILURE = 4; 
	 * int STARTED= 16;
	 */

	@Override
	public void onFinish(ITestContext result) {
		Reporter.log("Test is finished : " + result.getName());
	}

	@Override
	public void onStart(ITestContext result) {
		Reporter.log("Test is started : " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log(result.getName() + " is failed", true);
		int status = result.getStatus();
		if (status == 2) {
			String screenShotPath = takeSnap(status);
			Reporter.log("<a href='" +screenShotPath+ "'> <img src='" +screenShotPath+ "' height='100' width='100' /> </a>");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getName() + " is skipped", true);
		int status = result.getStatus();
		if (status == 3) {
			String screenShotPath = takeSnap(status);
			Reporter.log("<a href='" +screenShotPath+ "'> <img src='" +screenShotPath+ "' height='100' width='100' /> </a>");
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getName() +" is started", true);
		int status = result.getStatus();
		if (status == 16) {
			String screenShotPath = takeSnap(status);
			Reporter.log("<a href='" +screenShotPath+ "'> <img src='" +screenShotPath+ "' height='100' width='100' /> </a>");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName() + " is successful", true);
		int status = result.getStatus();
		if (status == 1) {
			String screenShotPath = takeSnap(status);
			Reporter.log("<a href='" +screenShotPath+ "'> <img src='" +screenShotPath+ "' height='100' width='100' /> </a>");
		}
	}
}