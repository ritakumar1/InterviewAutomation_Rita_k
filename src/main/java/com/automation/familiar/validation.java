package com.automation.familiar;


import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

public class validation extends BaseClass{
	
	
	@SuppressWarnings("unused")
	public static void isElementPresent(WebElement ele, String pgObj)  
    {
		
       boolean ElementPresent=false;
      String screenS = createScreenshot();
    	  
    	   		if(ele.isDisplayed())
    	   		{
    	   		 ElementPresent=true;
   
                logger.log(LogStatus.PASS, String.valueOf(pgObj + ": is Present"));
                 System.out.println(pgObj + " : is present");
         
                 
    	   		}
    	   		else
    	   		{
    
    	   		logger.log(LogStatus.FAIL, logger.addScreenCapture(screenS));
    	   		System.out.println(pgObj + " : is not present");
    	   		ElementPresent=false;
    	   
    	   	}

    }
	
	
	

}
