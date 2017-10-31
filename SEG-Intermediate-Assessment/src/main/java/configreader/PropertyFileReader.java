package configreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;

import browser.BrowserType;
import utility.ResourceHandler;

public class PropertyFileReader implements ConfigReader {

	private Properties prop;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			prop.load(ResourceHandler.getResourcePathInputStream("\\src\\main\\resources\\configfile\\" + "config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}

	public String getReservationUrl() {
		return prop.getProperty("ReservationUrl");
	}

	public String getHerokuappUrl() {
		return prop.getProperty("HerokuappUrl");
	}
	
	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplicitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	public long getPollingTimeInMilliSecond() {
		return Long.parseLong(prop.getProperty("PollingTimeInMilliSecond"));
	}
	
	public Level getLoggerLevel() {
		switch (prop.getProperty("Logger.Level")) {
		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}
}