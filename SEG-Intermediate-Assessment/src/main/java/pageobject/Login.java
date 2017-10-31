package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import pageobject.reservation.FlightReservation;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class Login {

	private static final Logger log = LoggerHandler.getLogger(Login.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	
	@FindBy(name="userName")
	WebElement userName;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@name='submit']")
	WebElement signIn;
	
	@FindBy(linkText="Flights")
	WebElement flightTicketBook;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		log.info("Welcome : " + this.driver.getTitle());
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(driver);
		wait = new WaitHandler(driver);
		wait.waitForElement(this.signIn, ObjectRepository.reader.getExplicitWait());
	}
	
	public Login enterUserName(String userName) {
		log.info("Entering username : " + userName);
		handlers.enterData(this.userName, userName);
		return this;
	}
	
	public Login enterPassword(String password) {
		log.info("Entering password : " + password);
		handlers.enterData(this.password, password);
		return this;
	}
	
	public FlightReservation clickFlightTicketBooking() {
		log.info("Clicking flight ticket booking");
		handlers.clickElement(flightTicketBook);
		return new FlightReservation(this.driver);
	}
	
	public LoginSuccess clickSignIn() {
		log.info("Clicking Sign-In");
		handlers.clickElement(signIn);
		return new LoginSuccess(this.driver);
	}
}