package com.automation.familiar;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.LogStatus;

public class InvalidLogin extends BaseClass {
	
	@Test(priority=2)
	public void Invalidlogin()  {
	
		try
		{
//		WebDriver driver = plateformSelection(obj.getCellData("Preference", "Plateform", 2),obj.getCellData("Preference", "Browser", 2));     
		WebDriver driver = plateformSelection(obj.getCellData("Preference", "Plateform", 2),null);  
		logger=report.startTest("Invalid Login Testing with invalid credentails on -- "+ obj.getCellData("Preference", "Plateform", 2));
		logger.log(LogStatus.PASS, obj.getCellData("Preference", "Plateform", 2)+ ".. Connected");
		Actions builder = new Actions(driver);
		//cookies acceptance
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 2))), obj.getCellData("ObjectDetails", "Label", 2));
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 2))).click();
		
		//login as -- click different on desktop and mobile.
		switch(obj.getCellData("Preference", "Plateform", 2))
		{
		
		case"iPhone":
		case"Android":		
			//menu click
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 3))), obj.getCellData("ObjectDetails", "Label", 3));
			driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 3))).click();
			validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath_Mobile", 4))), obj.getCellData("ObjectDetails", "Label", 4));
			driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath_Mobile", 4))).click();
//			driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 3))).click();
			break;
		case "Desktop":
			
			//login click
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 4))), obj.getCellData("ObjectDetails", "Label", 4));
		builder.moveToElement(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 4)))).build().perform();
		break;
		}
		//Validating invalid credentials
//		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 4))),obj.getCellData("ObjectDetails", "Label", 4));
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 5))),obj.getCellData("ObjectDetails", "Label", 5));
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 5))).click();
		//login page enter credentials
		driver.switchTo().frame(0);
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 6))),obj.getCellData("ObjectDetails", "Label", 6));
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 6))).click();
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 6))).sendKeys(obj.getCellData("ObjectDetails", "Value", 6));
//		validation.isElementPresent(driver.findElement(By.id(obj.getCellData("ObjectDetails", "Xpath", 7))),obj.getCellData("ObjectDetails", "Label", 7));
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 7))).sendKeys(obj.getCellData("ObjectDetails", "Value", 7));
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 8))),obj.getCellData("ObjectDetails", "Label", 8));
		driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 8))).click();
		driver.switchTo().defaultContent();
		validation.isElementPresent(driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 9))),obj.getCellData("ObjectDetails", "Label", 9) + driver.findElement(By.xpath(obj.getCellData("ObjectDetails", "Xpath", 9))).getText());
		
		driver.quit();
		
	}
	catch(Exception e)
	{
		
		driver.close();
		driver.quit();
		String screenS =  createScreenshot();
		logger.log(LogStatus.FAIL, String.valueOf(e + ": Login Failed") + logger.addScreenCapture(screenS));
		e.printStackTrace();
		report.endTest(logger);
		 report.flush();
	}
	}	

}
