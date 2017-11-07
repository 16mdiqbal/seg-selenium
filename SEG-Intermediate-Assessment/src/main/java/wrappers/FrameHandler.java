package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import logger.LoggerHandler;

public class FrameHandler {

	private static final Logger log = LoggerHandler.getLogger(FrameHandler.class);
	private WebDriver driver;
	
	public FrameHandler(WebDriver driver) {
		this.driver = driver;
		log.info("FrameHandler : " + driver.hashCode());
	}
	
	public void switchToFrame(WebElement frameElement) {
		try {
			driver.switchTo().frame(frameElement);
		} catch (NoSuchFrameException e) {
			log.error("The frame could not be found.");
		} catch (Exception e) {
			log.error("An unknown error occured");
		}
		log.info("switched to desired frame");
	}
	
	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error("An unknown error occured");
		}
		log.info("switched to default content");
	}
}