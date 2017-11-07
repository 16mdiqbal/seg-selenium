package pageobject.herokuapp.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.FrameHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class FramePage {

	private static final Logger log = LoggerHandler.getLogger(FramePage.class);
	private WebDriver driver;
	private WaitHandler wait;
	private GenericHandlers handlers;
	private FrameHandler frame;
	
	@FindBy(xpath="//button[@role='presentation']/span[text()='File']")
	WebElement fileMenuButton;
	
	@FindBy(xpath="//span[text()='New document']")
	WebElement newDocumentLink;
	
	@FindBy(xpath="(//iframe)[1]")
	WebElement textEditorFrame;
	
	@FindBy(id="tinymce")
	WebElement textArea;
	
	@FindBy(xpath="//span[text()='Format']")
	WebElement header_formatMenuButton; 
	
	@FindBy(xpath="(//span[text()='Formats'])[2]")
	WebElement header_formatsLink;
	
	@FindBy(xpath="(//span[text()='Formats'])[1]")
	WebElement formatsButton;
	
	@FindBy(xpath="//span[text()='Headings']")
	WebElement headingsLink;
	
	@FindBy(xpath="//span[text()='Heading 1']")
	WebElement heading1Link;
	
	@FindBy(xpath="(//span[text()='Inline'])[2]")
	WebElement inlineLink;
	
	@FindBy(xpath="(//span[text()='Italic'])[2]")
	WebElement italicLink;
	
	@FindBy(xpath="//div[@aria-label='Italic']/button")
	WebElement italicButton;
	
	@FindBy(xpath="//div[@aria-label='Bullet list']/button")
	WebElement bulletButton;
	
	public FramePage(WebDriver driver) {
		this.driver = driver;
		log.info("FramePage  : " + this.driver.getTitle());
		PageFactory.initElements(driver, this);
		handlers = new GenericHandlers(this.driver);
		wait = new WaitHandler(this.driver);
		frame = new FrameHandler(this.driver);
	}
	
	public FramePage clickFileButton() {
		log.info("Clicking File button");
		wait.waitForElementToBeClickable(ObjectRepository.reader.getExplicitWait(), this.fileMenuButton);
		handlers.clickElement(this.fileMenuButton);
		return this;
	}
	
	public FramePage clickNewDocumentLink() {
		log.info("Clicking New Document Link");
		handlers.clickElement(this.newDocumentLink);
		return this;
	}
	
	public FramePage enterTextAreaHeaderMessage(String message) {
		log.info("Entering text area header message");
		frame.switchToFrame(this.textEditorFrame);
		handlers.enterTextAreaData(this.textArea, message + Keys.chord(Keys.SHIFT, Keys.HOME));
		frame.switchToDefaultContent();
		return this;
	}
	
	public void clickFormatButton() {
		handlers.clickElement(this.header_formatMenuButton);
	}
	
	public void clickFormatsLink() {
		handlers.clickElement(this.header_formatsLink);
	}
	
	public void clickHeadings1Link() throws Exception{
		Actions builder = new Actions(this.driver);
		builder.moveToElement(this.header_formatsLink).click().build().perform();
		builder.moveToElement(this.headingsLink).build().perform();
		builder.moveToElement(this.heading1Link).click().build().perform();
	}
	
	public FramePage changeTextToHeader() throws Exception {
		log.info("Changing text to Header");
		clickFormatButton();
		clickHeadings1Link();
		return this;
	}
	
	public FramePage enterTextAreaItalicsMessage(String message) {
		log.info("Entering text area Italics message");
		frame.switchToFrame(this.textEditorFrame);
		handlers.enterTextAreaData(this.textArea, Keys.chord(Keys.END, Keys.ENTER) + 
				message + Keys.chord(Keys.SHIFT, Keys.HOME));
		frame.switchToDefaultContent();
		return this;
	}
	
	public void clickFormatsButton() {
		handlers.clickElement(this.formatsButton);
	}
	
	
	public void clickItalicsLink() {
		Actions builder = new Actions(this.driver);
		builder.moveToElement(this.inlineLink).build().perform();
		builder.moveToElement(this.italicLink).click().build().perform();
	}
	
	public FramePage changeTextToItalics() {
		log.info("Changing text to italics");
		clickFormatsButton();
		clickItalicsLink();
		return this;
	}
	
	public FramePage enterTextAreaBulletMessage(String bulletMessage1, String bulletMessage2, 
			String bulletMessage3) {
		log.info("Entering bullet message");
		frame.switchToFrame(this.textEditorFrame);
		handlers.enterTextAreaData(this.textArea, Keys.chord(Keys.END, Keys.ENTER) + bulletMessage1
				+ Keys.ENTER + bulletMessage2 + Keys.ENTER + bulletMessage3 
				+ Keys.chord(Keys.SHIFT, Keys.HOME, Keys.ARROW_UP, Keys.ARROW_UP));
		frame.switchToDefaultContent();
		return this;
	}
	
	public void clickItalicButton() {
		log.info("Clicking Italic button");
		handlers.clickElement(this.italicButton);
	}
	
	public void clickBulletButton() {
		log.info("Clicking bullet button");
		handlers.clickElement(this.bulletButton);
		unSelectText();
	}
	
	public void unSelectText() {
		frame.switchToFrame(this.textEditorFrame);
		handlers.enterTextAreaData(this.textArea, Keys.ARROW_RIGHT+"");
		frame.switchToDefaultContent();
	}
	
	public void applyBullet() {
		log.info("Applying bullet to the text");
		clickItalicButton();
		clickBulletButton();
		Reporter.log(driver.getTitle(), true);
		String snapPath = handlers.takeSnap();
		Reporter.log("<a href='" +snapPath+ "'> <img src='" +snapPath+ "' height='100' width='100' /> </a>");
	}
}