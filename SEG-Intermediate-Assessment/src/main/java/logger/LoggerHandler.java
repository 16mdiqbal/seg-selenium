package logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import utility.ResourceHandler;

public class LoggerHandler {

	public static boolean isLogObjCreated = false;
	
	public static Logger getLogger(Class<?> clazz) {

		if (isLogObjCreated) {
			return Logger.getLogger(clazz);
		}
		
		PropertyConfigurator.configure(ResourceHandler.getResourcePath("\\src\\main\\resources\\configfile\\log4j.properties"));
		isLogObjCreated = true;
		return Logger.getLogger(clazz);
	}
}
