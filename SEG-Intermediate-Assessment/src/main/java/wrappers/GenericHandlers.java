package wrappers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

import browser.BrowserType;
import configreader.ObjectRepository;
import logger.LoggerHandler;
import utility.ResourceHandler;

public class GenericHandlers {

	private static final Logger log = LoggerHandler.getLogger(GenericHandlers.class);

	public static WebDriver driver;
	protected static Properties prop;
	public String sUrl,sHubUrl,sHubPort;
	public String url;
	
	public GenericHandlers() {
		/*Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sHubUrl = prop.getProperty("HUB");
			sHubPort = prop.getProperty("PORT");
			sUrl = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
					break;
				}
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ObjectRepository.reader.getImplicitWait(), TimeUnit.SECONDS);
			//driver.get(ObjectRepository.reader.getReservationUrl());
			driver.get(url);
		} catch (Exception e) {
			log.info("The browser:" + browser + " could not be launched");
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
			log.info("The data: "+data+" could not be entered in the field");
		} catch (Exception e) {
			log.info("Unknown exception occured while entering "+data+" in the field");
		}
		log.info("The data: "+data+" entered successfully in field");
	}
	
	/**
	 * This method will close all the browsers
	 */
	public void closeAllBrowsers() {
		try {
			driver.quit();
		} catch (Exception e) {
			log.info("The browser could not be closed.");
		}

	}

	/**
	 * This method will close current browser window
	 */
	public void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			log.info("The browser could not be closed.");
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
			e.printStackTrace();
			log.info("The element : "+element+" could not be clicked.");
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
			log.info("The element : "+radioElement+" could not be selected.");
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
			log.info("The mouse over by xpath : "+element+" could not be performed.");
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
			log.info("The element with xpath: "+element+" could not be found.");
		}
		return bReturn; 
	}

	/**
	 * This method will take snapshot of the browser
	 */
	public String takeSnap(int status){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		String destinationPath="";
		File srcFile = null;
		File destFile = null;
		try {
			if (status==1) { //success
				destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots\\success");
			} else if (status==2) { //failure
				destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots\\failure");
			} else if (status==3) { //skip
				destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots\\skipped");
			} else if (status==16) { //skip
				destinationPath = ResourceHandler.getResourcePath("\\src\\main\\resources\\screenshots\\start");
			}
			srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			destFile = new File(destinationPath+"\\" +number+ ".jpg");
			FileUtils.copyFile(srcFile , destFile);
		} catch (WebDriverException e) {
			log.info("The browser has been closed.");
		} catch (IOException e) {
			log.info("The snapshot could not be taken");
		}
		return destFile.getAbsolutePath();
	}
}