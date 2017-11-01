package testcase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import commontest.TC_Common;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;

public class TC_Window extends TC_Common {

	@Parameters({"currentUrl"})
	@BeforeClass
	public void setData(String currentUrl) {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getBrowser();
		url = currentUrl;
	}
}
