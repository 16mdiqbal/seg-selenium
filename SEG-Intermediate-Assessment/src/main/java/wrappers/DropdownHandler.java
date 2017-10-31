package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import logger.LoggerHandler;

public class DropdownHandler {

	private static final Logger log = LoggerHandler.getLogger(DropdownHandler.class);
	private WebDriver driver;
	
	public DropdownHandler(WebDriver driver) {
		this.driver = driver;
		log.info("DropdownHandler : " + this.driver.hashCode());
	}
	
	/**
	 * This method will select the drop down value 
	 * @param element The webelement of the dropdown
	 * @param value The value to be selected (visibletext) from the dropdown 
	 */
	public void selectVisibileText(WebElement element, String value) {
		try{
			new Select(element).selectByVisibleText(value);
			log.info("The element : "+element+" is selected with value : "+value);
		} catch (Exception e) {
			log.info("The value: "+value+" could not be selected.");
		}
	}

	/**
	 * This method will select the drop down value using id as locator
	 * @param id The id (locator) of the drop down element
	 * @param value The value to be selected (index) from the dropdown 
	 */
	public void selectIndexById(WebElement element, int value) {
		try{
			new Select(element).selectByIndex(value);
			log.info("The element with id: "+element+" is selected with index :"+value);
		} catch (Exception e) {
			log.info("The index: "+value+" could not be selected.");
		}
	}
}