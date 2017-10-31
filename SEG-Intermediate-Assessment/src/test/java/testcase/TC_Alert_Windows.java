package testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.alert.AlertPage;

public class TC_Alert_Windows extends TC_Common{

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test
	public void tc_alert() {
		
		String fullName = "Iqbal";
		String jsAlertMsg = "You entered: "+fullName;
		
		AlertPage alertPage = new AlertPage(driver);
		alertPage.clickJavaScriptAlert();
		
		alertPage.clickJsAlertButton();
		alertPage.verifyJsAlertMsg("You successfuly clicked an alert");
		
		alertPage.clickJsConfirmBtn();
		alertPage.verifyJsAlertMsg("You clicked: Cancel");
		
		alertPage.clickJsPropmptBtn(fullName);
		alertPage.verifyJsAlertMsg(jsAlertMsg);
	}
}
