package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		
	 	File src = new File("./Configuration/config.properties");
	 	
	 	try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("FileNotFoundException--" + e.getMessage());
		} catch (IOException e) {
			
			System.out.println("IOException--" + e.getMessage());
		}
	}
	
	public String getBaseURL() {
		
		String url = pro.getProperty("baseURL");
		return url;
	}
	
	public String getFirefoxPath() {
		
		String firefoxPath = pro.getProperty("firefoxPath");
		return firefoxPath; 
	}

}
