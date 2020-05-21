package com.automation.familiar;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.automation.familiar.BaseClass;



public class TestCase_Title extends BaseClass{
	
	@Test
	public void loginTestcase() throws MalformedURLException, InterruptedException  {
		
		WebDriver driver = plateformSelection("iPhone", null);
		
		String title = driver.getTitle();
		System.out.println(title);
		
	}
	

}
