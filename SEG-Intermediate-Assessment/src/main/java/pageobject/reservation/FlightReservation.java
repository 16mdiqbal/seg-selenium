package pageobject.reservation;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.DropdownHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class FlightReservation {

	private static final Logger log = LoggerHandler.getLogger(FlightReservation.class);
	private WebDriver driver;
	private GenericHandlers handlers;
	private WaitHandler wait;
	private DropdownHandler dropDown;
	
	@FindBy(xpath="//input[@name='tripType' and @value='roundtrip']")
	WebElement roundTripRadioBtn;
	
	@FindBy(xpath="//input[@name='tripType' and @value='oneway']")
	WebElement singleTripRadioBtn;
	
	@FindBy(name="passCount")
	WebElement noOfPassengers;
	
	@FindBy(name="fromPort")
	WebElement departureCountry;
	
	@FindBy(name="fromMonth")
	WebElement departureMonth;
	
	@FindBy(name="fromDay")
	WebElement departureDay;
	
	@FindBy(name="toPort")
	WebElement arrivalCountry;
	
	@FindBy(name="toMonth")
	WebElement arrivalMonth;
	
	@FindBy(name="toDay")
	WebElement arrivalDay;
	
	@FindBy(xpath="//input[@name='servClass' and @value='Coach']")
	WebElement economyClass;
	
	@FindBy(xpath="//input[@name='servClass' and @value='Business']")
	WebElement businessClass;
	
	@FindBy(xpath="//input[@name='servClass' and @value='First']")
	WebElement firstClass;
	
	@FindBy(name="airline")
	WebElement airlines;
	
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement continueBtn;
	
	public FlightReservation(WebDriver driver) {
		this.driver = driver;
		log.info("FlightReservation : " + this.driver.getTitle());
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		dropDown = new DropdownHandler(this.driver);
		wait = new WaitHandler(this.driver);
		wait.waitForElement(this.continueBtn, ObjectRepository.reader.getExplicitWait());
	}
	
	public FlightReservation selectTripType(String trpType) {
		log.info("Selecting trip type as : " + trpType);
		if (trpType.contains("Round")) {
			handlers.selectElement(this.roundTripRadioBtn);
		} else {
			handlers.selectElement(this.singleTripRadioBtn);
		}
		return this;
	}
	
	public FlightReservation selectNoOfPassengers(String noOfPassenger) {
		log.info("Total :" +noOfPassenger+ " passenger selected");
		dropDown.selectVisibileText(this.noOfPassengers, noOfPassenger);
		return this;
	}
	
	public FlightReservation selectDepartureCountry(String departureCountry) {
		log.info("Selecting departure country as : " + departureCountry);
		dropDown.selectVisibileText(this.departureCountry, departureCountry);
		return this;
	}
	
	public FlightReservation selectDepartureMonth(String departureMonth) {
		log.info("Selecting departure month as : " + departureMonth);
		dropDown.selectVisibileText(this.departureMonth, departureMonth);
		return this;
	}
	
	public FlightReservation selectDepartureDay(String departureDay) {
		log.info("Selecting departure day as : " + departureDay);
		dropDown.selectVisibileText(this.departureDay, departureDay);
		return this;
	}
	
	public FlightReservation selectArrivalCountry(String arrivalCountry) {
		log.info("Selecting arrival country as : " + arrivalCountry);
		dropDown.selectVisibileText(this.arrivalCountry, arrivalCountry);
		return this;
	}
	
	public FlightReservation selectArrivalMonth(String arrivalMonth) {
		log.info("Selecting arrival month as : " + arrivalMonth);
		dropDown.selectVisibileText(this.arrivalMonth, arrivalMonth);
		return this;
	}
	
	public FlightReservation selectArrivalDay(String arrivalDay) {
		log.info("Selecting arrival day as : " + arrivalDay);
		dropDown.selectVisibileText(this.arrivalDay, arrivalDay);
		return this;
	}
	
	public FlightReservation selectServiceClass(String serviceClass) {
		log.info("Service class is :" + serviceClass);
		if (serviceClass.contains("Economy")) {
			handlers.selectElement(this.economyClass);
		} else if (serviceClass.contains("Business")){
			handlers.selectElement(this.businessClass);
		} else {
			handlers.selectElement(this.firstClass);
		}
		return this;
	}
	
	public FlightReservation selectAirlines(String airline) {
		log.info("Selecting airlines as : " + airline);
		dropDown.selectVisibileText(this.airlines, airline);
		return this;
	}
	
	public FlightReservationSuccess clickBookTckt() {
		Reporter.log(Thread.currentThread().getStackTrace()[1].getMethodName(), true);
		log.info("Clicking Continue booking button");
		String snapPath = handlers.takeSnap();
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
		handlers.clickElement(this.continueBtn);
		return new FlightReservationSuccess(this.driver);
	}
}