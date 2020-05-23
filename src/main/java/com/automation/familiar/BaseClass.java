package com.automation.familiar;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;





import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	
	public  static String location =System.getProperty("user.dir");
	public static String path = location +"//DataSheet.xlsx";
	public static Excel_Reader obj = new Excel_Reader(path);
	public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
	 public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());

	 public static String screenshotpath= System.getProperty("user.dir") +"//Screenshots//Screenshot_" + currentDate + ".png";
	public static ExtentReports report =new ExtentReports(location+"//Reports//TestReport_" + currentDate + ".html");
	public  static ExtentTest logger = null;
	public static DesiredCapabilities cap = new DesiredCapabilities();
	public static String url = obj.getCellData("Preference", "URL", 2);
	public static WebDriver driver = null;



public static WebDriver plateformSelection(String plateformName, String browserName) throws MalformedURLException, InterruptedException
{
	
//	logger=report.startTest("Home Page Testing");
	switch(plateformName)
	{
	case "Android": 
	        cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.ANDROID);
	        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,obj.getCellData("System", "Version", 2));
//	        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "AndroidTest");
	        cap.setCapability(MobileCapabilityType.DEVICE_NAME,obj.getCellData("System", "Name", 2));
	        cap.setCapability(MobileCapabilityType.BROWSER_NAME,obj.getCellData("System", "Browser", 2));
	        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
	        driver = new RemoteWebDriver(new URL(obj.getCellData("System", "Driver", 2)),cap);
	        driver.get(url);
	        
		break;
	case "iPhone":
		 	cap.setCapability(MobileCapabilityType.PLATFORM_NAME,MobilePlatform.IOS);
		 	cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,obj.getCellData("System", "Version", 3));
//		 	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		 	cap.setCapability(MobileCapabilityType.DEVICE_NAME,obj.getCellData("System", "Name", 3));
		 	  cap.setCapability(MobileCapabilityType.BROWSER_NAME,obj.getCellData("System", "Browser", 3));
		 	cap.setCapability("SafariGarbageCollect", true);
	        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000); 
		driver = new IOSDriver(new URL(obj.getCellData("System", "Driver", 2)),cap);
	
		driver.get(url);
		
		break;
	case "Desktop" :
		switch(browserName)
		{
		case "Chrome" :
			System.setProperty("webdriver.chrome.driver", "chromedriver 4");
			driver = new ChromeDriver();
			driver.get(url);
		
			break;
		case "Firefox":
			driver = new FirefoxDriver();
			driver.get(url);
			break;
		case "IE":
			driver = new InternetExplorerDriver();
			driver.get(url);
			break;
		}
		
	}
	
	return driver;
	
}
public static String createScreenshot(){
	 
    // generate screenshot as a file object

    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    try {
        // copy file object to designated location
    	
        FileUtils.copyFile(scrFile, new File(screenshotpath));
       
        
    } catch (IOException e) {
        System.out.println("Error while generating screenshot:\n" + e.toString());
    }
	return screenshotpath;
	
  
}

}
