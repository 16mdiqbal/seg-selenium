package configreader;

import browser.BrowserType;

public interface ConfigReader {

	public BrowserType getBrowser();
	public String getReservationUrl();
	public String getHerokuappUrl();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public long getPollingTimeInMilliSecond();
}
