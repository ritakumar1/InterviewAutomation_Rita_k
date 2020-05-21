package com.automation.familiar;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	
	public  static String location =System.getProperty("user.dir");
	public static String path = location +"//DataSheet.xlsx";
	public static Excel_Reader obj = new Excel_Reader(path);
	public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
	 public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());

	public static ExtentReports report =new ExtentReports(location+"//Reports//TestReport_" + currentDate + ".html");
	public  static ExtentTest logger = null;
	public static DesiredCapabilities cap = new DesiredCapabilities();
	
@SuppressWarnings("rawtypes")
public static WebDriver plateformSelection(String plateformName, String browserName) throws MalformedURLException
{
	WebDriver driver = null;
	switch(plateformName)
	{
	case "Android": 
		 
	        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
	        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"R");
	        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
	        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
	        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
	        driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"),cap);
	        driver.get("https://Familiar.Lsac.org");
		break;
	case "iPhone":
		 	cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.IOS);
		 	cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"13.2");
	        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
	        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
	        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		driver.get("https://Familiar.Lsac.org");
		break;
	case "Desktop" :
		switch(browserName)
		{
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", "chromedriver 4");
//			ChromeDriverManager.getInstance().setup();
			driver = new ChromeDriver();
			driver.get("https://Familiar.Lsac.org");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			driver.get("Familiar.Lsac.org");
			break;
		case "IE":
			driver = new InternetExplorerDriver();
			driver.get("Familiar.Lsac.org");
			break;
		}
		
	}
	return driver;
	
}

}
