package pageobject.herokuapp;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import pageobject.herokuapp.alert.AlertPage;
import pageobject.herokuapp.frame.IFramePage;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class MainPage {

	private static final Logger log = LoggerHandler.getLogger(MainPage.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	
	@FindBy(linkText="JavaScript Alerts")
	WebElement javaScriptAlertLink;
	
	@FindBy(linkText="Frames")
	WebElement frameLink;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		log.info("Main Page : " + this.driver.getTitle());
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public AlertPage clickJavaScriptAlert() {
		log.info("Clicking javascript link");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), this.javaScriptAlertLink);
		Reporter.log(driver.getTitle(), true);
		String snapPath = handlers.takeSnap();
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
		handlers.clickElement(this.javaScriptAlertLink);
		return new AlertPage(this.driver);
	}
	
	public IFramePage clickFrameLink() {
		log.info("Clicking frames link");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), this.frameLink);
		Reporter.log(driver.getTitle(), true);
		String snapPath = handlers.takeSnap();
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
		handlers.clickElement(this.frameLink);
		return new IFramePage(this.driver);
	}
}
