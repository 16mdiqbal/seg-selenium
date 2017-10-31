package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.Welcome;
import pageobject.registration.RegisterSuccess;

public class TC_Registration extends TC_Common {

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		dataFileName = "TC_Mercury_TestData";
		dataSheetName = "Registration";
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test(dataProvider = "fetchData")
	public void registerUser(String fName, String lName, String phoneNumber, String emailId, String address, 
			String city, String state, String postalCode, String country, String userName, String password,
			String confirmPwd, String welcomeMsg) {

		new Welcome(driver)
		.clickRegister()
		.enterFirstName(fName)
		.enterLastName(lName)
		.enterPhone(phoneNumber)
		.enterEmail(emailId)
		.enterAddress(address)
		.enterCity(city)
		.enterState(state)
		.enterPostalCode(postalCode)
		.selectCountry(country)
		.enterUsername(userName)
		.enterPassword(password)
		.enterConfirmPassword(confirmPwd)
		.clickSubmitButton();
		
		RegisterSuccess isSuccess = new RegisterSuccess(driver);
		
		// Confirmation of user firstname and lastname after registration 
		Assert.assertTrue(isSuccess.verifyConfirmName(fName, lName), "User details didnot matched");
		
		// confirmation of success message after successful user registration
		Assert.assertTrue(isSuccess.verifyConfirmMessage(welcomeMsg), "Confirmation messages didnot matched");
		
		// confirmation of newly created username
		Assert.assertTrue(isSuccess.verifyConfirmUserName(userName), "User name didnot matched");
		
	}
}