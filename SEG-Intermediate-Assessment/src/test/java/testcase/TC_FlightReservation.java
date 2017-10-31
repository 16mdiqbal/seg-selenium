package testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.Welcome;
import pageobject.reservation.FlightReservationSuccess;

public class TC_FlightReservation extends TC_Common {

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		dataFileName = "TC_Mercury_Reservation";
		dataSheetName = "Reservation";
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test(dataProvider="fetchData")
	public void flightReservation(String tripType, String noOfPassenger, 
			String departureCountry, String departureMonth, String departureDay, 
			String arrivalCountry, String arrivalMonth, String arrivalDay, 
			String serviceClass, String airline, String confirmationMessage) {
		
		new Welcome(driver)
		.clickFlightTicketBooking()
		.selectTripType(tripType)
		.selectNoOfPassengers(noOfPassenger)
		.selectDepartureCountry(departureCountry)
		.selectDepartureMonth(departureMonth)
		.selectDepartureDay(departureDay)
		.selectArrivalCountry(arrivalCountry)
		.selectArrivalMonth(arrivalMonth)
		.selectArrivalDay(arrivalDay)
		.selectServiceClass(serviceClass)
		.selectAirlines(airline)
		.clickBookTckt();
		
		FlightReservationSuccess flightReservation = new FlightReservationSuccess(driver);
		Assert.assertTrue(flightReservation.verifyConfirmationHeader(confirmationMessage), "Reservation confirmation message didn't matched");
		
		flightReservation.clickBackToHomeBtn();
	}
}
