package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.herokuapp.MainPage;
import pageobject.herokuapp.alert.AlertPage;

public class AlertPageTest extends TC_Common {

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	/**
	 * This testcase is to handle the alert
	 */
	@Test
	public void tc_alert() {
		
		String fullName = "Iqbal";
		
		MainPage mainPage = new MainPage(driver);
		mainPage.clickJavaScriptAlert();
		
		AlertPage alertPage = new AlertPage(driver);
		alertPage.clickJsAlertButton();
		Assert.assertTrue(alertPage.verifyJsAlertMsg("You successfuly clicked an alert"), "Alert message didn't matched.");
		
		alertPage.clickJsConfirmBtn();
		Assert.assertTrue(alertPage.verifyJsAlertMsg("You clicked: Cancel"), "Alert message didn't matched.");
		
		alertPage.clickJsPropmptBtn(fullName);
		Assert.assertTrue(alertPage.verifyJsAlertMsg("You entered: "+fullName), "Alert message didn't matched.");
	}
}