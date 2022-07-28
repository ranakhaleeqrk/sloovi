package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


import utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class BaseClass {
	
	static ReadConfig readConfig = new ReadConfig();
	
	public static String baseURL = readConfig.getBaseURL();
	public static WebDriver driver;

	@Parameters("browser")
	@BeforeClass 
	public void setup(String browser) {
		
		 ChromeOptions options = new ChromeOptions();
		    options.addArguments("use-fake-device-for-media-stream");
		    options.addArguments("use-fake-ui-for-media-stream");

		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "75.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability(ChromeOptions.CAPABILITY, options);
//		    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		
		if(browser.equals("chrome")) {
			
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(caps);
		
		}
		if(browser.equals("firefox")) {
			
		System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
		driver = new FirefoxDriver();
		
		}
		
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(3L, TimeUnit.SECONDS);
		
		
		
		driver.get(baseURL);
		driver.manage().window().maximize();

	}
	
//	@AfterClass
//	public void tearDown() {
//		
//		driver.quit();
//	}

}
