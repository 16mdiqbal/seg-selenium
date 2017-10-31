package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import logger.LoggerHandler;

public class BrowserHandler {

	private Logger log = LoggerHandler.getLogger(BrowserHandler.class);
	private WebDriver driver;

	public BrowserHandler(WebDriver driver) {
		this.driver = driver;
		log.debug("BrowserHelper : " + this.driver.hashCode());
	}
	
	public void goBack() {
		log.info("Going back to previous tab");
		driver.navigate().back();
	}

	public void goForward() {
		log.info("moving to next tab");
		driver.navigate().forward();
	}

	public void refresh() {
		log.info("refresh the current page in browser");
		driver.navigate().refresh();
	}
}
