package pageobject.registration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.VerifyHandler;
import wrappers.WaitHandler;

public class RegisterSuccess {
	private static final Logger log = LoggerHandler.getLogger(RegisterSuccess.class);
	private WebDriver driver;
	private WaitHandler wait;
	private VerifyHandler verify;

	@FindBy(xpath="//body//table//tr//td[@valign='top'][2]//tr[4]//table//table//tr[3]//p[1]//font//b")
	WebElement confirmName;

	@FindBy(xpath="//body//table//tr//td[@valign='top'][2]//tr[4]//table//table//tr[3]//p[2]")
	WebElement confirmationMsg;

	@FindBy(xpath="//body//table//tr//td[@valign='top'][2]//tr[4]//table//table//tr[3]//p[3]//font//b")
	WebElement confirmUserName;

	public RegisterSuccess(WebDriver driver) {
		this.driver = driver;
		log.info("RegisterUser : " + this.driver.hashCode());
		PageFactory.initElements(this.driver, this);
		verify = new VerifyHandler(this.driver);
		wait = new WaitHandler(this.driver);
		wait.waitForElementToBeVisible(confirmUserName, ObjectRepository.reader.getExplicitWait(), ObjectRepository.reader.getPollingTimeInMilliSecond());
	}

	public boolean verifyConfirmName(String fName, String lName) {
		log.info("Verifying Name");
		String name = "Dear " + fName + " " + lName + ",";
		return verify.isTextVerifiedByElement(confirmName, name);
	}

	public boolean verifyConfirmMessage(String welcomeMsg) {
		log.info("Verifying Confirmation message");
		return verify.isTextVerifiedByElement(confirmationMsg, welcomeMsg);

	}

	public boolean verifyConfirmUserName(String userName) {
		log.info("Verifying Username");
		String uName = "Note: Your user name is " + userName + ".";
		return verify.isTextVerifiedByElement(confirmUserName, uName);
	}
}
