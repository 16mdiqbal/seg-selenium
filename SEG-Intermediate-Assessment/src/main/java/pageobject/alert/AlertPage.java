package pageobject.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.AlertHandler;
import wrappers.GenericHandlers;
import wrappers.VerifyHandler;
import wrappers.WaitHandler;

public class AlertPage {

	private static final Logger log = LoggerHandler.getLogger(AlertPage.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	private AlertHandler alert;
	private VerifyHandler verify;
	
	@FindBy(linkText="JavaScript Alerts")
	WebElement javaScriptAlert;
	
	@FindBy(xpath="//button[text()='Click for JS Alert']")
	WebElement jsAlertBtn;
	
	@FindBy(id="result")
	WebElement jsAlertMsg;
	
	@FindBy(xpath="//button[text()='Click for JS Confirm']")
	WebElement jsConfirmBtn;
	
	@FindBy(xpath="//button[text()='Click for JS Prompt']")
	WebElement jsPropmptBtn;
	
	public AlertPage(WebDriver driver) {
		this.driver = driver;
		log.info("AlertPage : " + this.driver.getTitle());
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
		alert = new AlertHandler(this.driver);
		verify = new VerifyHandler(this.driver);
	}
	
	public AlertPage clickJavaScriptAlert() {
		log.info("Clicking javascript alert button");
		handlers.clickElement(this.javaScriptAlert);
		return this;
	}
	
	public AlertPage clickJsAlertButton() {
		log.info("Clicking JS Alert Button");
		wait.waitForElement(this.jsAlertBtn, ObjectRepository.reader.getExplicitWait());
		handlers.clickElement(this.jsAlertBtn);
		alert.acceptAlert();
		return this;
	}
	
	public AlertPage clickJsConfirmBtn() {
		log.info("Clicking JS Confirm button");
		handlers.clickElement(this.jsConfirmBtn);
		alert.dismissAlert();
		return this;
	}
	
	public AlertPage clickJsPropmptBtn(String promptBoxTxt) {
		log.info("Clicking JS Prompt button");
		handlers.clickElement(this.jsPropmptBtn);
		alert.promptBox(promptBoxTxt);
		return this;
	}
	
	public boolean verifyJsAlertMsg(String jsAlertMsg) {
		log.info("Verifying JS alert message");
		return verify.isTextVerifiedByElement(this.jsAlertMsg, jsAlertMsg);
	}
}
