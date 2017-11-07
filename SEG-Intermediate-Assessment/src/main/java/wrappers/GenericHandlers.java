package wrappers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import browser.BrowserType;
import configreader.ObjectRepository;
import logger.LoggerHandler;
import utility.ResourceHandler;
import utility.UtilityClass;

public class GenericHandlers {

	private static final Logger log = LoggerHandler.getLogger(GenericHandlers.class);

	public static WebDriver driver;
	protected static Properties prop;
	public String sUrl,sHubUrl,sHubPort;
	public String url;

	public GenericHandlers() {
		
	}

	public GenericHandlers(WebDriver driver) {
		this.driver = driver;
	}

	public void unloadObjects() {
		prop = null;
	}

	/**
	 * This method will launch the browser in local machine and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser) {
		invokeApp(browser,false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximise the browser and set the
	 * wait for 30 seconds and load the url 
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser, boolean bRemote) {

		log.info("Launching " + browser + " browser");
		String osName = System.getProperty("os.name");
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser.toString());

			if (osName.contains("Window")) {
				dc.setPlatform(Platform.WINDOWS);
			} else if (osName.contains("Mac")) {
				dc.setPlatform(Platform.MAC);
			}

			// this is for grid run
			if(bRemote)
				this.driver = new RemoteWebDriver(new URL("http://"+sHubUrl+":"+sHubPort+"/wd/hub"), dc);
			else{ // this is for local run
				switch (browser) {
				case Chrome:
					if (osName.contains("Window")) {
						System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\src\\main\\resources\\driver\\chromedriver.exe"));
					} else if (osName.contains("Mac")) {
						System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\src\\main\\resources\\driver\\chromedriver"));
					}
					driver = new ChromeDriver();
					break;
				case Firefox:
					if (osName.contains("Window")) {
						System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\src\\main\\resources\\driver\\geckodriver.exe"));
					} else if (osName.contains("Mac")) {
						System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\src\\main\\resources\\driver\\geckodriver"));
					}
					driver = new FirefoxDriver();
					break;
				case Iexplorer:
					break;
				default:
					throw new RuntimeException("Provided browser name is not correct");
				}
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ObjectRepository.reader.getImplicitWait(), TimeUnit.SECONDS);
			driver.get(url);
		} catch (Exception e) {
			log.error("The browser:" + browser + " could not be launched");
			log.error(e.getStackTrace());
		}
		log.info("The browser:" + browser + " launched successfully");
	}

	/**
	 * This method will enter the value to the text field
	 * @param element
	 * @param data
	 */
	public void enterData(WebElement element, String data) {
		try {
			element.clear();
			element.sendKeys(data);	
		} catch (NoSuchElementException e) {
			log.error("The data: "+data+" could not be entered in the field");
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("Unknown exception occured while entering "+data+" in the field");
			log.error(e.getStackTrace());
		}
		log.info("The data: "+data+" entered successfully in field");
	}


	/**
	 * This method will enter the value to the text area
	 * @param element
	 * @param data
	 */
	public void enterTextAreaData(WebElement element, String data) {
		try {
			element.sendKeys(data);	
		} catch (NoSuchElementException e) {
			log.error("The data: "+data+" could not be entered in the field");
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("Unknown exception occured while entering "+data+" in the field");
			log.error(e.getStackTrace());
		}
		log.info("The data: "+data+" entered successfully in field");
	}

	/**
	 * This method will close all the browsers
	 */
	public void closeAllBrowsers() {
		try {
			if (driver!=null) {
				driver.quit();
			}
		} catch (Exception e) {
			log.error("The browser could not be closed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will close current browser window
	 */
	public void closeBrowser() {
		try {
			if (driver!=null) {
				driver.close();
			}
		} catch (Exception e) {
			log.error("The browser could not be closed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method clicks the WebElement
	 * @param element
	 */
	public void clickElement(WebElement element) {
		try{
			log.info("The element : "+element+" is clicked.");
			element.click();
		} catch (Exception e) {
			log.error("The element : "+element+" could not be clicked.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will check if the radio button is not selected, then select the radio button
	 * @param radioElement
	 */
	public void selectElement(WebElement radioElement) {
		boolean isSelected = false;
		try {
			isSelected = radioElement.isSelected();
			if (!isSelected) {
				radioElement.click();
			}
		} catch (Exception e) {
			log.error("The element : "+radioElement+" could not be selected.");
			log.error(e.getStackTrace());
		}
		log.info("The element : "+radioElement+" is selected.");
	}

	/**
	 * This method will mouse over on the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element to be moused over
	 */
	public void mouseOver(WebElement element) {
		try{
			new Actions(driver).moveToElement(element).build().perform();
		} catch (Exception e) {
			log.error("The mouse over by xpath : "+element+" could not be performed.");
			log.error(e.getStackTrace());
		}
		log.info("The mouse over by xpath : "+element+" is performed.");
	}

	/**
	 * This method will return the text of the element using xpath as locator
	 * @param xpathVal  The xpath (locator) of the element
	 */
	public String getTextByXpath(WebElement element){
		String bReturn = "";
		try{
			return element.getText();
		} catch (Exception e) {
			log.error("The element with xpath: "+element+" could not be found.");
			log.error(e.getStackTrace());
		}
		return bReturn; 
	}

	/**
	 * This method will take snapshot of the browser
	 */
	public String takeSnap(){
		long number = UtilityClass.getRandomNumber();
		String destinationPath="";
		File srcFile = null;
		File destFile = null;
		String screenShotPath ="";
		try {
			destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots");
			srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			destFile = new File(destinationPath+"\\" +number+ ".jpg");
			FileUtils.copyFile(srcFile , destFile);
			screenShotPath = destFile.getAbsolutePath();
		} catch (WebDriverException e) {
			log.error(e.getStackTrace());
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		return screenShotPath;
	}

	/**
	 * This method will take snapshot of full screen using Robot class
	 */
	public String takeFullSnap() {
		long number = UtilityClass.getRandomNumber();
		String destinationPath= "";
		String screenShotPath = "";
		try {
			Robot robot = new Robot();
			String format = "jpg";
			destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots"); 
			File destFile = new File(destinationPath+"\\" +number+ "." + format);
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, destFile);
			screenShotPath = destFile.getAbsolutePath(); 
		} catch (AWTException | IOException ex) {
			log.error(ex.getStackTrace());
		} catch (Exception e) {
			log.error(e.getStackTrace());
		}
		return screenShotPath;
	}
}