package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceHandler {

	
	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		return path;
	}
	
	public static String getBaseResourcePath() {
		//String basePath = ResourceHelper.class.getClass().getResource("/").getPath();
		String basePath = System.getProperty("user.dir");
		return basePath;
	}

	public static InputStream getResourcePathInputStream(String resource) throws FileNotFoundException {
		return new FileInputStream(ResourceHandler.getResourcePath(resource));
		
	}
	
	public static void main(String[] args) {
		System.out.println(ResourceHandler.getResourcePath("\\configfile\\" + "config.properties"));
		//getBaseResourcePath();
	}

}
