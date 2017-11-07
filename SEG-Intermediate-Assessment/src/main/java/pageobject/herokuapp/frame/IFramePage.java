package pageobject.herokuapp.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class IFramePage {

	private static final Logger log = LoggerHandler.getLogger(IFramePage.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	
	@FindBy(linkText="iFrame")
	WebElement iFrameLink;
	
	public IFramePage(WebDriver driver) {
		this.driver = driver;
		log.info("IFramePage  : " + this.driver.getTitle());
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
	}
	
	public FramePage clickIframeLink() {
		log.info("Clicking IFrame link");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), this.iFrameLink);
		String snapPath = handlers.takeSnap();
		Reporter.log(this.driver.getTitle(), true);
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
		handlers.clickElement(this.iFrameLink);
		return new FramePage(this.driver);
	}
}
