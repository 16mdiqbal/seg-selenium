package wrappers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import logger.LoggerHandler;

public class WaitHandler {

	private static final Logger log = LoggerHandler.getLogger(WaitHandler.class);
	private WebDriver driver;

	public WaitHandler(WebDriver driver) {
		this.driver =  driver;
		log.info("Wait : " + this.driver.hashCode());
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit timeout : " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public WebElement waitForElementToBeClickable(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private WebDriverWait getWait(int timeOutInSeconds, long pollingEveryInMiliSec) {
		log.info("Webdriver wait : " + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(this.driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementToBeVisible(WebElement locator, int timeOutInSeconds, long pollingEveryInMiliSec) {
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
		log.info("Element found : " + locator.getText());
	}

	public void waitForElement(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element found : " + element.getText());
	}
}