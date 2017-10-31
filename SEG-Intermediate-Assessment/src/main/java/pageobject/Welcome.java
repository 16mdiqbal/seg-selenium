package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import pageobject.registration.RegisterUser;
import pageobject.reservation.FlightReservation;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class Welcome {

	private static final Logger log = LoggerHandler.getLogger(Welcome.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	
	@FindBy(linkText="REGISTER")
	WebElement registerUser;
	
	@FindBy(linkText="Flights")
	WebElement flightTicketBook;
	
	public Welcome(WebDriver driver) {
		this.driver = driver;
		log.info("Welcome : " + this.driver.getTitle());
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(driver);
		wait = new WaitHandler(driver);
		//wait.waitForElement(this.registerUser, ObjectRepository.reader.getExplicitWait());
	}
	
	public RegisterUser clickRegister() {
		log.info("Clicking REGISTER");
		handlers.clickElement(registerUser);
		return new RegisterUser(driver);
	}
	
	public FlightReservation clickFlightTicketBooking() {
		log.info("Clicking flight ticket booking");
		handlers.clickElement(flightTicketBook);
		return new FlightReservation(this.driver);
	}
}
