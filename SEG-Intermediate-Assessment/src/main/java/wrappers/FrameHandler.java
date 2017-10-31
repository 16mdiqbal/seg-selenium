package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import logger.LoggerHandler;

public class FrameHandler {

	private static final Logger log = LoggerHandler.getLogger(FrameHandler.class);
	private WebDriver driver;
	public FrameHandler(WebDriver driver) {
		log.info("FrameHandler : " + driver.hashCode());
		this.driver = driver;
	}
}
