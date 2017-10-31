package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.Login;
import pageobject.LoginSuccess;

public class TC_Login extends TC_Common{

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		dataFileName = "TC_Mercury_TestData";
		dataSheetName = "Login";
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test(dataProvider="fetchData")
	public void login(String uName, String pwd, String successMsg, String thanksMsg) {
		new Login(driver)
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickSignIn();
		
		LoginSuccess verify = new LoginSuccess(driver);
		
		// Verify success message
		Assert.assertTrue(verify.verifySuccessMessage(successMsg), "Success message didn't matched.");
		// verify Thanks message
		Assert.assertTrue(verify.verifyThanksMsg(thanksMsg), "Thanks message didn't matched.");
	}
	
}