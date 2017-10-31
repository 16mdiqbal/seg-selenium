package wrappers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import logger.LoggerHandler;

public class VerifyHandler {

	private static final Logger log = LoggerHandler.getLogger(VerifyHandler.class);
	private WebDriver driver;
	public VerifyHandler(WebDriver driver) {
		log.info("VerifyHandler : " + driver.hashCode());
		this.driver = driver;
	}
	
	/**
	 * This method will verify the title of the browser 
	 * @param title - The expected title of the browser
	 */
	public boolean verifyTitle(String title){
		boolean bReturn = false;
		try{
			if (driver.getTitle().equalsIgnoreCase(title)){
				log.info("The title of the page matches with the value :"+title);
				bReturn = true;
			}else
				System.out.println();
			log.info("The title of the page:"+driver.getTitle()+" did not match with the value :"+title);
		}catch (Exception e) {
			log.info("Unknown exception occured while verifying the title");
		}
		return bReturn;
	}

	/**
	 * This method will verify the given text matches in the element text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 * @return isVerified - returns true if the text matches or else returns false 
	 */
	public boolean isTextVerifiedByElement(WebElement element, String text){
		boolean isTextVerified = false;
		try {
			String sText = element.getText().trim();
			if (sText.equalsIgnoreCase(text)){
				isTextVerified = true;
				log.info("The text: "+sText+" matches with the value : "+text);
			}else{
				log.info("The text: "+sText+" did not match with the value : "+text);
			}
		}catch (Exception e) {
			log.info("Unknown exception occured while verifying the title");
		}
		return isTextVerified;
	}

	/**
	 * This method will verify the given text is available in the element text
	 * @param xpath - The locator of the object in xpath
	 * @param text  - The text to be verified
	 */
	public boolean isTextVerifiedContainsElement(WebElement element, String text){
		boolean isTextVerified = false;
		try{
			String sText = element.getText().trim();
			if (sText.contains(text)){
				isTextVerified = true;
				log.info("The text: "+sText+" contains the value :"+text);
			}else{
				log.info("The text: "+sText+" did not contain the value :"+text);
			}
		}catch (Exception e) {
			log.info("Unknown exception occured while verifying the title");
		}
		return isTextVerified;
	}
}