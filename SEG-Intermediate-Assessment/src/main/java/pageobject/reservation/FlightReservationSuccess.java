package pageobject.reservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import pageobject.Welcome;
import wrappers.GenericHandlers;
import wrappers.VerifyHandler;
import wrappers.WaitHandler;

public class FlightReservationSuccess {

	private static final Logger log = LoggerHandler.getLogger(FlightReservationSuccess.class);
	private WebDriver driver;
	private GenericHandlers handlers;
	private WaitHandler wait;
	private VerifyHandler verify;
	
	@FindBy(xpath="//body//table//tr//td[@valign='top'][2]//tr[4]//table//table//tr//td//b")
	WebElement confirmationHeader;
	
	@FindBy(xpath="")
	WebElement information;
	
	@FindBy(xpath="//td[@align='CENTER']//a")
	WebElement backHomeBtn;
	
	public FlightReservationSuccess(WebDriver driver) {
		this.driver = driver;
		log.info("FlightReservationSuccess : " + this.driver.getTitle());
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		verify = new VerifyHandler(this.driver);
		wait = new WaitHandler(this.driver);
		wait.waitForElement(this.backHomeBtn, ObjectRepository.reader.getExplicitWait());
	}
	
	
	public boolean verifyConfirmationHeader(String confirmationMessage) {
		log.info("Verifying confirmation message");
		return verify.isTextVerifiedByElement(this.confirmationHeader, confirmationMessage);
	}
	
	public Welcome clickBackToHomeBtn() {
		log.info("Clicking back to home button");
		handlers.clickElement(this.backHomeBtn);
		return new Welcome(this.driver);
	}
}
