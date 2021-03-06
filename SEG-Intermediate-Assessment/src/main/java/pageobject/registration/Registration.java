package pageobject.registration;

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


public class Registration {

	private static final Logger log = LoggerHandler.getLogger(Registration.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	private DropdownHandler dropdownHandlers;

	@FindBy(name="firstName")
	WebElement firstName;

	@FindBy(name="lastName")
	WebElement lastName;

	@FindBy(name="phone")
	WebElement phone;

	@FindBy(id="userName")
	WebElement eMail;

	@FindBy(name="address1")
	WebElement address;

	@FindBy(name="city")
	WebElement city;

	@FindBy(name="state")
	WebElement state;

	@FindBy(name="postalCode")
	WebElement postalCode;

	@FindBy(name="country")
	WebElement country;

	@FindBy(id="email")
	WebElement userName;

	@FindBy(name="password")
	WebElement password;

	@FindBy(name="confirmPassword")
	WebElement confirmPassword;

	@FindBy(name="submit")
	WebElement submitButton;

	public Registration(WebDriver driver) {
		this.driver = driver;
		log.info("RegisterUser : " + this.driver.hashCode());
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		dropdownHandlers = new DropdownHandler(this.driver);
		wait = new WaitHandler(this.driver);
		wait.waitForElement(submitButton, ObjectRepository.reader.getExplicitWait());
	}


	public Registration enterFirstName(String firstName) {
		log.info("Entering first name : " + firstName);
		handlers.enterData(this.firstName, firstName);
		return this;
	}

	public Registration enterLastName(String lastName) {
		log.info("Entering last name : " + lastName);
		handlers.enterData(this.lastName, lastName);
		return this;
	}

	public Registration enterPhone(String phoneNumber) {
		log.info("Entering phone number : " + phoneNumber);
		handlers.enterData(this.phone, phoneNumber);
		return this;
	}

	public Registration enterEmail(String emailId) {
		log.info("Entering address : " + emailId);
		handlers.enterData(this.eMail, emailId);
		return this;
	}

	public Registration enterAddress(String address) {
		log.info("Entering address : " + address);
		handlers.enterData(this.address, address);
		return this;
	}

	public Registration enterCity(String city) {
		log.info("Entering city :" + city);
		handlers.enterData(this.city, city);
		return this;
	}

	public Registration enterState(String state) {
		log.info("Entering state :" + state);
		handlers.enterData(this.state, state);
		return this;
	}

	public Registration enterPostalCode(String postalCode) {
		log.info("Entering postal code :" + postalCode);
		handlers.enterData(this.postalCode, postalCode);
		return this;
	}

	public Registration selectCountry(String country) {
		log.info("Selecting country : " + country);
		dropdownHandlers.selectVisibileText(this.country, country);
		return this;
	}

	public Registration enterUsername(String userName) {
		log.info("Entering username : " + userName);
		handlers.enterData(this.userName, userName);
		return this;
	}

	public Registration enterPassword(String password) {
		log.info("Entering password : " + password);
		handlers.enterData(this.password, password);
		return this;
	}

	public Registration enterConfirmPassword(String confirmPassword) {
		log.info("Entering password again : " + confirmPassword);
		handlers.enterData(this.confirmPassword, confirmPassword);
		return this;
	}

	public RegistrationSuccess clickSubmitButton() {
		Reporter.log(Thread.currentThread().getStackTrace()[1].getMethodName(), true);
		log.info("Clicking submit button");
		String snapPath = handlers.takeSnap();
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
		handlers.clickElement(this.submitButton);
		return new RegistrationSuccess(this.driver);
	}
}