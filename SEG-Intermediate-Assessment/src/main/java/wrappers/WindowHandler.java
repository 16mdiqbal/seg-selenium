package wrappers;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import logger.LoggerHandler;

public class WindowHandler {

	private static final Logger log = LoggerHandler.getLogger(WindowHandler.class);
	private WebDriver driver;
	
	public WindowHandler(WebDriver driver) {
		this.driver = driver;
		log.info("WindowHandler : " + this.driver.hashCode());
	}
	
	/**
	 * Method to switch to the desired window from any other window
	 * 
	 */
	public void switchToWindow(int index) {

		log.info(index);
		int windowCounter = 0;
		try {
			Set<String> windowList = this.driver.getWindowHandles();
			
			if (index < 0 || index > windowList.size())
				throw new IllegalArgumentException("Invalid Index : " + index);
			
			for (String eachWindow : windowList) {
				if (windowCounter==index) {
					driver.switchTo().window(eachWindow);
					break;
				}
				windowCounter++;
			}
		} catch (Exception e) {
			log.error("Could not switch to " +index+ " window");
		}
		log.info("switched to " +index+ " window");
	}
	
	/**
	 * Method to switch to the parent window from any other window
	 * 
	 */
	public void switchToParentWindow() {
		try {
			Set<String> windowList = this.driver.getWindowHandles();
			for (String eachWindow : windowList) {
				this.driver.switchTo().window(eachWindow);
				break;
			}
		} catch (Exception e) {
			log.error("Could not be switched to the first window.");
		} 
		log.info("Switched to parent window successfully");
	}
	
	/**
	 * Method to switch to the last window
	 * 
	 */
	public void switchToLastWindow() {
		try {
			Set<String> windowList = this.driver.getWindowHandles();
			for (String eachWindow : windowList) {
				this.driver.switchTo().window(eachWindow);
			}
		} catch (Exception e) {
			log.error("Could not be switched to the last window.");
		} 
		log.info("Switched to last window successfully");
	}
}