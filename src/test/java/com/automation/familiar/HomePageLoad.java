package com.automation.familiar;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


public class HomePageLoad extends BaseClass {
	
	@Test(priority=1)	
	public void homePageLoad()   {
	
		try
		{
//		WebDriver driver = plateformSelection(obj.getCellData("Preference", "Plateform", 2),obj.getCellData("Preference", "Browser", 2));     
		WebDriver driver = plateformSelection(obj.getCellData("Preference", "Plateform", 2),null);  
	
		logger=report.startTest("Home Page Testing");
		logger.log(LogStatus.PASS, obj.getCellData("Preference", "Plateform", 2)+ ".. Connected");
		String title = driver.getTitle();
		System.out.println(title);
		//cookies acceptance
		if(title.contains("LSAT"))
		{
			logger.log(LogStatus.PASS, title);
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 10))), obj.getCellData("ObjectDetails", "Label", 10));
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 10))), obj.getCellData("ObjectDetails", "Label", 11));
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 10))), obj.getCellData("ObjectDetails", "Label", 12));
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 10))), obj.getCellData("ObjectDetails", "Label", 13));
		}
		else
		{
			
			logger.log(LogStatus.FAIL, title + logger.addScreenCapture(createScreenshot()));
		}
		
		
		driver.quit();
		
	}
	catch(Exception e)
	{
		driver.close();
		driver.quit();
		String screenS =  createScreenshot();
		logger.log(LogStatus.FAIL, String.valueOf(e + ": Home page loading Failed") + logger.addScreenCapture(screenS));
		e.printStackTrace();
		report.endTest(logger);
		 report.flush();
	}
	}	
}