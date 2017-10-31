package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import logger.LoggerHandler;

public class AlertHandler {

	private static Logger log = LoggerHandler.getLogger(AlertHandler.class);
	private WebDriver driver;
	
	public AlertHandler(WebDriver driver) {
		this.driver = driver;
		log.info("AlertHandler : " + this.driver.hashCode());
	}
	
	private Alert getAlert() {
		Alert alert = null;
		try {
			alert =  driver.switchTo().alert();
			Thread.sleep(2000);
		} catch (NoAlertPresentException nape) {
			log.error("The alert could not be found.");
		} catch (Exception e) {
			log.error("An unknown error occured");
		}
		return alert;
	}
	
	/**
	 * This method will accept the opened alert
	 */
	public void acceptAlert() {
		try {
			getAlert().accept();
		} catch (Exception e) {
			log.error("The alert could not be accepted.");
		}
		log.info("Alert accepted");
	}
	
	/**
	 * This method will dismiss the opened alert
	 */
	public void dismissAlert() {
		log.info("dismiss alert");
		try {
			getAlert().dismiss();
		} catch (Exception e) {
			log.error("The alert could not be dismissed");
		}
	}
	
	/**
	 * This method will return the text of alert
	 * @return
	 */
	public String getAlertString() {
		String alertText="";
		try {
			alertText = getAlert().getText();
		} catch (Exception e) {
			log.error("Could not get the alert text");
		}
		log.info("Alert text is : " + alertText);
		return alertText;
	}
	
	/**
	 * This method will enter the value in prompt box
	 * @param text
	 * @return
	 */
	public void promptBox(String alertText) {
		try {
			getAlert().sendKeys(alertText);
		} catch (Exception e) {
			log.error("Could not get the alert text");
		}
		log.info("Prompt box message is : " + alertText);
	}
}