package testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import pageobject.herokuapp.MainPage;

public class FramePageTest extends TC_Common {

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		dataFileName = "TC_Heroku_Frames_TestData";
		dataSheetName = "Frames";
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
	
	@Test(dataProvider="fetchData")
	public void tc_frame(String headerMessage, String italicsMessage, String bulletMessage1,
			String bulletMessage2, String bulletMessage3) throws Exception {
		
		new MainPage(driver)
		.clickFrameLink()
		.clickIframeLink()
		.clickFileButton()
		.clickNewDocumentLink()
		.enterTextAreaHeaderMessage(headerMessage)
		.changeTextToHeader()
		.enterTextAreaItalicsMessage(italicsMessage)
		.changeTextToItalics()
		.enterTextAreaBulletMessage(bulletMessage1, bulletMessage2, bulletMessage3)
		.applyBullet();
	}
}
