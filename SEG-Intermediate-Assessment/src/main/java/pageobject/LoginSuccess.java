package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.VerifyHandler;
import wrappers.WaitHandler;

public class LoginSuccess {

	private static final Logger log = LoggerHandler.getLogger(LoginSuccess.class);
	private WebDriver driver;
	private WaitHandler wait;
	private VerifyHandler verify;
	
	@FindBy(xpath="(//td[@valign='top'])[1]/following-sibling::td//tr[4]//td//td//tr[1]/td")
	WebElement successMessage;
	
	@FindBy(xpath="(//td[@valign='top'])[1]/following-sibling::td//tr[4]//td//td//tr[3]/td/p")
	WebElement thanksMsg;
	
	public LoginSuccess(WebDriver driver) {
		this.driver = driver;
		log.info("Welcome : " + this.driver.getTitle());
		PageFactory.initElements(this.driver, this);
		verify = new VerifyHandler(this.driver);
		wait = new WaitHandler(this.driver);
		wait.waitForElement(this.successMessage, ObjectRepository.reader.getExplicitWait());
	}
	
	public boolean verifySuccessMessage(String successMsg) {
		log.info("Verifying success message");
		return verify.isTextVerifiedByElement(this.successMessage, successMsg);
	}
	
	public boolean verifyThanksMsg(String thanksMsg) {
		log.info("Verifying Success message");
		return verify.isTextVerifiedByElement(this.thanksMsg, thanksMsg);
	}
}